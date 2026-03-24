<template>
  <li><input type="checkbox" v-model="checked" /> 옵션</li>
</template>

<script setup>
import { ref } from 'vue';

const checked = ref(false);
</script>

<style lang="scss" scoped></style>

<!-- 
<script setup>
setup 하지 않았을 때 export default 사용해야 됨. 
-->
