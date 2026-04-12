<template>
  <!-- todo 추가 버튼 -->
  <div class="row">
    <div class="col p-3">
      <RouterLink class="btn btn-primary" to="/todos/add">추가</RouterLink>
      <ul class="list-group">
        <TodoItem
          v-for="todoItem in todoList"
          :key="todoItem.id"
          :todoItem="todoItem"
        />
      </ul>
    </div>
  </div>
</template>

<script setup>
import TodoItem from '@/components/TodoItem.vue';
import { inject } from 'vue';

const todoList = inject('todolist');
</script>

<style scoped></style>
