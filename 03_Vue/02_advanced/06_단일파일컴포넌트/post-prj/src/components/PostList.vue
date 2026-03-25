<template>
  <ul>
    <li v-for="post in posts" :key="post.no">
      <PostListItem
        :post="post"
        :isSelected="selectedPosts.includes(post)"
        @select-post="selectPost"
      ></PostListItem>
      <PostListCard
        :post="post"
        :isSelected="selectedPosts.includes(post)"
        :editingPost="editingPost"
        @select-post="selectPost"
        @save-edit="saveEdit"
        @delete-post="deletePost"
        @start-edit="startEdit"
        @cancel-edit="cancelEdit"
      ></PostListCard>
    </li>
  </ul>
</template>

<script setup>
import { ref } from 'vue';
import PostListItem from './PostListItem.vue';
import PostListCard from './PostListCard.vue';
const props = defineProps({
  posts: Array,
  selectedPosts: Array,
  editingPost: Object,
});

const emit = defineEmits([
  'select-post',
  'save-edit',
  'delete-post',
  'start-edit',
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
