import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    // 프록시 설정 : CORS 문제를 우회하기 위해 요청을 다른 서버로 중계하는 역할
    proxy: {
      '/api': {
        // target : 실제 요청을 보낼 백엔드 서버 주서
        target: 'http://localhost:3000',

        // 요청 헤더의 Origin을 target주소로 변경
        changeOrigin: true,

        // /api 경로 제거 후 전달
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
});
