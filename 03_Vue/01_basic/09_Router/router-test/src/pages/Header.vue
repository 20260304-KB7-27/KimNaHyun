<template>
  <div>
    <!-- 
    RouterLink
     - 클릭하면 페이지 이동
     - to 속성에 이동할 경로 지정 
     -->
    <RouterLink to="/">Home</RouterLink> |
    <RouterLink to="/members">MemberList</RouterLink> |
    <RouterLink to="/members/5">MemberInfo</RouterLink>
  </div>
</template>

<script setup></script>

<style scoped></style>
