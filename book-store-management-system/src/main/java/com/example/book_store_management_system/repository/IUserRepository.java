package com.example.book_store_management_system.repository;

import com.example.book_store_management_system.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    
    void deleteByUsername(String username);

}
