<script setup>
const props = defineProps({
  data: Array,
});

const getBarClass = (type) => {
  switch (type) {
    case 'gold1':
      return 'bg-[#fcaf17]';
    case 'gold2':
      return 'bg-[#fdb913]';
    case 'gradient':
      return 'bg-gradient-to-t from-[#fcaf17] to-[#fdb913]';
    case 'gray':
      return 'bg-[#645b4c] opacity-60';
  }
};
</script>

<template>
  <div>
    <h3 class="text-[#645b4c] font-bold mb-8">Weekly Activity</h3>

    <div class="flex justify-between items-end h-32 px-2">
      <div
        v-for="item in data"
        :key="item.day"
        class="w-4 h-full bg-[#f4f2ee] rounded-full relative overflow-hidden"
      >
        <div
          class="absolute bottom-0 w-full transition-all duration-500 rounded-full"
          :class="getBarClass(item.type)"
          :style="{ height: item.height + '%' }"
        />
      </div>
    </div>

    <div
      class="flex justify-between mt-4 text-xs text-[#a39b8f] px-1 font-medium"
    >
      <span v-for="item in data" :key="item.day">{{ item.day }}</span>
    </div>
  </div>
</template>
