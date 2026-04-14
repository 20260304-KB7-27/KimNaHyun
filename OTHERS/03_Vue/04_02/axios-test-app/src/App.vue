<template></template>

<script setup>
import axios from 'axios';
// const url = 'http://localhost:3000/todos/1';
// const url = '/api/todos/1';
// axios.get(url).then((response) => {
//   console.log('# 응답객체 : ', response);
//   // console.log(typeof response.data);
// });

// // GET
// const requestAPI = async () => {
//   const url = '/api/todos';
//   const response = await axios.get(url);
//   console.log('# 응답객체 : ', response);
// };

// // POST
const requestAPI = async () => {
  try {
    const url = '/api/todos';
    let data = { todo: '윗몸일으키기 3 세트 ', desc: '너무 빠르지 않게 ...' };
    const response = await axios.post(url, data);
    console.log('# 응답객체 : ', response.data);
  } catch (error) {
    console.error('API 요청 실패:', error);
  }
};

requestAPI();
</script>

<style scoped></style>
