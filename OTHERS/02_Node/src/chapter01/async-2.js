function displayA() {
  console.log('A');
}

function displayB(callback) {
  setTimeout(() => {
    console.log('B');
    callback();
  }, 2000);
}
function displayC(n) {
  console.log(n);
}
displayA();
displayB(displayC(1));
