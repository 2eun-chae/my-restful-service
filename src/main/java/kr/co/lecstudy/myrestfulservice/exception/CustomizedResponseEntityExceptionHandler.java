package kr.co.lecstudy.myrestfulservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice
/* [ControllerAdvice & RestControllerAdvice] - 전역적으로 예외를 처리할수 있다.
  1) ControllerAdvice
    - ControllerAdvice는 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해준다.
    - @ControllerAdvice 에는 @Component 어노테이션이 들어있어서 ControllerAdvice가 선언된 클래스는 스프링 빈으로 등록된다.
       그러므로 우리는 전역적으로 에러를 핸들링하는 클래스를 만들어 어노테이션을 붙여줌으로써 에러 처리를 위임할 수 있다.
    - 여러 ControllerAdvice가 있을 때 @Order 어노테이션으로 순서를 지정하지 않는다면 Spring은 ControllerAdvice를 임의의 순서로 에러를 처리할 수 있다.
       그러므로 한 프로젝트당 하나의 ControllerAdvice만 관리하는것이 좋다.

  2) RestControllerAdvice
    - @ControllerAdvice와 달리 @ResponseBody가 붙어 있어 JSON 형식으로 응답을 반환한다는 점에서 다르다.
*/
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)//인자로 캐치하고 싶은 예외클래스를 등록
    //@ExceptionHandler : @Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.

    public final ResponseEntity<Object> handeAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); // 500에러코드발생
        // ResponseEntity : HTTP 응답을 구성할 때 사용하는 클래스입니다.(HTTP 상태 코드, 헤더, 바디)
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handlerUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND); // 404에러코드발생
    }

    @Override
    //timestamp / message / details - 내가 설정한 message출력됨
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "validation failed!!!!", ex.getBindingResult().toString()); //에외객체안에 포함되어있는 데이터중에서 현재에 들어가있는 에러메세지까지 출력해준다.

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);// 400에러코드발생
    }
}

