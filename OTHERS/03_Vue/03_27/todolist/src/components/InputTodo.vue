<template>
  <input
    type="text"
    v-model.trim="todo"
    @keyup.enter.exact="addTodo"
    @keyup.ctrl.enter.exact="addTodo"
  />
  <button @click="addTodo">추가</button>
</template>

<script setup>
import { ref } from 'vue';
const todo = ref('');
const emit = defineEmits(['add']);

const addTodo = (e) => {
  if (todo.value === '') return;
  emit('add', {
    id: new Date().getTime(),
    todo: todo.value,
    completed: e.ctrlKey ? true : false,
  });
  todo.value = '';
};
</script>

<style lang="scss" scoped></style>
