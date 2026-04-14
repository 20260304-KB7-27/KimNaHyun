import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/pages/Home.vue';
import About from '@/pages/About.vue';
import TodoList from '../pages/TodoList.vue';
import TodoAdd from '@/pages/TodoAdd.vue'; // 👈 추가

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: Home },
    { path: '/home', component: Home },
    { path: '/about', component: About },
    { path: '/todos', component: TodoList },
    { path: '/todos/add', component: TodoAdd },
  ],
});

export default router;
