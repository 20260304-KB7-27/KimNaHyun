package org.scoula.travel.dao;

import org.apache.ibatis.session.SqlSession;
import org.scoula.travel.database.MyBatisConfig;
import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/*
* SqlSession 메서드
* - selectOne(id) : 결과가 정확히 1건인 select
* - selectList(id) : 결과가 0건 이상인 (응답이 List 형태로 나옴)
* - insert(id, param) : return이 영향받은 행의 수
* - update(id, param) : return이 영향받은 행의 수
* - delete(id, param) : return이 영향받은 행의 수
*
* id에 NAMESPACE 구분 넣어주면 해당 쿼리 실행.
*
* Transaction
* - commit() : 변경사항을 DB에 반영
* - rollback() : 변경사항 취소
*
* */

public class TravelDaoImpl implements TravelDao{

    @Override
    public int getTotalCount() {
        return MyBatisConfig.getSqlSession()
                .selectOne(NAMESPACE + "getTotalCount"); // xml 상의 주소가 됨.
    }

    @Override
    public List<TravelVO> getTravels(int start, int end) {
        return List.of();
    }

    @Override
    public List<String> getDistricts() {
        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getDistricts");
    }

    @Override
    public List<TravelVO> getTravelsByDistrict(String district) {
        return List.of();
    }

    @Override
    public Optional<TravelVO> getTravel(Long no) {
        return Optional.empty();
    }

    /*
    * 트랜젝션 처리 메서드
    * */
    private void transaction(Consumer<SqlSession> action){
        try(SqlSession session = MyBatisConfig.getSqlSession()){
            action.accept(session);
            session.commit();  // db에 반영
        }
    }

    @Override
    public void insert(TravelVO travel) {
        transaction(session -> session.insert(NAMESPACE + "insert", travel));
    }

    @Override
    public void update(TravelVO travel) {

    }

    @Override
    public void remove(Long no) {

    }
}
