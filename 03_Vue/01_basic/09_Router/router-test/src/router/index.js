import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
} from 'vue-router';
import Home from '@/pages/Home.vue';

/*
네비게이션 가드 (beforeEnter)
- false 반환하게 되면 접근을 차단함
*/
const membersIdGuard = (to, from) => {
  // /members인 경우 /members/:id에서만 /members/:id 로 접근 가능
  if (from.name !== 'members' && from.name !== 'members/id') {
    return false;
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Home,
    },
    {
      path: '/members',
      name: 'members',
      // 지연 로딩 : 해당 경로에 처음 접근할 때 컴포넌트를 불러옴
      // 초기 로딩 속도 개선
      component: () => import('@/pages/MemberList.vue'),
    },
    // {
    //   path: '/members/:id',
    //   component: () => import('@/pages/MemberInfo.vue'),
    // },
    {
      path: '/members/:id',
      name: 'members/id',
      component: () => import('@/pages/MemberInfo.vue'),
      //  members에서만 접근해야 상세정보를 볼수 있게 설정
      beforeEnter: membersIdGuard,
    },
    {
      /*
      중첩 라우팅
      - 부모 컴포넌트 안에 <RouterView>를 두고 children 배열로 자식 라우트를 정의
      - 자식 컴포넌트는 부모의 <RouterView> 위치에 랜더링
      */

      path: '/members-nested',
      component: () => import('@/pages/nested/MemberLayout.vue'),
      children: [
        {
          // 기본값
          path: '',
          name: 'members-nested',
          component: () => import('@/pages/nested/MemberDefault.vue'),
        },
        {
          // /members-nested/:id
          path: ':id',
          name: 'members-nested/detail',
          component: () => import('@/pages/nested/MemberDefault.vue'),
        },
      ],
    },
  ],
});

export default router;
