fs = require('fs');
fs.readFile('./example.txt', 'utf-8', (err, data) => {
  if (err) {
    console.error(err);
  }
  console.log(data);
});
// 터미널 위치가 기준이 됨 => 현재 위치 기준으로 하고 싶다 => copy
