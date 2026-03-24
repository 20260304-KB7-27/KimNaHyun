<template>
  <h1>📌 게시판 목록 v2</h1>
  <InputPost />
</template>

<script setup>
import InputPost from './components/InputPost.vue';
import data from './data/data.json';

const { ref } = Vue;
let ts = new Date().getTime();
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

function closePost(post) {
  if (selectedPosts.value.includes(post)) {
    selectedPosts.value = selectedPosts.value.filter((p) => p.no !== post.no);
  }
  console.log(selectedPosts);
}

const nextNo = ref(Math.max(...data.map((p) => p.no), 0) + 1);
const newPost = ref({
  title: null,
  content: null,
});

function addPost() {
  if (!newPost.value.title || !newPost.value.content) {
    alert('제목과 내용은 빈칸일 수 없습니다.');
    return;
  }

  posts.value.push({
    no: nextNo.value,
    ...newPost.value,
  });

  nextNo.value++;
  newPost.value.title = null;
  newPost.value.content = null;
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
