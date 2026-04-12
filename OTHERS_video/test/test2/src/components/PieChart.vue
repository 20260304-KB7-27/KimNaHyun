<script setup>
import { computed } from 'vue';

const props = defineProps({
  title: {
    type: String,
    default: 'Progress',
  },
  value: {
    type: Number,
    required: true,
  },
  total: {
    type: Number,
    default: 100,
  },
  unit: {
    type: String,
    default: '%',
  },
});

const percentage = computed(() => {
  if (props.total === 0) return 0;
  return Math.min(100, Math.round((props.value / props.total) * 100));
});

const radius = 50;
const circumference = 2 * Math.PI * radius;

const strokeDashoffset = computed(() => {
  return circumference - (circumference * percentage.value) / 100;
});
</script>
<template>
  <div class="flex flex-col items-center">
    <h2 class="text-[#645b4c] font-bold text-xl mb-6 self-start ml-2">
      {{ title }}
    </h2>

    <div class="relative w-56 h-56 flex items-center justify-center mb-6">
      <div
        class="absolute inset-0 rounded-full bg-[#f4f2ee] shadow-[inset_10px_10px_20px_#d9d5ce,inset_-10px_-10px_20px_#ffffff]"
      ></div>

      <svg
        viewBox="0 0 120 120"
        class="w-full h-full -rotate-90 transform relative z-10"
      >
        <defs>
          <linearGradient id="yellowGrad" x1="0%" y1="0%" x2="100%" y2="0%">
            <stop offset="0%" stop-color="#fcaf17" />
            <stop offset="100%" stop-color="#fdb913" />
          </linearGradient>
        </defs>

        <circle
          cx="60"
          cy="60"
          :r="radius"
          fill="none"
          stroke="url(#yellowGrad)"
          stroke-width="12"
          stroke-linecap="round"
          class="transition-all duration-500 ease-out"
          :style="{
            strokeDasharray: circumference,
            strokeDashoffset: strokeDashoffset,
          }"
        />
      </svg>

      <div class="absolute flex flex-col items-center z-20">
        <p class="text-4xl font-extrabold text-[#645b4c] tabular-nums">
          {{ value }}<span class="text-xl font-bold ml-0.5">{{ unit }}</span>
        </p>
        <p class="text-xs text-[#a39b8f] tracking-widest uppercase mt-1">
          of {{ total }} {{ unit }}
        </p>
      </div>
    </div>
  </div>
</template>
