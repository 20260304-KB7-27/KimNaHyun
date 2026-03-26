<template>
  <input
    type="text"
    :value="name"
    @input="$emit('update:name', $event.target.value)"
  />
</template>

<script>
export default {
  name: 'InputName', // 닫는 따옴표 추가
  props: ['name'], // 부모의 v-model:name="searchName"에서 'name'을 받음
  emits: ['update:name'], // (선택사항이지만 권장) 발생시킬 이벤트를 명시
};
</script>
