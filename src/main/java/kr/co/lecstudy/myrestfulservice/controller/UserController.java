package kr.co.lecstudy.myrestfulservice.controller;

import com.sun.tools.jconsole.JConsoleContext;
import jakarta.validation.Valid;
import kr.co.lecstudy.myrestfulservice.bean.User;
import kr.co.lecstudy.myrestfulservice.dao.UserDaoService;
import kr.co.lecstudy.myrestfulservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
//https://cheershennah.tistory.com/179

@RestController
/*
[Controller & ResController]
@RestController는 리턴값에 자동으로 @ResponseBody가 붙게되어 별도 어노테이션을 명시해주지 않아도 자바 객체를 HTTP 응답 본문의 객체로 변환하여 전달 된다.
@Controller인 경우에 바디를 자바객체로 받기 위해서는 @ResponseBody 어노테이션을 반드시 명시해주어야한다.

[@RequestBody / @ResponseBody]
    - 클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로 보내면,
     서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.
    - 서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송한다.
 */
public class UserController {
    private final UserDaoService service;

    public UserController(UserDaoService service) {//화면에서 오른쪽버튼으로 Generate -> 생성자 만듬.
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retreiveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retreiveUser(@PathVariable int id ){ //가변데이터를 매게변수로 사용할경우 @PathVariable 어노테이션 선언
        User user = service.findOne(id);

        //사용자가 없는 경우 예외처리 추가
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        return user;
    }

    @PostMapping("/users")
    //ResponseEntity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다. 따라서 HttpStatus, HttpHeaders, HttpBody를 포함한다.
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        //postman에서 신규데이터 생성시 상태코드 변경과, header의 location을 보게되면 신규생성된 데이터의 uri를 볼수있고 해당 uri로 상세정보를 확인할수 있도록 만들수 있다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//현재 request정보로부터 uri를 생성
                .path("/{id}") // 요청url값
                .buildAndExpand(savedUser.getId()) // 뒤에 저장되어있는 저장되어있는 값의 id를 가져와서
                .toUri(); // uri로 만들겠다.

        return ResponseEntity.created(location).build();
        //.created() 메소드는 반환 객체에 대한 response타입을 결정하는데 201코드를 반환한다.
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        //삭제된거기때문에 user데이터를 반환할필요없음.(ResponseEntity<User> - X)
        User deleteUser = service.deleteById(id);

        if(deleteUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
        return ResponseEntity.noContent().build(); //204No Content 반환
    }
    
    @PostMapping("/users/{id}")
    public ResponseEntity ModifyUser(@PathVariable int id,@RequestBody User user){

        User modUser = service.ModById(id, user);

        user.setJoinDate(modUser.getJoinDate());
        user.setName(modUser.getName());

        service.save(user);

        return null;
        }



}
