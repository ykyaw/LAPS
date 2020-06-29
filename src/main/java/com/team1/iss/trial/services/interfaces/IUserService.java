package com.team1.iss.trial.services.interfaces;

import com.team1.iss.trial.domain.User;

public interface IUserService {

    User login(String email,String password);
}
