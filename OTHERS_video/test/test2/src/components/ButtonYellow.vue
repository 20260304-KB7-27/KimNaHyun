<script setup>
import { computed } from 'vue';

const props = defineProps({
  modelValue: Boolean,
});

const emit = defineEmits(['update:modelValue']);

const toggle = () => {
  emit('update:modelValue', !props.modelValue);
};

const BtnYellowClass = computed(() => [
  'w-full py-3 rounded-2xl font-bold',
  'shadow-[6px_6px_12px_#d9d5ce,-6px_-6px_12px_#ffffff]',
  props.modelValue
    ? 'bg-[#f4f2ee] text-[#645b4c] active:shadow-[inset_4px_4px_8px_#d9d5ce,inset_-4px_-4px_8px_#ffffff]'
    : 'bg-gradient-to-r from-[#fcaf17] to-[#fdb913] text-white active:shadow-[inset_4px_4px_8px_#d79e10,inset_-4px_-4px_8px_#ffd416]',
]);
</script>

<template>
  <button @click="toggle" :class="BtnYellowClass">
    {{ props.modelValue ? 'Following' : 'Follow' }}
  </button>
</template>
