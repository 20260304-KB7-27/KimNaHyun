package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class JDBCTests {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("MySQL JDBC 드라이버 로딩 성공!");
        } catch (Exception e) {
            log.error("MySQL JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("MySQL 데이터베이스 연결 테스트")
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3306/scoula_db";
        String username = "scoula";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            log.info("데이터베이스 연결 성공!");
            log.info("연결된 객체 정보: " + con);

            assertNotNull(con, "Connection 객체가 null입니다.");

        } catch (Exception e) {
            log.error("데이터베이스 연결에 실패했습니다.");
            log.error("에러 원인: " + e.getMessage());
            fail(e.getMessage());
        }
    }
}