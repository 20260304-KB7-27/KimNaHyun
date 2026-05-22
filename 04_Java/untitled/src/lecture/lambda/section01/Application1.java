package lecture.lambda.section01;

public class Application1 {

    public static void main(String[] args) {

        // 1. 인터페이스를 구현한 구현체를 만드는 방식
        Calculator c1 = new CalculatorImpl();

        System.out.println(c1.sum(1,5));

        // 2. 익명함수 (1회용 객체)
        Calculator c2 = new Calculator() {
            @Override
            public int sum(int a, int b) {
                return a+b;
            }
        };

        System.out.println(c2.sum(1, 5));

        // 3. 람다식
        Calculator c3 = (a, b) -> a + b;

    }
}
