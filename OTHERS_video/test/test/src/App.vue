<script setup>
import UserCard from './components/UserCard.vue';
import ActivityChart from './components/ActivityChart.vue';
import { weeklyActivity } from './data/activity';
import { COLORS } from './composables/useDesign';
import Card from './components/Card.vue';

const user = {
  name: 'User Name',
  role: 'UI/UX Designer',
  icon: '👤',
};
</script>

<template>
  <div class="bg-[#f4f2ee] min-h-screen flex items-center justify-center p-10">
    <div class="grid grid-cols-1 md:grid-cols-2 gap-10">
      <UserCard :user="user" />
      <Card> <ActivityChart :data="weeklyActivity" /></Card>
    </div>
  </div>
</template>
