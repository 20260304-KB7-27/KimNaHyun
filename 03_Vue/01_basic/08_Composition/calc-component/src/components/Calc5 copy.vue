<template>
  <div>
    X : <input type="text" v-model.number="x" /><br />
    <div>결과 : {{ result }}</div>
  </div>
</template>

<!-- 
<script setup>
- 간결한 컴포넌트를 작성하기 위한 문법
- 보일러플레이트 코드 감소
- 변수/함수를 템플릿에서 바로 사용 가능
-->
<script setup>
import { ref, watch } from 'vue';

const x = ref(10);
const result = ref(20);

watch(x, (current, old) => {
  console.log(`${old} -> ${current}`);

  // computed : 계산 결과를 반황하는 파생값 -> 템플릿 표시용
  // watch : 변화에 반응하는 사이드이펙트 -> API 호출, 로깅, 감시대상이 아닌 다른 상태값을 변경하고 싶을 때
  result.value = x.value * 2;
});
</script>
