package com.orangelabs.RestaurantDemo.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.UserEntity;

@Service
public class UserService implements UserDetailsService {

    private UserDaoInterface userDao;

    public UserService(UserDaoInterface userDaoToInject) {
        this.userDao = userDaoToInject;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(), emptyList());
    }

}
