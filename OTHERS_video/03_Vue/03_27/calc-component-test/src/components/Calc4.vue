<template>
  <div>
    X : <input type="text" v-model.number="x" /><br />
    Y : <input type="text" v-model.number="y" /><br />

    <div>결과 : {{ result }}</div>
  </div>
</template>

<script>
import { computed, reactive } from 'vue';

export default {
  name: 'Calc4',
  setup() {
    const state = reactive({ x: 10, y: 20 });

    const result = computed(() => {
      return state.x + state.y;
    });

    return { state, result };
  },
};
</script>
