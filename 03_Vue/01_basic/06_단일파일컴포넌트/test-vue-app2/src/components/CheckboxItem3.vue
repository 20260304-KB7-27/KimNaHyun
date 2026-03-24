<template>
  <li><input type="checkbox" :checked="idol.checked" />{{ idol.name }}</li>
</template>

<script setup>
// defineProps : 부모 요소에서 전달받은 Props를 정의하는 함수

// 유효성 검사
defineProps({
  idol: {
    type: Object,
    required: true,

    // Vue 공식 기능
    // 부모가 props를 전달할 때마다 함수가 호출된다.
    //반환되는 값이 false면 콘솔에 경고를 출력해준다.
    validator: (value) => {
      return (
        (typeof value.id === 'number' || typeof value.id === 'string') &&
        typeof value.name === 'string' &&
        typeof value.checked === 'boolean'
      );
    },
  },
});
</script>

<style lang="scss" scoped></style>
