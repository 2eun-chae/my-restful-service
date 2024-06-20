package kr.co.lecstudy.myrestfulservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor // 클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성한다
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 자동으로 생성한다.
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
