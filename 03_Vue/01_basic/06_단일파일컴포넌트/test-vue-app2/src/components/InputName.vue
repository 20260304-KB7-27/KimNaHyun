<template>
  <div>
    이름 : <input type="text" v-model="name" />
    <!-- name-change라는 이벤트로 외부(부모 컴포넌트)로 보낼 수 있게 등록 -->
    <button @click="emit('name-change', { name })">이벤트 발신</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const name = ref('');
const emit = defineEmits(['name-change']);
</script>

<style lang="scss" scoped></style>
