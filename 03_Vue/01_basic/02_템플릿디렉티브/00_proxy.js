/* 
Proxy (중계자)
- 특정객체를 직접 조작하는 대신, 앞단의 작업을 가로채 미리 정의된 동작을 할 수 있게 해주는 기능

프록시의 기본구조
- Target : 프록시로 감쌀 원본 객체
- Handler 작업을 가로채서 실행할 트랩들을 정의하는 객체

*/

// 원본객체
const user = {
  name: '홍길동',
  age: 25,
};

const handler = {
  // Trap : 정의가 되어있음

  // 값을 읽으려고 할때 실행
  get(target, prop) {
    console.log(`${prop} 속성에 접근했습니다.`);

    return target[prop];
  },

  // 값을 변경하려고 할 때 실행
  set(target, prop, value) {
    if ((prop = 'age' && value < 0)) {
      console.error('나이는 음수가 될 수 없습니다.');
      return false;
    }

    console.log(`${prop}을 ${value}로 변경합니다. `);
    target[prop] = value;
    return target;
  },
};

const proxyUser = new Proxy(user, handler);
console.log(proxyUser.name);
proxyUser.age = -5;
