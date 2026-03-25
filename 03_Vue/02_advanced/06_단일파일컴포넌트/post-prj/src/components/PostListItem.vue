<template>
  <div class="post-item" @click="selectPost(post)">
    {{ post.no }}. {{ post.title }}
    <span class="arrow" v-if="isSelected">▲</span
    ><span class="arrow" v-else>▼</span>
  </div>
</template>

<script setup>
const props = defineProps({ post: Object, isSelected: Boolean });
const emit = defineEmits(['select-post']);
const selectPost = (post) => {
  emit('select-post', post);
};
</script>
