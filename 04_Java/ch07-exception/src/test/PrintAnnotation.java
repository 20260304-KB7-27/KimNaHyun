package test;
import java.lang.annotation.*;
// 사용자 정의 어노테이션

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
 * Annotation : @ 기호로 시작하는 "메모 태그"
 * - 클래스, 메서드, 필드 등에 붙여서 추가 정보를 표시한다.
 *
 * @Target
 * - TYPE : 클래스, 인터페이스
 * - FIELD : 멤버 변수
 * - METHOD : 메서드
 *
 * @Retention
 * - SOURCE : 컴파일하면 사라짐
 * - CLASS :  .Class파일까지만 유지 실행 중엔 사라짐
 * - RUNTIME : 실행중에도 유지
 *
 * 어노테이션의 동작방식
 * 1. 컴파일단계 : 컴파일러가 확인. ( @Overide )
 * 2. 런타임 +  Reflection : 런타임중에 읽어서 동작 ( @Autowired, @Controller ..)
 * 3. Annotation Processor : 컴파일시 코드를 생성 ( @Getter, @Setter .. )
 * */

@Target({ElementType.METHOD}) // 어노테이션을 붙일 수 있는 범위
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보가 언제까지 유지되는지
public @interface PrintAnnotation {

    // 속성 : 어노테이션에 넘길 수 있는 값
    // default가 붙어있는 속성은 값을 생략 가능

    String value() default "-"; // 기본값 "-"
    int number() default 15; // 기본값 15

}