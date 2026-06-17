package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("RootConfig를 통한 DataSource 빈 생성 및 주입 테스트")
    public void testDataSourceBean() {
        log.info("주입된 DataSource 객체 검증");
        assertNotNull(dataSource, "DataSource 빈이 스프링 컨텍스트에 등록되지 않았습니다.");
        log.info("DataSource 객체: " + dataSource);
    }

    @Test
    @DisplayName("HikariCP 커넥션 풀을 통한 데이터베이스 연결 테스트")
    public void testConnection() {
        try (Connection con = dataSource.getConnection()) {

            log.info("HikariCP 커넥션 풀 연결 성공!");
            log.info("획득한 Connection 객체: " + con);

            assertNotNull(con, "Connection 객체가 null입니다.");

        } catch (Exception e) {
            log.error("HikariCP 커넥션 획득 실패!");
            log.error("에러 원인: " + e.getMessage());
            fail(e.getMessage());
        }
    }
}