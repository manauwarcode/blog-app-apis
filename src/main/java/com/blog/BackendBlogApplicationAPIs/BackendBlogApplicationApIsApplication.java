package com.blog.BackendBlogApplicationAPIs;

import com.blog.BackendBlogApplicationAPIs.config.AppConstants;
import com.blog.BackendBlogApplicationAPIs.entities.Role;
import com.blog.BackendBlogApplicationAPIs.repositories.RoleRepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Blog Application API's",version = "1.0",
		contact = @Contact(name = "Manauwar Ansari",email = "manauwar.zansari@gmail.com")))
@SecurityScheme(name = "Authorization" , scheme = "basic",type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER)
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
			this.roleRepo.save(role);

			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_USER);
			role1.setName("ROLE_USER");
			this.roleRepo.save(role1);

			/*Role role2 = new Role();
			role2.setId(AppConstants.ROLE_CONSULTANT);
			role2.setName("ROLE_CONSULTANT");
			this.roleRepo.save(role2);*/

		}catch (Exception e){

		}
	}



}
