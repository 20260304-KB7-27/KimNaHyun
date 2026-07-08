<script setup>
import { useRoute, useRouter } from "vue-router";
import api from "@/api/boardApi";
import { ref } from "vue";
import moment from "moment";
import { useAuthStore } from "@/stores/auth";
import { downloadFile } from "@/utils/download";

const auth = useAuthStore();
const cr = useRoute();
const router = useRouter();

const no = cr.params.no; // URL 파라미터에서 게시글 번호 추출
const article = ref({});

// 목록으로 돌아가기
const back = () => {
  router.push({ name: "board/list", query: cr.query });
};

// 수정 페이지로 이동
const update = () => {
  router.push({ name: "board/update", params: { no: no }, query: cr.query });
};

// 게시글 삭제
const remove = async () => {
  if (!confirm("삭제할까요?")) return;
  await api.delete(no);
  router.push({ name: "board/list", query: cr.query });
};

// 파일 다운로드
const download = async (fileNo) => {
  const URL = "/api/board/download/" + fileNo;
  await downloadFile(URL);
};

// 게시글 상세 정보 로드
const load = async () => {
  article.value = await api.get(no);
  console.log("DETAIL", article.value);
};
load();
</script>

<template>
  <h1>{{ article.title }}</h1>

  <div class="my-3 d-flex justify-content-between">
    <div>
      <i class="fa-solid fa-user"></i>
      {{ article.writer }}
    </div>
    <div>
      <i class="fa-regular fa-clock"></i>
      {{ moment(article.updateDate).format("YYYY-MM-DD HH:mm") }}
    </div>
  </div>

  <hr />

  <div class="text-end">
    <div v-for="file in article.attaches" :key="file.no" class="attach">
      <span @click="download(file.no)">
        <i class="fa-solid fa-paperclip"></i>
        {{ file.filename }}
      </span>
    </div>
  </div>

  <div 
    class="content tiptap-rendered-content" 
    v-html="article.content"
  ></div>

  <div class="my-5">
    <button class="btn btn-primary me-2" @click="back">
      <i class="fa-solid fa-list"></i> 목록
    </button>

    <template v-if="auth.username == article.writer">
      <button class="btn btn-primary me-2" @click="update">
        <i class="fa-regular fa-pen-to-square"></i> 수정
      </button>
      <button class="btn btn-danger" @click="remove">
        <i class="fa-solid fa-trash-can"></i> 삭제
      </button>
    </template>
  </div>
</template>

<style scoped>
.attach {
  font-size: 0.8rem;
  cursor: pointer;
}

/* 중요: TipTap으로 생성된 HTML 서식 스타일 지정 */
.tiptap-rendered-content {
  min-height: 200px;
  line-height: 1.6;
  word-break: break-all;
}

/* 리스트 태그(ul, ol)의 부트스트랩 기본 여백 리셋 방지 */
.tiptap-rendered-content :deep(ul), 
.tiptap-rendered-content :deep(ol) {
  padding-left: 1.5rem;
  margin-bottom: 1rem;
}

/* 제목 태그 간격 조정 */
.tiptap-rendered-content :deep(h1),
.tiptap-rendered-content :deep(h2),
.tiptap-rendered-content :deep(h3) {
  margin-top: 1.5rem;
  margin-bottom: 0.5rem;
}
</style>