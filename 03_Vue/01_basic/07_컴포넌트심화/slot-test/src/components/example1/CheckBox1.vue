<template>
  <div>
    <input type="checkbox" :value="id" :checked="checked" @change="onChange" />
    <span
      v-if="checked === true"
      style="color: blue; text-decoration: underline"
    >
      <i>{{ label }}</i>
    </span>
    <span v-else style="color: gray">
      <i>{{ label }}</i>
    </span>
  </div>
</template>

<script setup>
const props = defineProps(['id', 'checked', 'label']);
const emit = defineEmits(['check-changed']);
function onChange(event) {
  emit('check-changed', { id: props.id, checked: event.target.checked });
}
</script>

<style lang="scss" scoped></style>
