// const
// 재할당을 금지

const p1 = { name: 'jonh', age: 20 };

// 객체 내부 값은 변경 가능
p1.age = 22;
p1.name = 'bear';

console.log(p1);

// 새 객체 할당 불가. TypeError 발생
// p1 = { name: 'lee', age: 25 };

// stack 영역에서의 변경을 막음. heap영역의 내용은 가능.
