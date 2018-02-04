package com.dp.exercise.service;

import com.dp.exercise.dao.UserDAO;
import com.dp.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Override
    public void create(User user) {
        userDao.save(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteFile(String rootPath, String relativePath) throws IOException {
        String[] temp = relativePath.split("/");
        String fileName = temp[temp.length - 1];
        String filePath = rootPath + "/" + fileName;
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }else{
            System.out.println("File not found");
        }
    }

}
