package com.team1.iss.trial.services.impl;


import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.exception.UsernameIsExitedException;
import com.team1.iss.trial.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author itguang
 * @create 2018-01-02 16:07
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameIsExitedException("该用户不存在");
        }


        // 关于 UserDetailsService 和 User 对象之前的文章已经讲过.
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), emptyList());


    }
}
