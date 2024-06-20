package kr.co.lecstudy.myrestfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
// 제일먼저 실행됨.
// value로 원하는 예외를 지정하고 이를 핸들링 할 수 있습니다. / 스프링 부트 애플리케이션에서 전역적으로 예외를 핸들링할 수 있게 해주는 어노테이션
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)//인자로 캐치하고 싶은 예외클래스를 등록
    //@ExceptionHandler : @Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.
    public final ResponseEntity<Object> handeAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); // 500에러코드발생
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handlerUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND); // 404에러코드발생
    }
}

