package com.dp.exercise.service;

import com.dp.exercise.dao.UserDAO;
import com.dp.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public List<User> getListUser() {
        List<User> listUsers = userDao.list();
        return listUsers;
    }
}
