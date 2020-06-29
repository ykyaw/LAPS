package com.team1.iss.trial.services.impl;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(String email, String password) {
        User user=userRepository.login(email,password);
        return user;
    }
}
