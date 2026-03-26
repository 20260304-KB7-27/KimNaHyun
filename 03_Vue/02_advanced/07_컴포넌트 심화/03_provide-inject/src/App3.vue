<template>
  <div>
    <h2>비동기 컴포넌트</h2>
    <AsyncComponent></AsyncComponent>

    <AsyncComp></AsyncComp>
  </div>
</template>

<script setup>
import { defineAsyncComponent } from 'vue';
import AsyncComponent from './components/AsyncComponent.vue';
import LoadingComp from './components/LoadingComp.vue';
import ErrorComp from './components/ErrorComp.vue';

// 옵션 없이 사용
// const AsyncComp = defineAsyncComponent(
//   () => import('./components/AsyncComponent.vue'),
// );

/*
defineAsyncComponent 사용 시점
- 초기 로딩 속도 개선이 필요할때
- 무겁거나 (차트, 에디터) 자주 사용하지 않는 컴포넌트
*/

// 옵션 적용
const AsyncComp = defineAsyncComponent({
  loader: () =>
    new Promise((resolve) => {
      setTimeout(() => {
        import('./components/AsyncComponent.vue');
      });
    }),
  loadingComponent: LoadingComp, // delay 이후 로딩 중에 표시
  errorComponent: ErrorComp, // timeout 초과 또는 오류 발생 시 표시
  delay: 200,
  timeout: 2000, // 3초 지나면 ErrorComponent
});
</script>

<style lang="scss" scoped></style>
