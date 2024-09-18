package com.example.book_store_management_system.service;

import com.example.book_store_management_system.model.MyUser;
import com.example.book_store_management_system.repository.IUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if (user.getRoles() == null) {
            return new String[]{"USER"};
        }
        return user.getRoles().split(",");
    }

    @Transactional
    public void deleteUserByName(String username) {
        userRepository.deleteByUsername(username);
    }

}
