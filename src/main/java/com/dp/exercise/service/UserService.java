package com.dp.exercise.service;

import com.dp.exercise.model.User;
import com.sun.jersey.core.header.FormDataContentDisposition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public interface UserService {

    public List<User> getListUser();

    public void create(User user);

    public User findById(int id);

    public void delete(User user);

    public void deleteFile(String rootPath, String relativePath) throws IOException;
}
