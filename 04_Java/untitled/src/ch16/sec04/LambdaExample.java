package ch16.sec04;

public class LambdaExample {
    public static void main(String[] args) {
        Person person = new Person();

        person.action((x, y)->{
            double res = x+y;
            return res;
        });
        person.action((x, y)-> {
            return sum(x,y);
        });

    }

    public static double sum(double x, double y){
        return (x+y);
    }
}
