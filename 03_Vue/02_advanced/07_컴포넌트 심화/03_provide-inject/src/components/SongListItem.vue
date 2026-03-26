<template>
  <li>
    <i :class="song.done ? icons.checked : icons.unchecked"></i>
    {{ song.title }}
  </li>
</template>

<script setup>
import { inject } from 'vue';

const props = defineProps(['song']);

// inject('key') : 상위 컴포넌트에서 전달된 provide 수신
const icons = inject('icons');
</script>
