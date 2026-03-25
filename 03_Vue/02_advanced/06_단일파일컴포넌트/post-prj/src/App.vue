<template>
  <h1>📌 게시판 목록 v2</h1>
  <InputPost @add="addPost"></InputPost>
  <PostList
    :posts="posts"
    :selectedPosts="selectedPosts"
    :editingPost="editingPost"
    @select-post="selectPost"
    @save-edit="saveEdit"
    @delete-post="deletePost"
    @start-edit="startEdit"
    @cancel-edit="cancelEdit"
  ></PostList>
</template>

<script setup>
import InputPost from './components/InputPost.vue';
import PostList from './components/PostList.vue';
import data from './data/data.json';

import { ref } from 'vue';
const posts = ref(data);

const selectedPosts = ref([]);

function selectPost(post) {
  if (!selectedPosts.value.includes(post)) {
    selectedPosts.value.push(post);
  } else {
    selectedPosts.value = selectedPosts.value.filter((p) => p.no !== post.no);
  }
  console.log(selectedPosts);
}

const nextNo = ref(Math.max(...data.map((p) => p.no), 0) + 1);

function addPost(newPost) {
  posts.value.push({
    no: nextNo.value,
    ...newPost,
  });

  console.log(posts);
  nextNo.value++;
}

function deletePost(no) {
  posts.value = posts.value.filter((p) => p.no !== no);
  selectedPosts.value = selectedPosts.value.filter((p) => p.no !== no);
}

const editingPost = ref({
  no: null,
  title: null,
  content: null,
});

function startEdit(post) {
  editingPost.value = { ...post };
}

function saveEdit() {
  if (!editingPost.value.title || !editingPost.value.content) {
    alert('내용을 입력해주세요.');
    return;
  }

  const index = posts.value.findIndex((p) => p.no === editingPost.value.no);

  if (index !== -1) {
    posts.value[index] = { ...editingPost.value };
    const sIndex = selectedPosts.value.findIndex(
      (p) => p.no === editingPost.value.no,
    );
    if (sIndex !== -1) {
      selectedPosts.value[sIndex] = { ...editingPost.value };
    }
  }
  cancelEdit();
}

function cancelEdit() {
  editingPost.value = {
    no: null,
    title: null,
    content: null,
  };
}
</script>
