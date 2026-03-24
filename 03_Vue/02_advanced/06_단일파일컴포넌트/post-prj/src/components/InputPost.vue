<template>
  <div>
    <input type="text" v-model="newPost.title" />
    <input type="text" v-model="newPost.content" />
    <button @click="addPost()">추가</button>
  </div>
</template>

<script setup>
import { newPost, addPost } from './repository/dataManager.js';
</script>
