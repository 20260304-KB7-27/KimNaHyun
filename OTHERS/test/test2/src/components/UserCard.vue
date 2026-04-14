<script setup>
import { ref, computed } from 'vue';
import ButtonYellow from './ButtonYellow.vue';

const props = defineProps({
  user: Object,
});

const isFollowed = ref(false);

const isPressed = ref(false);

const avatarClass = computed(() => [
  'w-24 h-24 rounded-full flex items-center justify-center mb-4 transition-all duration-200 bg-[#f4f2ee]',
  isPressed.value
    ? 'shadow-[6px_6px_12px_#d9d5ce,-6px_-6px_12px_#ffffff] scale-100'
    : 'shadow-[inset_6px_6px_12px_#d9d5ce,inset_-6px_-6px_12px_#ffffff] scale-100',
]);
</script>

<template>
  <div class="flex flex-col items-center">
    <div
      :class="avatarClass"
      @mousedown="isPressed = true"
      @mouseup="isPressed = false"
      @mouseleave="isPressed = false"
    >
      <span class="text-3xl opacity-70">{{ user.icon }}</span>
    </div>

    <h2 class="text-[#645b4c] font-bold text-xl">{{ user.name }}</h2>
    <p class="text-[#a39b8f] text-sm mb-6">{{ user.role }}</p>

    <ButtonYellow v-model="isFollowed" />
  </div>
</template>
