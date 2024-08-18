package kr.co.lecstudy.myrestfulservice.controller;

import kr.co.lecstudy.myrestfulservice.bean.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;//view를 가지지않는 RESTDATA를 반환

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //메소드1
   @GetMapping(path = "/hello-world")
    public String helloworld(){
       return "Hello-world";
     }

    //메소드2
    //자바형태의 bean으로 전환
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloworldBean(){
        return new HelloWorldBean("Helloo world"); //json 형식(객체형태)으로 전달되는것
        //@ResController 를 추가했기 때문에 별도 ResponseBody에 helloworld 빈이라는 객체를 넣어 반환하지않아도됨.
    }

    //메소드3
    //@PathVaribale 어노테이션을 통해 가변변수와 매게변수가 같은 변수임을 선언(변수명은 달라도 되긴하지만 왠만하면 같게)
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloworldBeanPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

    //다국어 처리 방법
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name="Accept-Language", required=false)  Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }


    }

