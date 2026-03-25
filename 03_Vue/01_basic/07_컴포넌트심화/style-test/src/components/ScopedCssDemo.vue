<template>
  <h2>2. 범위 CSS (Scoped)</h2>
  <div class="box">전역 CSS가 적용된 박스입니다</div>
</template>

<script setup></script>

<style scoped>
/* 
Scoped 속성
- 이 컴포넌트에만 스타일을 적용
- Vue가 data-v-해시값 속성을 생성하여 적용함
- 다른 컴포넌트의 같은 클래스명과 충돌하지 않음

scoped 지우면 전역이 적용됨. 
*/

.box {
  background-color: aqua;
  border: 2px solid blue;
}
</style>
