<script setup>
import CheckboxItem from './components/CheckboxItem.vue';
import HelloWorld from './components/HelloWorld.vue';
import TheWelcome from './components/TheWelcome.vue';

// export default {
//   name: 'App',
//   components: { CheckboxItem },
// };
</script>

<template>
  <div>
    <h2>App 컴포넌트</h2>
    <hr />
    <ul>
      <CheckboxItem />
      <CheckboxItem />
      <CheckboxItem />
      <CheckboxItem />
    </ul>
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
