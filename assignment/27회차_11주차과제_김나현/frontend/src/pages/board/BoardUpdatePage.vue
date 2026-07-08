<script setup>
import { ref, reactive, onBeforeUnmount } from "vue";
import { useRoute, useRouter } from "vue-router";
import boardApi from "@/api/boardApi";

// TipTap 관련 라이브러리 임포트
import { useEditor, EditorContent } from "@tiptap/vue-3";
import StarterKit from "@tiptap/starter-kit";

const cr = useRoute();
const router = useRouter();
const no = cr.params.no;

const article = reactive({}); // 수정할 게시글 데이터
const attachments = ref([]); // 첨부파일 목록
const orgArticle = ref({}); // 원본 게시글 데이터 (리셋용)
const files = ref(null);

// TipTap 에디터 초기화
const editor = useEditor({
  content: '', // 초기값은 비워두고 로드 후 채웁니다.
  extensions: [StarterKit],
  // 에디터 내용이 바뀔 때마다 article.content 갱신
  onUpdate: ({ editor }) => {
    article.content = editor.getHTML();
  },
  immediatelyRender: false,
});

// 컴포넌트 해제 시 에디터 메모리 해제
onBeforeUnmount(() => {
  if (editor.value) {
    editor.value.destroy();
  }
});

// 취소 버튼 - 상세 페이지로 복귀
const back = () => {
  router.push({
    name: "board/detail",
    params: { no },
    query: cr.query,
  });
};

// 첨부파일 삭제
const removeFile = async (fileNo, filename) => {
  if (!confirm(filename + "을 삭제할까요?")) return;

  await boardApi.deleteAttachment(fileNo);
  // 화면에서 해당 파일 제거
  const ix = attachments.value.findIndex((f) => f.no === fileNo);
  attachments.value.splice(ix, 1);
};

// 폼 제출, 수정 완료 후 상세 페이지로 이동
const submit = async () => {
  if (!confirm("수정할까요?")) return;

  if (files.value.files.length > 0) {
    article.files = files.value.files;
  }

  await boardApi.update(article);
  router.push({
    name: "board/detail",
    params: { no },
    query: cr.query,
  });
};

// 입력값 초기화 (리셋)
const reset = () => {
  article.no = orgArticle.value.no;
  article.title = orgArticle.value.title;
  article.writer = orgArticle.value.writer;
  article.content = orgArticle.value.content;
  
  // TipTap 에디터에도 기존 본문 내용을 다시 세팅해 줍니다.
  if (editor.value) {
    editor.value.commands.setContent(article.content || '');
  }
  console.log(article);
};

// 데이터 로드
const load = async () => {
  const data = await boardApi.get(no);
  orgArticle.value = { ...data }; // 원본 데이터 복사
  attachments.value = data.attaches;
  reset(); // 여기서 reset이 실행되며 에디터에 글이 들어갑니다.
};

load();
</script>

<template>
  <h1><i class="fa-regular fa-pen-to-square"></i> 글 수정</h1>

  <form @submit.prevent="submit">
    <div class="mb-3 mt-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        class="form-control"
        placeholder="제목"
        id="title"
        v-model="article.title"
      />
      <div class="invalid-feedback">제목은 필수 요소입니다.</div>
    </div>

    <div class="mb-3 mt-3">
      <label class="form-label">기존 첨부파일</label>
      <div v-for="file in attachments" :key="file.no" class="attach">
        <i class="fa-solid fa-paperclip"></i> {{ file.filename }}
        <i
          class="fa-solid fa-trash-can text-danger ms-2"
          @click="removeFile(file.no, file.filename)"
        ></i>
      </div>
    </div>

    <div class="mb-3 mt-3">
      <label for="files" class="form-label">첨부파일</label>
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
      <label class="form-label">내용</label>
      
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
      <button type="submit" class="btn btn-primary me-3">
        <i class="fa-solid fa-check"></i> 확인
      </button>
      <button type="button" class="btn btn-primary me-3" @click="reset">
        <i class="fa-solid fa-undo"></i> 취소
      </button>
      <button type="button" class="btn btn-primary" @click="back">
        <i class="fa-solid fa-arrow-left"></i> 돌아가기
      </button>
    </div>
  </form>
</template>

<style scoped>
.fa-trash-can {
  cursor: pointer;
}

/* TipTap 스타일링 */
.tiptap-content :deep(.tiptap) {
  min-height: 300px;
  outline: none;
}
.tiptap-content :deep(ul), .tiptap-content :deep(ol) {
  padding-left: 1.5rem;
}
</style>