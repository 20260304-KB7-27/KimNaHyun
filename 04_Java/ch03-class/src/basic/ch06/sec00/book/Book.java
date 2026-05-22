package basic.ch06.sec00.book;

public class Book {
    // 필드
        String author;
        String title;
        String coverType;
        int pageCount;

    // 생성자
    public Book() {
    }

    public Book(String author, String title, String coverType, int pageCount) {
        this.author = author;
        this.title = title;
        this.coverType = coverType;
        this.pageCount = pageCount;
    }

    public Book(String author, String title) {
//        this() : 생성자 최상단에 와야함
        this(author, title, "soft cover", 12);
    }

    // 메서드


}
