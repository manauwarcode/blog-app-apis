package com.blog.BackendBlogApplicationAPIs;

import com.blog.BackendBlogApplicationAPIs.config.AppConstants;
import com.blog.BackendBlogApplicationAPIs.entities.Role;
import com.blog.BackendBlogApplicationAPIs.repositories.RoleRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BackendBlogApplicationApIsApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BackendBlogApplicationApIsApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("abc"));

		try{
			Role role = new Role();
			role.setId(AppConstants.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_USER);
			role1.setName("ROLE_USER");

			List<Role> roles = List.of(role,role1);
			List<Role> savedRoles = this.roleRepo.saveAll(roles);

			savedRoles.forEach(r -> System.out.println(r.getName()));
		}catch (Exception e){

		}
	}



}
