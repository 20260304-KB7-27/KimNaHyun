<template>
  <h1>:: Todolist App</h1>

  <InputTodo @add="addTodo"></InputTodo>
  <TodoList
    :todolist="todolist"
    @toggle="toggleCompleted"
    @delete="deleteTodo"
  ></TodoList>
</template>

<script>
import InputTodo from './components/InputTodo.vue';
import TodoList from './components/TodoList.vue';

export default {
  components: {
    InputTodo,
    TodoList,
  },
  data() {
    let ts = new Date().getTime();

    return {
      todolist: [
        { id: ts, todo: '자전거 타기', completed: false },
        { id: ts + 1, todo: '딸과 공원 산책', completed: true },
        { id: ts + 2, todo: '일요일 애견 카페', completed: false },
        { id: ts + 3, todo: 'Vue 원고 집필', completed: false },
      ],
    };
  },
  methods: {
    deleteTodo(id) {
      let index = this.todolist.findIndex((item) => id == item.id);
      this.todolist.splice(index, 1);
    },
    toggleCompleted(id) {
      let index = this.todolist.findIndex((item) => id == item.id);
      this.todolist[index].completed = !this.todolist[index].completed;
    },
    addTodo(newTodo) {
      this.todolist.push(newTodo);
    },
  },
};
</script>

<style scoped></style>
