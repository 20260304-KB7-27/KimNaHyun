package basic.lecture.encapsulation;

public class Database {
    /*
    싱글톤 패턴
    - 어플리케이션에서 하나의 객체만 생성되도록 보장하는 디자인 패턴
    - 동일한 인스턴스를 공유해서 사용
    */


    private String connection = "MYSQL";

    // 객체를 만들지 않아도 사용 가능
    private static Database database = new Database();
}


