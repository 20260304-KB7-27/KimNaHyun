<script setup>
import { computed } from 'vue';

const props = defineProps({
  title: { type: String, default: 'Activity Trend' },
  data: { type: Array, required: true, default: () => [] },
  maxY: { type: Number, default: null },
});

const viewboxWidth = 500;
const viewboxHeight = 200;
const padding = 20;

const points = computed(() => {
  if (!props.data || props.data.length < 2) return [];

  const dataMax = Math.max(...props.data.map((d) => d.value));
  const effectiveMaxY = props.maxY || dataMax || 100;

  const usableWidth = viewboxWidth - padding * 2;
  const usableHeight = viewboxHeight - padding * 2;

  return props.data.map((d, i) => {
    const x = padding + (i / (props.data.length - 1)) * usableWidth;
    const y =
      viewboxHeight - padding - (d.value / effectiveMaxY) * usableHeight;

    return { x, y, value: d.value, label: d.label };
  });
});

const polylinePoints = computed(() => {
  return points.value.map((p) => `${p.x},${p.y}`).join(' ');
});
</script>

<template>
  <div class="flex flex-col">
    <h2 class="text-[#645b4c] font-semibold text-lg mb-6">
      {{ title }}
    </h2>

    <div class="w-full h-56 p-2">
      <svg
        :viewBox="`0 0 ${viewboxWidth} ${viewboxHeight}`"
        class="w-full h-full"
        preserveAspectRatio="none"
      >
        <defs>
          <linearGradient id="lineGrad" x1="0%" y1="0%" x2="100%" y2="0%">
            <stop offset="0%" stop-color="#fcaf17" />
            <stop offset="100%" stop-color="#fdb913" />
          </linearGradient>
        </defs>

        <!-- 라인 -->
        <polyline
          fill="none"
          stroke="url(#lineGrad)"
          stroke-width="3"
          stroke-linecap="round"
          stroke-linejoin="round"
          :points="polylinePoints"
        />

        <!-- 점 -->
        <g v-for="(point, i) in points" :key="i">
          <circle
            :cx="point.x"
            :cy="point.y"
            r="4"
            class="fill-[#f4f2ee] stroke-[#e0ddd7] stroke-2"
          />
        </g>
      </svg>
    </div>

    <!-- 라벨 -->
    <div
      class="flex justify-between text-xs text-[#a39b8f] font-medium tracking-wider uppercase px-2 mt-2"
    >
      <span v-for="d in data" :key="d.label">{{ d.label }}</span>
    </div>
  </div>
</template>
