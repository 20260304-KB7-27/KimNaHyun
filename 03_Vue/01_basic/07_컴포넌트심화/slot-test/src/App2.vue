<template>
  <div>
    <BaseCard class="card-row">
      <template #header> 사용자 프로필 </template>
      <!-- #default : template 없이 작성해도 기본 슬롯으로 들어감 -->
      <p><strong>이름 : </strong>홍길동</p>
      <p><strong>직무 : </strong>프론트개발자</p>
      <p><strong>경력 : </strong>3년</p>

      <template #footer>
        <button>취소</button>
        <button>저장</button>
      </template>
    </BaseCard>
    <BaseCard>
      <template #header> 공지사항 </template>
      <!-- #default : template 없이 작성해도 기본 슬롯으로 들어감 -->
      <p>
        3월 25일 이전에 풀던 example 컴포넌트로 분리해서 만드는거 완성하세요:
      </p>
      <p><strong>직무 : </strong>프론트개발자</p>
      <p><strong>경력 : </strong>3년</p>
    </BaseCard>
  </div>
</template>

<script setup>
import BaseCard from './components/example2/BaseCard.vue';
</script>

<style lang="scss" scoped>
.card-row {
  overflow: hidden;
  float: left;
}
</style>
