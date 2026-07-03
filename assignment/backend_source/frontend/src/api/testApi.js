// import api from 'axios';
import api from '@/api';

const BASE_URL = '/api/member';
const headers = { 'Content-Type': 'multipart/form-data' }; // 파일 업로드용 헤더

export default {
  // 중복 체크
  async getList(){
    const { data } = await api.get(`${BASE_URL}/list`);
    return data;
  }
};