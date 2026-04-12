// import axios from 'axios';
import { ref } from 'vue';
import axios from '@/util/axios';

/*
컴포저블 함수
훈련생 데이터를 관리하는 함수
*/

export const useStudent = () => {
  const students = ref([]);

  // 전체 조회
  const fetchAllStudents = async () => {
    // const response = await axios.get('/api/students');

    // baseURL 설정으로 /api 안 써도 됨!
    const response = await axios.get('/students');

    students.value = response.data;
  };

  // 추가
  // 조회, 삭제 등
  return { fetchAllStudents, students };
};
