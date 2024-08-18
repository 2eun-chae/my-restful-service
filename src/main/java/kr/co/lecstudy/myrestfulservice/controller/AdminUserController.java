package kr.co.lecstudy.myrestfulservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import kr.co.lecstudy.myrestfulservice.bean.AdminUser;
import kr.co.lecstudy.myrestfulservice.bean.AdminUserV2;
import kr.co.lecstudy.myrestfulservice.bean.User;
import kr.co.lecstudy.myrestfulservice.dao.UserDaoService;
import kr.co.lecstudy.myrestfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/admin") // admin이 url에 자동으로 붙음.
public class AdminUserController {
    private final UserDaoService service;

    public AdminUserController(UserDaoService service) {//화면에서 오른쪽버튼으로 Generate -> 생성자 만듬.
        this.service = service;
    }

    //@GetMapping(value = "/users/{id}",params = "version=1")
    //@GetMapping(value = "/users/{id}",headers = "X-API-VERSION=1")
    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv1+json")
    //MappingJacksonValue : 특정 조건에 맞는 필드만 JSON으로 직렬화하고, 나머지 필드는 제외
    public MappingJacksonValue retreiveUser(@PathVariable int id ){
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();

        //사용자가 없는 경우 예외처리 추가
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }else{
            //user.setName(user.getName()); 이 내용을 아래처럼 더 간단히 사용가능.
            BeanUtils.copyProperties(user, adminUser);
            //하나의 Java 빈(객체)에서 다른 Java 빈(객체)으로 속성값을 복사
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //필터를 관리

        //// MappingJacksonValue를 사용하여 필터 적용
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for (User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }

//1) url을 이용한 버전관리방법
  //@GetMapping("/v2/users/{id}")
//2) request parameter를 이용한 버전관리방법
  //@GetMapping(value = "/users/{id}",params = "version=2")
//3) header를 이용한 버전관리방법 - 일반 브라우저에서 실행불가
  //@GetMapping(value = "/users/{id}",headers = "X-API-VERSION=2")
//4) mime-type - 일반 브라우저에서 실행불가
@GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv2+json")
    //MappingJacksonValue : 특정 조건에 맞는 필드만 JSON으로 직렬화하고, 나머지 필드는 제외
    public MappingJacksonValue retreiveUserAdminV2(@PathVariable int id ){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        //// MappingJacksonValue를 사용하여 필터 적용
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }




}
