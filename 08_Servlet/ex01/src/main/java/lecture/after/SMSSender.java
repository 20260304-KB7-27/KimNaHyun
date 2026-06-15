package lecture.after;
import org.springframework.stereotype.Component;

@Component
public class SMSSender extends EmailSender {  // 다형성을 위한 상속
    // 문자 보내는 기능
    @Override
    public void send(String message){
        System.out.println("SMS 발송 : " + message);
    }
}


