import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useLikePersistStore = defineStore(
  'like-persist',
  () => {
    const like = ref(0);
    const fivetime = computed(() => like.value * 5);

    function increment() {
      like.value++;
    }

    return { like, increment, fivetime };
  },
  {
    persist: {
      key: 'like_p', // storage에 저장될 key 이름 (기본값 store ID)
      storage: sessionStorage, // 기본값 : localStorage
      pick: ['like'], // 저장하고 싶은 상태값 지정 가능
    },
  },
);
