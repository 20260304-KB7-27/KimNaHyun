<template>
  <div v-if="member">
    <img :src="member.photo" alt="" />
    <h2>{{ member.name }}</h2>
    <h2>{{ member.role }}</h2>
    <h2>{{ member.desc }}</h2>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import members from '@/members.json';
import { computed } from 'vue';

const route = useRoute();
const member = computed(() =>
  members.find((m) => m.id === Number(route.params.id)),
);
</script>

<style scoped></style>
