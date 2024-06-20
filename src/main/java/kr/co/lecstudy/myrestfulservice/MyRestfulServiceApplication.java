package kr.co.lecstudy.myrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication // 시작Class
public class MyRestfulServiceApplication {

	public static void main(String[] args) {

/*		전체 빈 목록 확인하는 법
		ApplicationContext ac = SpringApplication.run(MyRestfulServiceApplication.class, args);

		String[] allBeanNames = ac.getBeanDefinitionNames();
		for(String beanName : allBeanNames){
			System.out.println(beanName);
		}*/

		SpringApplication.run(MyRestfulServiceApplication.class, args);

	}

}
