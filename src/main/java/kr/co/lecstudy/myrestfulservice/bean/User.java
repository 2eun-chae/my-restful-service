package kr.co.lecstudy.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성한다
public class User {
    private Integer id;
    private String name;
    private Date joinData;
}
