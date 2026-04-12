<template>
  <h1>Members</h1>
  <div v-for="m in members" :key="m.id">
    <RouterLink :to="{ name: 'members/id', params: { id: m.id } }">
      <img :src="m.photo" style="width: 90px; height: 100px" />
      <h6>{{ m.name }}</h6>
    </RouterLink>
  </div>
</template>

<script setup>
import members from '@/members.json';
</script>

<style scoped></style>
