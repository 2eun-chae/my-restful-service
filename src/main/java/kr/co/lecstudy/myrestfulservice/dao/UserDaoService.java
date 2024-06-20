package kr.co.lecstudy.myrestfulservice.dao;

import kr.co.lecstudy.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component // 스프링이 기동이 돠면 UserDaoService가 스프링 컨텍트 메모리에 즉, 빈으로 등록됨
/*
@Component 어노테이션을 이용하면 Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있다.
빈 등록자체를 빈 클래스 자체에다가 할 수 있다는 의미이다.

@Component 어노테이션은 기본적으로 타입기반의 자동주입 어노테이션이다.
@Autowired, @Resource와 비슷한 기능을 수행한다고 할 수 있겠다.
* */

public class UserDaoService {
    private static List<User> Users = new ArrayList<>();// 관계형 db에 저장하지 않을거기때문에 임시 리스트 생성함.

    private static int userCount = 3;

    static {
        Users.add(new User(1, "Elii", new Date()));
        Users.add(new User(2, "Amy", new Date()));
        Users.add(new User(3, "Alii", new Date()));
    }

    public List<User> findAll() {
        return Users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        if(user.getJoinData() == null){
            user.setJoinData(new Date());
        }

        Users.add(user);

        return user;
    }

    public User findOne(int id){
        for(User a : Users){
            if(a.getId() == id){
                return a;
            }
        }

        return null;

    }
}
