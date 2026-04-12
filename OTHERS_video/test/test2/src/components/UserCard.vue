<script setup>
import { ref, computed } from 'vue';
import ButtonYellow from './ButtonYellow.vue';

const props = defineProps({
  user: Object,
});

const isFollowed = ref(false);

const toggleFollow = () => {
  isFollowed.value = !isFollowed.value;
};

const followBtnClass = computed(() => [
  'w-full py-3 rounded-2xl font-bold transition-all duration-200 shadow-[6px_6px_12px_#d9d5ce,-6px_-6px_12px_#ffffff]',
  isFollowed.value
    ? 'bg-[#f4f2ee] text-[#645b4c]'
    : 'bg-gradient-to-r from-[#fcaf17] to-[#fdb913] text-white',
]);
</script>

<template>
  <div class="flex flex-col items-center">
    <div
      class="w-24 h-24 rounded-full flex items-center justify-center mb-4 shadow-inner"
    >
      <span class="text-3xl opacity-70">{{ user.icon }}</span>
    </div>

    <h2 class="text-[#645b4c] font-bold text-xl">{{ user.name }}</h2>
    <p class="text-[#a39b8f] text-sm mb-6">{{ user.role }}</p>

    <ButtonYellow v-model="isFollowed" />
  </div>
</template>
