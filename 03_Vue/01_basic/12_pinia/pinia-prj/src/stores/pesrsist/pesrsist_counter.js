import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useCounterPersistStore = defineStore(
  'counter-persist',
  () => {
    const count = ref(0);

    // actions
    function increment() {
      count.value++;
    }

    function reset() {
      count.value = 0; // 초기화
    }

    return { count, increment, reset };
  },
  {
    persist: true, // localstroage에 자동 저장/복원
  },
);
