package kr.co.lecstudy.myrestfulservice.bean;

import lombok.AllArgsConstructor; // 현재 가지고있는 프로퍼티즈를 가지고있는 생성자를 추가해주세요
import lombok.Data; // 어노테이션 선언시 자동으로 setter,getter메소드가 생성됨 -> 확인방법 : 왼쪽 structure 에서 메소드 확인가능

@Data
@AllArgsConstructor// 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성한다
public class HelloWorldBean {
    private String message;

//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
