import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

// 바로 아래 'like' : store의 고유 id
export const useLikeStore = defineStore('like', () => {
  // state
  const like = ref(Number(sessionStorage.getItem('like-session')));

  // getter
  // 여러 컴포넌트에서 사용하는 (공유되는) 계산 값
  const fivetime = computed(() => like.value * 5);

  // actions
  function increment() {
    like.value++;

    /*
    sessionStorage
    - 현재 탭에서만 유지 됨 (임시 데이터, 일회성 상태)
    */
    sessionStorage.setItem('like-session', like.value);
  }

  return { like, increment, fivetime };
});
