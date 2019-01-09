package com.ych.service;

import com.ych.dao.UserDao;
import com.ych.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Repository
@Component
@Controller
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao dao;
    @Override
    public List<User> getLists(User user) {
        return dao.getLists(user);
    }

    @Override
    public int add(User user) {
        return dao.add(user);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public User getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public User getOneByName(String username) {
        return dao.getOneByName(username);
    }
}
