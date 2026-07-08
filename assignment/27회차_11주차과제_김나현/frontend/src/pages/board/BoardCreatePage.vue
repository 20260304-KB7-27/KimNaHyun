<script setup>
import boardApi from '@/api/boardApi';

import { computed, reactive, ref, onBeforeUnmount } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRoute, useRouter } from 'vue-router';

// TipTap 관련 임포트
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';

const auth = useAuthStore();
const router = useRouter();
const cr = useRoute();

const files = ref(null);

const article = reactive({
  writer: auth.username,
  title: '',
  content: '', // 에디터의 HTML 내용이 담길 곳입니다.
  files: null,
});

// TipTap 에디터 초기화
const editor = useEditor({
  content: article.content,
  extensions: [StarterKit],
  // 에디터 내용이 바뀔 때마다 article.content에 반영
  onUpdate: ({ editor }) => {
    article.content = editor.getHTML();
  },
  immediatelyRender: false, // SSR/안정성 오류 방지
});

// 컴포넌트 언마운트 시 에디터 메모리 해제
onBeforeUnmount(() => {
  if (editor.value) {
    editor.value.destroy();
  }
});

// 제목 또는 에디터 내용이 비어있으면 전송 제한 (필요에 따라 조건 수정 가능)
const disableSubmit = computed(() => !article.title || !article.content || article.content === '<p></p>');

const submit = async () => {
  if (!confirm('등록할까요?')) return;

  if (files.value.files.length > 0) {
    // 첨부파일 선택이 있는 경우
    article.files = files.value.files;
  }

  await boardApi.create(article);
  router.push('/board/list');
};
</script>

<template>
  <h1><i class="fa-regular fa-pen-to-square"></i> 글 작성</h1>

  <form @submit.prevent="submit">
    <div class="mb-3 mt-3">
      <label for="title" class="form-label"> 제목 </label>
      <input
        type="text"
        class="form-control"
        placeholder="제목"
        id="title"
        v-model="article.title"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="files" class="form-label"> 첨부파일 </label>
      <input
        type="file"
        class="form-control"
        placeholder="첨부파일"
        id="files"
        ref="files"
        multiple
      />
    </div>

    <div class="mb-3 mt-3">
      <label class="form-label"> 내용 </label>
      
      <div v-if="editor" class="editor-wrapper border rounded">
        <div class="editor-toolbar bg-light p-2 border-bottom d-flex gap-1">
          <button
            type="button"
            class="btn btn-sm"
            :class="editor.isActive('bold') ? 'btn-secondary' : 'btn-outline-secondary'"
            @click="editor.chain().focus().toggleBold().run()"
          >
            <i class="fa-solid fa-bold"></i>
          </button>
          <button
            type="button"
            class="btn btn-sm"
            :class="editor.isActive('italic') ? 'btn-secondary' : 'btn-outline-secondary'"
            @click="editor.chain().focus().toggleItalic().run()"
          >
            <i class="fa-solid fa-italic"></i>
          </button>
          <button
            type="button"
            class="btn btn-sm"
            :class="editor.isActive('heading', { level: 3 }) ? 'btn-secondary' : 'btn-outline-secondary'"
            @click="editor.chain().focus().toggleHeading({ level: 3 }).run()"
          >
            H3
          </button>
          <button
            type="button"
            class="btn btn-sm"
            :class="editor.isActive('bulletList') ? 'btn-secondary' : 'btn-outline-secondary'"
            @click="editor.chain().focus().toggleBulletList().run()"
          >
            <i class="fa-solid fa-list-ul"></i>
          </button>
        </div>

        <editor-content :editor="editor" class="tiptap-content p-3" />
      </div>
    </div>

    <div class="my-5 text-center">
      <button
        type="submit"
        class="btn btn-primary me-3"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-check"></i>
        확인
      </button>

      <router-link
        class="btn btn-primary"
        :to="{ name: 'board/list', query: cr.query }"
      >
        <i class="fa-solid fa-list"></i>
        목록
      </router-link>
    </div>
  </form>
</template>

<style scoped>
/* TipTap 입력창 포커스 및 최소 높이 스타일링 */
.tiptap-content :deep(.tiptap) {
  min-height: 300px;
  outline: none;
}

/* 리스트 스타일이 부트스트랩에 의해 뭉개지는 것 방지 */
.tiptap-content :deep(ul), .tiptap-content :deep(ol) {
  padding-left: 1.5rem;
}
</style>