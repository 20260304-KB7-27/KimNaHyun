<script setup>
import { ref } from 'vue';
import Calendar from '@/components/ui/calendar/Calendar.vue';

const date = ref(new Date());
</script>
<template>
  <Calendar
    mode="single"
    :selected="date"
    @update:selected="(val) => (date = val)"
    class="rounded-lg border"
  />
</template>

<style scoped></style>
