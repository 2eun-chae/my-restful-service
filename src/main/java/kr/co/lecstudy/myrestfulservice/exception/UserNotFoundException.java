package kr.co.lecstudy.myrestfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{ //사용자가 핸들링할수있는 형태는 exception임.

    public UserNotFoundException(String message) {
        super(message);
        /*
        message : 500Internal Server Error
        이런경우 예외처리에 개인정보가 포함될수 있기때문에(postman-trace항목들)500에러로 사용하기보다는 변경해서 사용하는게 일반적.
          --> @ResponseStatus(HttpStatus.NOT_FOUND) 어노테이션 추가를 통해 404코드를 전송함.
          (서버에러가 아닌 클라이언트 에러기때문에 500에러보다는 400대 에러를 보내는게 맞음.)
        */


    }
}
