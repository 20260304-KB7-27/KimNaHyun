<template>
  <div>
    <InputName v-model:name="searchName" />
    <hr />
    <h3>검색어 : {{ searchName }}</h3>
  </div>
</template>

<script>
import InputName from './components/InputName.vue';

export default {
  name: 'App',
  components: { InputName },
  data() {
    return {
      searchName: 'John',
    };
  },
};
</script>
