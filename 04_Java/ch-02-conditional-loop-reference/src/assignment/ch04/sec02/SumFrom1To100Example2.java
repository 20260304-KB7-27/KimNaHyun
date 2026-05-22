package assignment.ch04.sec02;

public class SumFrom1To100Example2 {
    public static void main(String[] args) {
        int sum = 0;
        int i=1;

        while (i<101){
            sum += i;
            i++;
        }

        System.out.println("1~100 합 : " + sum);
    }

}
