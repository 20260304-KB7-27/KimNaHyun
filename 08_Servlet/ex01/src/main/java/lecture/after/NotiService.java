package lecture.after;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotiService {

    /*
    * 문제점
    * 1. 기존 클래스를 수정 -> NoticeService의 내부 코드 변화가 일어나야 함.
    * */

    private final EmailSender emailSender;

    @Autowired
    public NotiService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void notify(String message){
        emailSender.send(message);
    }
}
