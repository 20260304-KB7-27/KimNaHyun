class Student {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  printProfile() {
    console.log(`이름 : ${this.name}, 나이 : ${this.age}`);
  }
}

let s1 = new Student('홍길동 ', 20);
s1.printProfile();
console.log('printProfile' in s1.__proto__);
console.log('printProfile' in Student.prototype);
