package kr.co.lecstudy.myrestfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // HTTP 응답 상태 코드를 설정
public class UserNotFoundException extends RuntimeException{ //사용자가 핸들링할수있는 형태는 exception임.

    public UserNotFoundException(String message) {
        super(message);
        /*
        message : 500Internal Server Error
        --> @ResponseStatus(HttpStatus.NOT_FOUND) 어노테이션 추가를 통해 404코드를 전송함.
        (서버에러가 아닌 클라이언트에러기때문에 500에러보다는 400대 에러를 보내는게 맞음.)
        */


    }
}
