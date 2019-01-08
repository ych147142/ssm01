package com.ych.web;

import com.sun.deploy.net.HttpResponse;
import com.ych.pojo.CookieUtil;
import com.ych.pojo.User;
import com.ych.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/login.do")
    public String login(){
        return "login";
    }
    @RequestMapping("/dologin.do")
    public String doLogin(String username, String password, HttpServletResponse resp, HttpServletRequest req){
        User user = service.getOneByName(username);
        if (user.getUsername()!= null){
            if (password.equals(user.getPassword())){
                Cookie cname =new Cookie("username",username);
                Cookie cpwd = new Cookie("password",password);

                cname.setMaxAge(60*60);
                cpwd.setMaxAge(60*60);
                resp.addCookie(cname);
                resp.addCookie(cpwd);
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                return "redirect:list.do";
            }
        }
        return "login";
    }
    @RequestMapping("/exit.do")
    public String exit(HttpServletRequest req,HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        Map<String,Cookie> maps = CookieUtil.getCookie(cookies);
        Cookie c = maps.get("username");
        c.setValue("");
        c.setMaxAge(0);
        resp.addCookie(c);
        HttpSession session =req.getSession();
        session.invalidate();
        return "redirect:login.do";
    }

    @RequestMapping("/doUpload.do")
    public String upload(@RequestParam("files") MultipartFile[] files){
        for (MultipartFile f:files
             ) {
            if (!f.isEmpty()){
                String filename = f.getOriginalFilename();
                try {
                    FileUtils.copyInputStreamToFile(f.getInputStream(),new File("C:\\Users\\Administrator\\Documents\\a\\"+filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return "";
    }

}
