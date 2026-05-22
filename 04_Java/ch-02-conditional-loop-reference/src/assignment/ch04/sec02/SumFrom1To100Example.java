package assignment.ch04.sec02;

public class SumFrom1To100Example {
    public static void main(String[] args) {
        int sum = 0;
        for (int i=1; i<101; i++){
            sum += i;
        }
        System.out.println("1~100 합 : " + sum);
    }

}
