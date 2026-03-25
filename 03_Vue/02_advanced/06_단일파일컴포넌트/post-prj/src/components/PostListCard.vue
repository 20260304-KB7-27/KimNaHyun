<template>
  <div class="post-content" v-if="isSelected">
    <div v-if="post.no !== editingPost.no">
      <h3>{{ post.title }}</h3>
      <p>{{ post.content }}</p>
      <button @click="startEdit(post)">수정</button>
      <button @click="deletePost(post.no)">삭제</button>
      <button @click="selectPost(post)">닫기</button>
    </div>
    <div v-else class="post-edit">
      <input type="text" v-model="editingPost.title" />
      <textarea v-model="editingPost.content"></textarea>
      <button @click="saveEdit()">수정</button>
      <button @click="cancelEdit()">취소</button>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  post: Object,
  isSelected: Boolean,
  editingPost: Object,
});

const emit = defineEmits([
  'delete-post',
  'select-post',
  'start-edit',
  'save-edit',
  'cancel-edit',
]);

const deletePost = (no) => {
  emit('delete-post', no);
};
const selectPost = (post) => {
  emit('select-post', post);
};
const startEdit = (post) => {
  emit('start-edit', post);
};
const saveEdit = () => {
  emit('save-edit');
};
const cancelEdit = () => {
  emit('cancel-edit');
};
</script>
