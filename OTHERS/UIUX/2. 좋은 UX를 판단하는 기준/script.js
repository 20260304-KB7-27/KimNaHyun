const loginBtn = document.getElementById('loginBtn');
const errorBox = document.getElementById('errorBox');
const successBox = document.getElementById('successBox');

// 메시지 초기화 함수
// 로그인 시도 전에 기존 메시지를 모두 숨기고 초기화
function resetMessages() {
  // 메시지 영역 숨기기
  errorBox.classList.add('hidden');
  successBox.classList.add('hidden');
  helperLink.classList.add('hidden');

  // 메시지 내용 초기화
  errorBox.textContent = '';
  successBox.textContent = '';
}

loginBtn.addEventListener('click', () => {
  resetMessages();
  const id = document.getElementById('userId');
  const pass = document.getElementById('userPw');
  const helperLink = document.getElementById('helperLink');
  const validId = ['hello'];
  const validPw = ['helloworld'];

  if (id.value.length < 4) {
    errorBox.textContent = 'id의 길이가 4 이상이어야 됩니다';
    errorBox.classList.remove('hidden');
  } else if (pass.value.length < 8) {
    errorBox.textContent = '비밀번호의 길이가 8 이상이어야 됩니다';
    errorBox.classList.remove('hidden');
  } else if (!validId.includes(id.value)) {
    errorBox.textContent = '아이디가 틀렸습니다.';
    errorBox.classList.remove('hidden');
    helperLink.classList.remove('hidden');
  } else if (!validPw.includes(pass.value)) {
    errorBox.textContent = '비밀번호가 틀렸습니다.';
    errorBox.classList.remove('hidden');
    helperLink.classList.remove('hidden');
  } else {
    successBox.textContent = '로그인에 성공하였습니다. ';
    successBox.classList.remove('hidden');
  }

  console.log(id);
});
