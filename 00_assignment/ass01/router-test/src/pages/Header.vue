<template>
  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <div class="container-fluid">
      <RouterLink class="navbar-brand font-weight-bold" to="/">
        <span class="text-">이날치</span>(LeeNalChi)
      </RouterLink>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#routerNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="routerNavbar">
        <ul class="navbar-nav">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/">홈</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/about">소개</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/members">멤버</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/videos">영상</RouterLink>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup></script>

<style scoped></style>
