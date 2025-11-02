package com.bsse5a.EcommerceWeb.configurations;

import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByEmail("hassam@admin.com").isEmpty()){
                UserEntity admin = new UserEntity();
                admin.setEmail("hassam@admin.com");
                admin.setName("hassam");
                admin.setPassword(passwordEncoder.encode("admin@123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin created!");
            }else{
                System.out.println("Admin : hassam exists");
            }
        };
    }
}
