function outer() {
  return function () {
    console.log('Hello Function...!');
  };
}
//호출 1
outer()();
//호출 2
var fn = outer();
fn();
