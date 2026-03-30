<template>
  <input
    type="text"
    v-model.trim="todo"
    @keyup.enter.exact="addTodo"
    @keyup.ctrl.enter.exact="addTodo"
  />
  <button @click="addTodo">추가</button>
</template>

<script>
export default {
  emits: ['add'],
  data() {
    return {
      todo: '',
    };
  },
  methods: {
    addTodo(e) {
      if (this.todo === '') return;

      this.$emit('add', {
        id: new Date().getTime(),
        todo: this.todo,
        completed: e?.ctrlKey ? true : false,
      });

      this.todo = '';
    },
  },
};
</script>

<style lang="scss" scoped></style>
