package com.springojwt22.spring.jwt22;

import com.springojwt22.spring.jwt22.model.Client;
import com.springojwt22.spring.jwt22.model.Role;
import com.springojwt22.spring.jwt22.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    CommandLineRunner run(ClientService clientService){
		return args->{
			clientService.saveRole(new Role(0,"ROLE_CLIENT"));
			clientService.saveRole(new Role(0,"ROLE_ADMIN"));


			clientService.saveClient(new Client(0,"Kishor","kishor.dnj@gmail.com","roykish","1234",new ArrayList<>()));
			clientService.saveClient(new Client(0,"Rahim","rahim.dnj@gmail.com","rahim","1234",new ArrayList<>()));

			clientService.addRoleToClient("roykish","ROLE_ADMIN");
			clientService.addRoleToClient("rahim","ROLE_CLIENT");
		};
	}
	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
