<script setup>
import { RouterView, RouterLink } from 'vue-router';
import Header from './pages/Header.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
</script>

<template>
  <div class="container">
    <Header></Header>
    <RouterView></RouterView>
  </div>
</template>

<style scoped>
.container {
  text-align: center;
  margin-top: 10px;
}
</style>
