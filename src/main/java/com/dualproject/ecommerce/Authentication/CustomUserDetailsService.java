package com.dualproject.ecommerce.Authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dualproject.ecommerce.Models.User;
import com.dualproject.ecommerce.Services.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return new CustomUserDetails(user.get());
    }
    
}
