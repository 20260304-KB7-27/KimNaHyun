<template>
  <div class="card">
    <div class="card-header">
      <slot name="header"></slot>
    </div>

    <div class="card-body">
      <slot></slot>
    </div>

    <!-- 부모가 #footer 슬롯에 내용을 넣었을 때만 렌더링 -->
    <div v-if="$slots.footer" class="card-footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.card {
  border: 1px solid black;
  width: 280px;
}

.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid black;
  font-weight: bold;
  background-color: lightgray;
}

.card-body {
  padding: 16px;
}

.card-footer {
  padding: 10px 16px;
  border-top: 1px solid black;
  display: flex;
  gap: 8px;
  justify-content: flex-end;
}
</style>
