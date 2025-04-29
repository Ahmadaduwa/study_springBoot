// src/main/java/com/study/study/services/UsersService.java
package com.study.study.services;

import com.study.study.models.Auth.RegisterRequest;
import com.study.study.models.Auth.Role;
import com.study.study.models.Auth.UserPrincipal;
import com.study.study.models.Auth.Users;
import com.study.study.repository.RoleRepository;
import com.study.study.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username)
                );

        return new UserPrincipal(users);
    }

    public Users registerUser(RegisterRequest req) {
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        Users u = new Users(req.username(),
                new BCryptPasswordEncoder(12).encode(req.password()));
        u.setRoles(List.of(userRole));
        return usersRepository.save(u);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }
}
