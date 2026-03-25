<template>
  <div>
    <h3>당신이 경험한 프론트엔드 기술은? (Slot 사용 O)</h3>
    <CheckBox2
      v-for="item in items"
      :key="item.id"
      :id="item.id"
      :checked="item.checked"
      @check-changed="checkBoxChanged"
      :label="item.label"
    >
      <p>내용 추가</p>
      <!-- 여기 부분이 slot 위치에 들어감 -->
    </CheckBox2>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import CheckBox2 from './CheckBox2.vue';

const items = ref([
  { id: 'V', checked: true, label: 'Vue' },
  { id: 'A', checked: false, label: 'Angular' },
  { id: 'R', checked: false, label: 'React' },
  { id: 'S', checked: false, label: 'Svelte' },
]);

function checkBoxChanged(e) {
  let item = items.value.find((item) => item.id === e.id);
  item.checked = e.checked;
}
</script>

<style lang="scss" scoped></style>
