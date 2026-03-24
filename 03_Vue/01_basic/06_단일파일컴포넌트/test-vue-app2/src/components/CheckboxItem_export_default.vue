<template>
  <li><input type="checkbox" v-model="checked" /> 옵션</li>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'CheckboxItem',
  setup() {
    const checked = ref(false);
    return {
      checked,
    };
  },
};
</script>

<style lang="scss" scoped></style>

<!-- setup 하지 않았을 때 export default 사용해야 됨. -->
