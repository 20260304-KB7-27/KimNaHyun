<template>
  <div>
    <input type="text" v-model.trim="title" />
    <input type="text" v-model.trim="content" />
    <button @click="addPost">추가</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const title = ref('');
const content = ref('');

const emit = defineEmits(['add']);

const addPost = (e) => {
  if (title.value == '') return;
  if (content.value == '') return;

  emit('add', {
    title: title.value,
    content: content.value,
  });
  title.value = '';
  content.value = '';
};
</script>
