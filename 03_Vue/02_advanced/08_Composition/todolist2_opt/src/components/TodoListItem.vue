<template>
  <li @click="toggleCompleted(todoitem.id)">
    <span
      :style="{ textDecoration: todoitem.completed ? 'line-through' : 'none' }"
    >
      {{ todoitem.todo }} {{ todoitem.completed ? '[완료]' : '' }}
    </span>

    <!-- 삭제하기 -->
    <span @click.stop="deleteTodo(todoitem.id)">삭제</span>
  </li>
</template>

<script>
export default {
  props: {
    todoitem: Object,
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
