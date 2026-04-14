<script setup>
import draggable from 'vuedraggable';

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

const initialCards = [
  { id: 1, type: 'user' },
  { id: 2, type: 'activity' },
  { id: 3, type: 'dashboard' },
  { id: 4, type: 'calendar' },
  { id: 5, type: 'pie' },
  { id: 6, type: 'line' },
];

const cards = ref([...initialCards]);

const resetLayout = () => {
  cards.value = [...initialCards];
};
</script>

<template>
  <div class="bg-[#f4f2ee] min-h-screen flex items-center justify-center p-10">
    <div class="w-full lg:max-w-5xl md:max-w-2xl">
      <!-- 초기화 버튼 -->
      <div class="flex justify-end mb-4">
        <button
          @click="resetLayout"
          class="px-4 py-2 bg-gray-800 text-white rounded-lg transition-all duration-200 hover:scale-105 active:scale-95"
        >
          초기화
        </button>
      </div>

      <!-- 드래그 영역 -->
      <draggable
        v-model="cards"
        item-key="id"
        animation="250"
        class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-10 justify-items-center"
      >
        <template #item="{ element }">
          <Card>
            <UserCard v-if="element.type === 'user'" :user="user" />

            <ActivityChart
              v-else-if="element.type === 'activity'"
              :data="weeklyActivity"
            />

            <DashboardContainer v-else-if="element.type === 'dashboard'" />

            <div v-else-if="element.type === 'calendar'">
              <Calendar v-model="date" class="rounded-lg border p-4" />
              <p class="mt-2 text-sm text-gray-500 text-center">
                Selected Date: {{ date.toString() }}
              </p>
            </div>

            <PieChart
              v-else-if="element.type === 'pie'"
              :value="myValue"
              :total="100"
              unit="%"
            />

            <LineChart
              v-else-if="element.type === 'line'"
              :data="myWeeklyData"
            />
          </Card>
        </template>
      </draggable>
    </div>
  </div>
</template>
