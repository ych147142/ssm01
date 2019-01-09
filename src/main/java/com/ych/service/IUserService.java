package com.ych.service;

import com.ych.pojo.User;

import java.util.List;

public interface IUserService {
    public List<User> getLists(User user);
    public int add(User user);
    public int delete(int id);
    public int update(User user);
    public User getOne(int id);
    public User getOneByName(String username);
}
