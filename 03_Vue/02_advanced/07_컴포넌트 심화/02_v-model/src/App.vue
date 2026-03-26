<template>
  <h1>v-model 테스트</h1>
  <hr />

  <!-- 하나의 컴포넌트   -->
  <BaseInput
    v-model="modelValue"
    label="닉네임"
    placeholder="닉네임을 입력해주세요"
  ></BaseInput>

  <BaseInput
    v-model:modelValue="lastname"
    label="성"
    placeholder="성을 입력해주세요"
  ></BaseInput>

  <BaseInput
    v-model:modelValue="firstname"
    label="이름"
    placeholder="이름을 입력해주세요"
  ></BaseInput>

  <p>
    닉네임 : <strong>{{ modelValue }}</strong>
  </p>

  <p>
    전체 이름 : <strong>{{ lastname }}{{ firstname }}</strong>
  </p>
</template>

<script setup>
import BaseInput from './components/BaseInput.vue';
import { ref } from 'vue';

// 입력받을 반응형 데이터
const modelValue = ref('');
const firstname = ref('');
const lastname = ref('');
</script>
<style scoped></style>
