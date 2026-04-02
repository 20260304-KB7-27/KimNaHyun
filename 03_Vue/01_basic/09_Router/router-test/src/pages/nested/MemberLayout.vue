<template>
  <RouterLink
    v-for="m in members"
    :key="m.id"
    ,
    :to="{ name: 'members-nested/detail', params: { id: m.id } }"
    class="m-2"
  >
    {{ m.name }}
  </RouterLink>
  <!-- 
   자식 라우트 (아래 둘 중 하나가 RouterView에 렌더링 됨 )
   - MemberDefault.vue
   - MemberDetail.vue

   -->
  <RouterView></RouterView>
</template>

<script setup>
import members from '@/members.json';
</script>

<style scoped></style>
