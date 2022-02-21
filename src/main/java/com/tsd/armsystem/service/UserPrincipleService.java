package com.tsd.armsystem.service;

import com.tsd.armsystem.auth.UserPrinciple;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserPrincipleService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nic) throws UsernameNotFoundException {
        User user = userRepository.findByNic(nic)
                .orElseThrow(()-> new UsernameNotFoundException("User "+ nic +" not found"));

        return new UserPrinciple(user);
    }
}
