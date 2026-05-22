package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Application {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethod = Service.class.getDeclaredMethods();
        Method method = declaredMethod[0];

        PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);

        if (printAnnotation != null) {
            System.out.println(printAnnotation);
            int number = printAnnotation.number();
            String value = printAnnotation.value();

            for (int i=0; i<number; i++){
                System.out.print(value);
            }

            System.out.println();
        }
    method.invoke(new Service());
    }
}
