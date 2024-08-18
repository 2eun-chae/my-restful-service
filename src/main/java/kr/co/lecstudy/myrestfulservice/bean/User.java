package kr.co.lecstudy.myrestfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Data // getter/sette 자동생성카
@AllArgsConstructor // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성한다
@JsonIgnoreProperties(value = {"password","ssn"})
public class User {
    private Integer id;

    @Size(min = 2 , message = "Name은 2글자 이상 입력해주세요.")
    private String name;

    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
    private Date joinDate;

   // @JsonIgnore
    private String password;
   // @JsonIgnore
    private String ssn;

    public void ChangeUser(String name, Date joinDate){
        this.name = name;
        this.joinDate = joinDate;
    }
}
