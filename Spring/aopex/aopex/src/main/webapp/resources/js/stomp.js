// 🌟 StompJs v7 클라이언트 인스턴스 생성 (SockJS를 사용할 것이므로 brokerURL 설정은 비워둡니다)
const stompClient = new StompJs.Client({});

// 🌟 대화방 격리 및 나가기 기능을 추적하기 위한 전역 상태 변수
let currentRoomId = null;
let currentSubscription = null;

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// 웹소켓 & STOMP 연결 성공 시 실행될 콜백 함수
stompClient.onConnect = (frame) => {
    console.log("Connected: ", frame);
    setConnected(true);

    const name = document.getElementById('name').value;

    // 🌟 1. JSP 화면의 input(id="roomId")에서 방 ID를 동적으로 가져옵니다. (없으면 default_room)
    currentRoomId = document.getElementById('roomId')?.value || 'default_room';

    console.log(`구독 요청 방 번호: ${currentRoomId}`);

    // 🌟 2. 고정 주소가 아닌 /topic/chat/{roomId} 주소로 해당 대화방만 격리 구독(Subscribe)합니다.
    currentSubscription = stompClient.subscribe(`/topic/chat/${currentRoomId}`, (chat) => {
        console.log(`[수신 데이터] /topic/chat/${currentRoomId} -> `, chat.body);
        const message = JSON.parse(chat.body);

        // 🌟 3. 백엔드(ChatController)에서 가공해 준 메시지 타입(type)에 따라 화면에 출력 방식 분기
        if (message.type === 'ENTER' || message.type === 'QUIT') {
            // 입장/퇴장 시스템 안내 메시지 출력 ("XXX님이 입장하셨습니다.")
            showMessage(message.content);
        } else {
            // 일반 대화 메시지 출력 ("이름: 내용")
            showMessage(`${message.name}: ${message.content || ''}`);
        }
    });

    // 🌟 4. 연결과 동시에 백엔드 핸들러로 입장(ENTER) 알림 메시지 발행(Publish)
    stompClient.publish({
        destination: `/app/chat/${currentRoomId}`,
        body: JSON.stringify({
            type: 'ENTER',
            name: name,
            content: ''
        })
    });
};

// 상태값에 따른 UI 제어 함수
function setConnected(connected) {
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const messages = document.getElementById('chat-messages');
    const roomIdInput = document.getElementById('roomId');

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;

    // 연결 중에는 방 번호를 바꿀 수 없도록 입력창 활성화/비활성화 처리
    if (roomIdInput) roomIdInput.disabled = connected;

    if (!connected) {
        messages.innerHTML = ''; // 연결 종료 시 메시지 창 초기화
    }
}

// [연결] 버튼 클릭 시 동작
function connect() {
    const name = document.getElementById('name').value;
    const roomId = document.getElementById('roomId').value;

    if (!name.trim()) {
        alert("이름(닉네임)을 입력해주세요.");
        return;
    }
    if (!roomId.trim()) {
        alert("대화방 ID를 입력해주세요.");
        return;
    }

    // 🌟 5. 백엔드의 .withSockJS() 브로커 설정과 통신 규격을 맞추기 위해 SockJS 팩토리 어댑터 주입
    stompClient.webSocketFactory = function () {
        // 브라우저가 접속한 현재 주소(localhost 또는 IP)를 동적으로 추적하여 SockJS 객체를 리턴합니다.
        return new SockJS(`http://${window.location.host}/chat-app`);
    };

    stompClient.activate(); // STOMP 활성화 및 연결 시도
}

// [끊기/나가기] 버튼 클릭 시 동작
function disconnect() {
    const name = document.getElementById('name').value;

    if (stompClient.connected) {
        // 🌟 6. 연결을 완전히 완전히 끊기 전에, 해당 방 사용자들에게 퇴장(QUIT) 메시지를 먼저 발행합니다.
        stompClient.publish({
            destination: `/app/chat/${currentRoomId}`,
            body: JSON.stringify({
                type: 'QUIT',
                name: name,
                content: ''
            })
        });

        // 🌟 7. 퇴장 메시지가 서버 브로커를 거쳐 전달될 수 있도록 200ms 미세 딜레이 후 연결 해제 진행
        setTimeout(() => {
            if (currentSubscription) {
                currentSubscription.unsubscribe(); // 특정 방 구독 해제
            }
            stompClient.deactivate(); // 웹소켓 비활성화
            setConnected(false);
            console.log('Disconnected Success');
        }, 200);
    }
}

// [Send] 버튼 클릭 시 동작 (특정 대화방 내에서 대화하기)
function sendMessage() {
    const name = document.getElementById('name').value;
    const content = document.getElementById('content').value;

    if (!content.trim()) return; // 빈 메시지 발송 방지
    if (!stompClient.connected) {
        alert("웹소켓 연결이 되어있지 않습니다.");
        return;
    }

    console.log("[발송 데이터] ", { currentRoomId, name, content });

    // 🌟 8. 지정된 방 주소(/app/chat/{roomId})로 일반 대화(TALK) 타입을 명시하여 JSON 데이터 발행
    stompClient.publish({
        destination: `/app/chat/${currentRoomId}`,
        body: JSON.stringify({
            type: 'TALK',
            name: name,
            content: content
        })
    });

    document.getElementById('content').value = ''; // 메시지 입력창 초기화
    document.getElementById('content').focus();    // 포커스 유지
}

// 테이블 본문에 채팅 텍스트 행을 추가하는 함수
function showMessage(message) {
    const messages = document.getElementById('chat-messages');

    // 🌟 안전하게 행(tr)과 칸(td) 객체를 직접 생성하여 주입하는 방식으로 변경
    const row = document.createElement('tr');
    const cell = document.createElement('td');

    cell.textContent = message; // 텍스트 안전 대입
    row.appendChild(cell);
    messages.appendChild(row); // 테이블 본문에 동적 추가
}

// DOM 로드 완료 시 이벤트 바인딩
window.addEventListener("DOMContentLoaded", (event) => {
    const forms = document.querySelectorAll('.form-inline');
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const sendBtn = document.getElementById('send');

    connectBtn.addEventListener('click', () => connect());
    disconnectBtn.addEventListener('click', () => disconnect());
    sendBtn.addEventListener('click', () => sendMessage());

    // 폼 엔터 입력 시 페이지가 새로고침(submit)되는 현상 방지
    for (const form of forms) {
        form.addEventListener('submit', (e) => e.preventDefault());
    }
});