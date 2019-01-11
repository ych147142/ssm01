package com.ych.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sun.deploy.net.HttpResponse;
import com.ych.pojo.CookieUtil;
import com.ych.pojo.User;
import com.ych.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String list(ModelMap map,HttpServletRequest req,User user){
        int pageNum=req.getParameter("pageNum")==null?1:Integer.parseInt(req.getParameter("pageNum"));
        int pagesize=3;
        PageHelper.startPage(pageNum,pagesize);
        List<User> lists = null;
        if (StringUtils.isBlank(user.getUsername())){
            user.setUsername(null);
            lists = service.getLists(user);
        }else {
            lists = service.getLists(user);
            String username = "&username="+user.getUsername();
            map.put("username",username);
        }
        PageInfo<User> pageInfo = new PageInfo<>(lists,4);
        map.put("lists",lists);
        map.put("page",pageInfo);
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

    /**
     * 注册
     */
    @RequestMapping("/register.do")
    public String register(){
        return "register";
    }
    @RequestMapping("/doregister.do")
    @ResponseBody
    public String doregister(User user,String cpwd){
        if (user.getUsername() != ""){
            User u = service.getOneByName(user.getUsername());
            if (u == null){
                if (user.getPassword() != null){
                    if (user.getPassword().equals(cpwd)){
                        return "1";
                    }else {
                        return "2";
                    }
                }else {
                    return "5";
                }

            }else {
                return "3";//user存在
            }
        }else {
            return "4";//username null
        }
    }
    @RequestMapping("/addregister.do")
    @ResponseBody
    public String addRegister(User user){
        int result = service.add(user);
        if (result > 0){
            return "1";
        }else {
            return "0";
        }
    }

}
