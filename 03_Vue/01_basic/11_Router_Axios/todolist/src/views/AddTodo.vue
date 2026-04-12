<template>
  <div class="row">
    <div class="col p-3">
      <h2>할일추가</h2>
    </div>
  </div>
  <div class="row">
    <div class="col">
      <div class="form-group">
        <!-- htmlFor : table이상 -->
        <label htmlFor="todo">할일:</label>
        <input
          type="text"
          class="form-control"
          id="todo"
          v-model="todoItem.todo"
        />
      </div>
      <div class="form-group">
        <label htmlFor="desc">설명:</label>
        <textarea
          class="form-control"
          rows="3"
          id="desc"
          v-model="todoItem.desc"
        ></textarea>
      </div>
      <div class="form-group">
        <button
          type="button"
          class="btn btn-primary m-1"
          @click="addTodoHandler"
        >
          추가
        </button>
        <button
          type="button"
          class="btn btn-primary m-1"
          @click="router.push('/todos')"
        >
          취소
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { inject, reactive } from 'vue';
import { useRouter } from 'vue-router';

const { addTodo } = inject('actions');

// 화면 전환용 router
const router = useRouter();

// 할일 추가용
const todoItem = reactive({ todo: '', desc: '' });

// 할일 추가 버튼 핸들러
const addTodoHandler = () => {
  // todoItem을 값만 복사해서 전달
  addTodo({ ...todoItem });

  // 목록화면으로 이동
  router.push('/todos');
};
</script>

<style scoped></style>
