<script setup>
import { useCounterStore } from './stores/counter.js';

import { computed } from 'vue';
const { increment } = useCounterStore();
const store = useCounterStore();

const doubleCount = computed(() => store.doubleCount);

// const store = useCounterStore();
// console.log(store);
</script>

<template>
  count : {{ store.count }}<br />
  double count : {{ store.doubleCount }}<br />
  <button @click="store.increment">Increment</button>
</template>

<style scoped></style>
