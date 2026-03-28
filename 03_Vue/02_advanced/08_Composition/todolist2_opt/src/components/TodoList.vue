<template>
  <ul>
    <TodoListItem
      v-for="todoitem in todolist"
      :key="todoitem.id"
      :todoitem="todoitem"
      @toggle="toggleCompleted"
      @delete="deleteTodo"
    ></TodoListItem>
  </ul>
</template>

<script>
import TodoListItem from './TodoListItem.vue';

export default {
  components: {
    TodoListItem,
  },
  props: {
    todolist: Array,
  },
  emits: ['toggle', 'delete'],
  methods: {
    toggleCompleted(id) {
      this.$emit('toggle', id);
    },
    deleteTodo(id) {
      this.$emit('delete', id);
    },
  },
};
</script>

<style lang="scss" scoped></style>
