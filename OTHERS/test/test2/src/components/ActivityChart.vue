<script setup>
const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
});

const barClassMap = {
  gold1: 'bg-[#fcaf17]',
  gold2: 'bg-[#fdb913]',
  gradient: 'bg-gradient-to-t from-[#fcaf17] to-[#fdb913]',
  gray: 'bg-[#645b4c]/60',
};

const getBarClass = (type) => {
  return barClassMap[type] || 'bg-gray-300';
};
</script>

<template>
  <div>
    <h3 class="text-[#645b4c] font-semibold text-lg mb-6">Weekly Activity</h3>

    <!-- bars -->
    <div class="flex justify-between items-end h-36 px-2">
      <div
        v-for="(item, index) in data"
        :key="item.day || index"
        class="w-3 h-full rounded-full bg-[#f4f2ee] shadow-[inset_4px_4px_8px_#d9d5ce,inset_-4px_-4px_8px_#ffffff] relative overflow-hidden"
      >
        <div
          class="absolute bottom-0 w-full rounded-full shadow-[2px_2px_4px_#d9d5ce,-2px_-2px_4px_#ffffff] transition-all duration-300"
          :class="getBarClass(item.type)"
          :style="{ height: (item.height || 0) + '%' }"
        />
      </div>
    </div>

    <!-- labels -->
    <div
      class="flex justify-between mt-4 text-xs text-[#a39b8f] px-1 font-medium tracking-wide"
    >
      <span v-for="(item, index) in data" :key="item.day || index">
        {{ item.day }}
      </span>
    </div>
  </div>
</template>
