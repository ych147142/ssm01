package com.ych.dao;

import com.ych.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    /*@Select("select * from user")*///优先级比xml低
    public List<User> getLists (User user);
    public int add(User user);
    public int delete(int id);
    public int update(User user);
    public User getOne(int id);
    public User getOneByName(String username);
}
