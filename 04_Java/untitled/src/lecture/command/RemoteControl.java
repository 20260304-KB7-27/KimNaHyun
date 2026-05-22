package lecture.command;

// Invoker : Command를 모아놓고 실행요청을 전달하는 객체
public class RemoteControl {

    private Command command;

    //setter
    public void setCommand(Command command) {
        this.command = command;
    }

    // 단순히 command를 실행만함.
    public void pressButton() {
        command.execute();
    }
}
