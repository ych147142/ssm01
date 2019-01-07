package com.ych.web;

import com.ych.pojo.User;
import com.ych.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebTest {
    @Autowired
    IUserService service ;
    @RequestMapping("/list.do")
    public String list(ModelMap map){
        List<User> lists = service.getLists();
        map.put("lists",lists);
        return "list";
    }
    @RequestMapping("/add.do")
    public String add(){

        return "add";
    }
    @RequestMapping("/doAdd.do")
    public String doAdd(User user){
        service.add(user);
        return "redirect:list.do";
    }
    @RequestMapping("/delete.do")
    public String delete(Integer id){
        service.delete(id);
        return "redirect:list.do";
    }
    @RequestMapping("/update.do")
    public String update(ModelMap map,Integer id){
       User u = service.getOne(id);
        map.put("u",u);
        return "update";
    }
    @RequestMapping("/doupdate.do")
    public String doUpdate(User user){
        service.update(user);
        return "redirect:list.do";
    }

}
