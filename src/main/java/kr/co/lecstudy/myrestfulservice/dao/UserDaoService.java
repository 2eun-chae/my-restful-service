package kr.co.lecstudy.myrestfulservice.dao;

import kr.co.lecstudy.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component // Spring Framework에서 빈(bean) 및 의존성 주입을 처리하는 데 사용되는 어노테이션
/*
Spring Framework에서 클래스의 인스턴스를 스프링 컨테이너가 관리하는 빈(bean)으로 등록하는 데 사용하는 어노테이션입니다.
이 어노테이션을 통해 스프링은 해당 클래스를 자동으로 스캔하고,
인스턴스를 생성하여 의존성 주입 등의 기능을 통해 애플리케이션에서 사용할 수 있도록 합니다.

@Component 어노테이션은 기본적으로 타입기반의 자동주입 어노테이션이다.
   @Autowired - 생성자, 필드, 세터 메서드에 붙여서 의존성을 자동으로 주입할 수 있습니다.
   @Resource  - 이름(name)을 기반으로 빈을 주입합니다. / 보통의 빈이름은 필드이름으로 설정됨.
*/

public class UserDaoService {
    private static final List<User> Users = new ArrayList<>(); // 관계형 db에 저장하지 않을거기때문에 임시 리스트 생성함.

    private static int userCount = 3;

    static {
        Users.add(new User(1, "Kenneth", new Date(), "test1", "111111-1111111"));
        Users.add(new User(2, "Alice", new Date(), "test2", "222222-2222222"));
        Users.add(new User(3, "Elena", new Date(), "test3", "333333-3333333"));
    }

    public List<User> findAll() {
        return Users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
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

    public User deleteById(int id) {
        Iterator<User> iterator = Users.iterator(); //컬렉션의 요소들을 읽어오는 방법

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    public User ModById(int id,User request){
        for(User a : Users){
            if(a.getId() == id){
                a.ChangeUser(request.getName(),request.getJoinDate());
                return a;
            }
        }
        return null;
    }
}
