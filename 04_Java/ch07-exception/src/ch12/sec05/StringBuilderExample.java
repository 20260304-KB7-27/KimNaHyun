package ch12.sec05;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class StringBuilderExample {
    public static void main(String[] args) {
//        Date date = new Date();
//        date.setMonth(1);  // 월이 0부터 시작
//        System.out.println(date);

        // java time 패키지 사용

        // LocalTime : 시간을 표현해주는 클래스
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);

        // LocalDate : 날짜를 표현해주는 클래스
        // 연도 월 일
        LocalDate dateNow = LocalDate.now();
        System.out.println(dateNow);
        LocalDate dateNow2 = LocalDate.of(2024, 4, 23);   // month 1부터 시작
        System.out.println(dateNow2);

        // ZoneDateTime
        // 날짜, 시간, 시간대를 함께 표현하는 클래스
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        // LocalDateTime
        // 날짜와 시간을 함께 표시하는 클래스
        LocalDateTime localDateNow = LocalDateTime.now();
        System.out.println(localDateNow);

    }

}
