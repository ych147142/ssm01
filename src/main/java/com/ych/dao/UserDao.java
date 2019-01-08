package com.ych.dao;

import com.ych.pojo.User;

import java.util.List;

public interface UserDao {
    public List<User> getLists ();
    public int add(User user);
    public int delete(int id);
    public int update(User user);
    public User getOne(int id);
    public User getOneByName(String username);
}
