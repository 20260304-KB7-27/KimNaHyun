<script setup>
import { ref } from 'vue';

// 1. 유저 데이터
const user = {
  name: 'NaHyun Kim',
  role: 'UI/UX Designer',
  icon: '👤',
};

// 2. 원형 차트 데이터 (Pie)
const dailyProgress = ref(75);

// 3. 선 그래프 데이터 (Line)
const weeklyData = ref([
  { label: 'M', value: 30 },
  { label: 'T', value: 55 },
  { label: 'W', value: 40 },
  { label: 'T', value: 85 },
  { label: 'F', value: 50 },
]);
</script>

<template>
  <div class="bg-[#f4f2ee] flex items-center justify-center p-6">
    <div class="flex flex-col gap-6">
      <div class="neo-inset p-5 rounded-[30px] flex items-center gap-5 pr-10">
        <div
          class="w-14 h-14 rounded-full bg-[#f4f2ee] shadow-[5px_5px_10px_#d9d5ce,-5px_-5px_10px_#ffffff] flex items-center justify-center text-xl"
        >
          {{ user.icon }}
        </div>
        <div class="whitespace-nowrap">
          <h2 class="text-[#645b4c] font-bold text-base leading-tight">
            {{ user.name }}
          </h2>
          <p
            class="text-[#a39b8f] text-[10px] uppercase tracking-widest font-bold"
          >
            {{ user.role }}
          </p>
        </div>
      </div>

      <div class="neo-inset p-6 rounded-[30px] min-w-[280px]">
        <h3 class="text-[#645b4c] font-bold mb-2 text-sm">Daily Activity</h3>
        <div class="text-[#a39b8f] text-sm italic">
          차트 내용이 들어갈 자리입니다.
        </div>
      </div>

      <div class="neo-inset p-6 rounded-[30px]">
        <h3 class="text-[#645b4c] font-bold mb-2 text-sm">Weekly Trend</h3>
        <div class="text-[#a39b8f] text-sm">그래프 데이터 시각화 영역</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 안으로 파인 뉴모피즘 효과 */
.neo-inset {
  background: #f4f2ee;
  box-shadow:
    inset 8px 8px 16px #d9d5ce,
    inset -8px -8px 16px #ffffff;
}

/* 텍스트가 줄바꿈되지 않도록 설정 (너비 유지용) */
.whitespace-nowrap {
  white-space: nowrap;
}
</style>
