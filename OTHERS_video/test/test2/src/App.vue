<script setup>
import { ref } from 'vue';
import { today, getLocalTimeZone } from '@internationalized/date';

import { Calendar } from '@/components/ui/calendar';
import Card from './components/Card.vue';
import UserCard from './components/UserCard.vue';
import ActivityChart from './components/ActivityChart.vue';
import { weeklyActivity } from './data/activity';
import PieChart from './components/PieChart.vue';
import LineChart from './components/LineChart.vue';
import DashboardContainer from './components/CardsContainer.vue';

const date = ref(today(getLocalTimeZone()));

const user = {
  name: 'User Name',
  role: 'UI/UX Designer',
  icon: '👤',
};
// 임시 데이터
const myValue = ref(75);
const myWeeklyData = ref([
  { label: 'Mon', value: 30 },
  { label: 'Tue', value: 50 },
  { label: 'Wed', value: 45 },
  { label: 'Thu', value: 80 },
  { label: 'Fri', value: 60 },
  { label: 'Sat', value: 90 },
  { label: 'Sun', value: 70 },
]);
</script>

<template>
  <div class="bg-[#f4f2ee] min-h-screen flex items-center justify-center p-10">
    <div class="grid grid-cols-1 md:grid-cols-2 gap-10">
      <Card><UserCard :user="user" /></Card>

      <Card>
        <ActivityChart :data="weeklyActivity" />
      </Card>

      <Card>
        <Calendar v-model="date" class="rounded-lg border p-4" />
        <p class="mt-2 text-sm text-gray-500 text-center">
          Selected Date: {{ date.toString() }}
        </p>
      </Card>
      <Card
        ><PieChart title="Activity" :value="myValue" :total="100" unit="%"
      /></Card>
      <Card><LineChart title="Weekly Trend" :data="myWeeklyData" /></Card>
      <Card><DashboardContainer /></Card>
    </div>
  </div>
</template>
