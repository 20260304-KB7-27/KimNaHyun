package lecture.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {
    
//    @Autowired // 필드주입
//    private ClassA classA;


    private ClassA classA;

    @Autowired // 생성자 주입
    public ClassB(ClassA classA) {
        this.classA = classA;
    }

    public void doSomething(){
        System.out.println("Class A is Working");
        classA.doSomething();
    }

}

