<template>
  <div
    class="bg-[#f4f2ee] min-h-screen flex items-center justify-center p-10 font-sans"
  >
    <div class="grid grid-cols-1 md:grid-cols-2 gap-10">
      <div
        class="w-80 p-8 rounded-[40px] bg-[#f4f2ee] shadow-[15px_15px_30px_#d9d5ce,-15px_-15px_30px_#ffffff]"
      >
        <div class="flex flex-col items-center">
          <div
            class="w-24 h-24 rounded-full bg-[#f4f2ee] shadow-[inset_6px_6px_12px_#d9d5ce,inset_-6px_-6px_12px_#ffffff] flex items-center justify-center mb-4"
          >
            <span class="text-3xl opacity-70">{{ user.icon }}</span>
          </div>

          <h2 class="text-[#645b4c] font-bold text-xl">{{ user.name }}</h2>
          <p class="text-[#a39b8f] text-sm mb-6">{{ user.role }}</p>

          <button
            @click="toggleFollow"
            :class="[
              'w-full py-3 rounded-2xl font-bold transition-all duration-200 shadow-[6px_6px_12px_#d9d5ce,-6px_-6px_12px_#ffffff]',
              isFollowed
                ? 'bg-[#f4f2ee] text-[#645b4c] active:shadow-[inset_4px_4px_8px_#d9d5ce,inset_-4px_-4px_8px_#ffffff]'
                : 'bg-gradient-to-r from-[#fcaf17] to-[#fdb913] text-white active:shadow-[inset_4px_4px_8px_#d79e10,inset_-4px_-4px_8px_#ffd416]',
            ]"
          >
            {{ isFollowed ? 'Following' : 'Follow' }}
          </button>
        </div>
      </div>

      <div
        class="w-80 p-8 rounded-[40px] bg-[#f4f2ee] shadow-[15px_15px_30px_#d9d5ce,-15px_-15px_30px_#ffffff]"
      >
        <h3 class="text-[#645b4c] font-bold mb-8">Weekly Activity</h3>
        <div class="flex justify-between items-end h-32 px-2">
          <div
            v-for="item in weeklyActivity"
            :key="item.day"
            class="w-4 h-full bg-[#f4f2ee] rounded-full shadow-[inset_4px_4px_8px_#d9d5ce,inset_-4px_-4px_8px_#ffffff] relative overflow-hidden"
          >
            <div
              class="absolute bottom-0 w-full transition-all duration-500 rounded-full"
              :class="item.colorClass"
              :style="{ height: item.height + '%' }"
            ></div>
          </div>
        </div>

        <div
          class="flex justify-between mt-4 text-xs text-[#a39b8f] px-1 font-medium"
        >
          <span v-for="item in weeklyActivity" :key="item.day">{{
            item.day
          }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';

// 사용자 정보 데이터 (반응형)
const user = reactive({
  name: 'User Name',
  role: 'UI/UX Designer',
  icon: '👤',
});

// 팔로우 상태 (반응형)
const isFollowed = ref(false);

// 팔로우 버튼 클릭 핸들러
const toggleFollow = () => {
  isFollowed.value = !isFollowed.value;
};

// 주간 활동 데이터 (원본 디자인의 색상 및 그라데이션 완벽 복원)
const weeklyActivity = [
  // 월: 단색 골드 1 (#fcaf17)
  { day: 'Mon', height: 60, colorClass: 'bg-[#fcaf17]' },
  // 화: 단색 골드 2 (#fdb913)
  { day: 'Tue', height: 85, colorClass: 'bg-[#fdb913]' },
  // 수: 골드 그라데이션 (#fcaf17 -> #fdb913)
  {
    day: 'Wed',
    height: 40,
    colorClass: 'bg-gradient-to-t from-[#fcaf17] to-[#fdb913]',
  },
  // 목: 단색 웜 그레이 (#645b4c) + 불투명도 60%
  { day: 'Thu', height: 70, colorClass: 'bg-[#645b4c] opacity-60' },
];
</script>

<style scoped>
/* 원본 디자인의 폰트 및 트랜지션 효과는 Tailwind CSS 클래스로 완벽하게 처리되어, 
   추가적인 scoped 스타일이 필요하지 않습니다. */
</style>
