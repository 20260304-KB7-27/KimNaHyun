import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';

const app = createApp(App);

// PersistedState 플러그인 추가한 pinia
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

// pinia 추가
app.use(pinia);

app.mount('#app');
