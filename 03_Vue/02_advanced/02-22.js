let p = new Promise((resolve, reject) => {
  console.log(`first!`);
  resolve('second');
});

p.then((msg) => {
  console.log(msg);
  return 'third';
})
  .then((msg) => {
    console.log(msg);
  })
  .catch((error) => {
    console.log('오류 발생 ==>  ' + error);
  });
