<template>
  <div class="modal">
    <div class="box">처리중</div>
  </div>
</template>

<script setup></script>

<style scoped>
.modal {
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(2px);
}

.box {
  background-color: white;
  padding: 30px 50px;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 250px;
  font-size: 1.1rem;
  font-weight: 600;
  color: #333;
}
</style>
