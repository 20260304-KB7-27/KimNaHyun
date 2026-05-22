package lecture.command;

public class Application {

    /*
    * 커맨드 패턴 (Command)
    * - 요청을 객체로 만들어서 실행,취소,재사용를 가능하게 하는 패턴
    *
    * Command           : 실행 명령 (인터페이스)
    * ConcreteCommand   : Command의 구현체
    * Receiver          : 실제 동작을 수행하는 객체
    * Invoker           : Command를 보관,실행하는 객체
    * Client            : 실행하는곳
    * */
    public static void main(String[] args) {

        // command의 실제 동작을 수행하는 객체
        Light light = new Light("거실");

        // Command의 구현체를 생성해서 Reciever 주입
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOFFCommand(light);

        // Invoker : 명령실행기
        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOff); // 명령등록

        System.out.println("거실불 = " + light);
        remote.pressButton(); // 명령 실행
        System.out.println("거실불 = " + light);

        Light light2 = new Light("화장실");

        remote.setCommand(new LightOnCommand(light2));

        System.out.println("화장실 = " + light2);
        remote.pressButton(); // 명령 실행
        System.out.println("화장실 = " + light2);

        // 결합도를 낮춤 (RemoteControl은 Light를 몰라도됨)
        // 명령(메서드)를 바꾸기 쉽게
        // 다른 객체들에서도 동일한 방식으로 제어 가능


    }
}
