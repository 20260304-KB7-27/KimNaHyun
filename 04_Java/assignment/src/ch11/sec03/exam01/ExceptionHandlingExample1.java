package ch11.sec03.exam01;

public class ExceptionHandlingExample1 {
    public static void printLength(String data) {
        try{
            int result = data.length();
            System.out.println("문자 수: " + result);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("[프로그램 시작]\n");
        printLength("ThisIsJava");
        printLength(null);
        System.out.println("[프로그램 종료]");
    }
}

/*
* data가 null인 경우의 처리가 되어있지 않아,
* data가 null이면 존재하지 않는 객체의 메서드 data.length()를 호출하려고 하여 오류가 납니다
* 이로 인해 NullPointerException이 발생합니다.
*
*
* 수정 전 실행 결과 :
* Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "data" is null
	at ass3.ExceptionHandlingExample1.printLength(ExceptionHandlingExample1.java:5)
	at ass3.ExceptionHandlingExample1.main(ExceptionHandlingExample1.java:12)
*/