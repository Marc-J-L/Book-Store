package com.example.book_store_management_system;

import com.example.book_store_management_system.model.MyUser;
import com.example.book_store_management_system.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BookStoreManagementSystemApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final IUserRepository userRepository;

    public BookStoreManagementSystemApplication(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookStoreManagementSystemApplication.class, args);
	}

	//loading some sample users with different roles
//	@Bean
//	public CommandLineRunner loadData() {
//		return args -> {
//
//			//create a user as a customer
//			MyUser user = new MyUser();
//			user.setUsername("user");
//			user.setPassword(passwordEncoder.encode("test123"));
//			user.setRoles("USER");
//			userRepository.save(user);
//
//
//			// Create admin with admin roles
//			MyUser admin = new MyUser();
//			admin.setUsername("admin");
//			admin.setPassword(passwordEncoder.encode("test123"));
//			admin.setRoles("ADMIN");
//			userRepository.save(admin);
//
//		};
//	}
}
