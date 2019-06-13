package com.bj186.ssm.controller;
import com.bj186.ssm.exception.NullNameException;
import com.bj186.ssm.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
// http://localhost:8080/user  user就会找到注解中标注为user的Controller类
@RequestMapping("/user")
@SessionAttributes(value = {"sessionVal"},types = {String.class})
public class UserController {

    @ModelAttribute("user")
    public User getUser(){
        User user = new User();
        user.setUsername("李思喜");
        user.setPhoneNumber("123456789");
        return user;
    }


    // http://localhost:8080/user/login login就会找到方法中标注为login的RequestMapping
    @RequestMapping(value={"login","login2"})
    public String login(){  // 方法名叫什么都可以
        System.out.println("view here...");
        // 返回值表示视图的文件名
        //  /WEB-INF/jsp/user/login.jsp
        //  /WEB-INF/jsp/user/register.jsp

        //do somthing   service->mapper->db

       // return "/WEB-INF/jsp/user/login.jsp";
        return "user/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "user/register";
    }

    @RequestMapping("/session")
    /*ModelMap : 模型映射表对象,添加键值对,供jsp页面通过el表达式访问
    通过参数进行声明
    * */
    public String getSession(ModelMap modelMap){
        modelMap.put("sessionVal","admin");
        return "session";
    }

   /* restful设计风格:  /login/1001
    如果我们希望从路由中得到1001
    必须通过PathVariable注解在参数上进行声明
    接下来在路由中带上参数 {id}
    */
    @RequestMapping("/login/id/{id}/name/{name}")
    public String getVar(@PathVariable("id") Integer id,@PathVariable("name")String name){
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        return "user/login";
    }
    @RequestMapping("/getName")
    public String getName(@RequestParam(name="username",required = true,defaultValue = "admin") String username){
        System.out.println("username:"+username);
        return "user/login";
    }

    /*@RequestMapping("/ajax")
    public String ajax(@RequestBody User user){
        System.out.println(user);
        return "user/login";
    }*/

   /* @RequestMapping("/ajax")
    @ResponseBody
    public User ajax(@RequestBody User user){
        System.out.println(user);
        user.setPhoneNumber("123456789");
        user.setGender("女");
        return user;
    }*/

    @RequestMapping("/ajax")
    @ResponseBody
    public List<User> ajax(@RequestBody User user) throws NullNameException {
        System.out.println(user);
        // 利用异常,改写操作
        if(user.getUsername().equals("")){
            throw new NullNameException("用户名不能为空");
        }else{
            user.setPhoneNumber("123456789");
            user.setGender("女");
            List<User> users = new ArrayList<>();
            users.add(user);
            return users;
        }
    }



    @RequestMapping(value="/getcookie")
    public String getReg2(@CookieValue(value = "JSESSIONID") String jsessionid) {
        System.out.println(jsessionid);
        return "/user/login";
    }

//    @RequestMapping(value = "/http", method = RequestMethod.GET)
//    public String loginMVC(HttpServletRequest request,
//                           HttpServletResponse responses, ModelMap model) {
//        System.out.println("loginHttpMVCController--------------------print");
//        System.out.println("request=" + request);
//        System.out.println("responses=" + responses);
//        model.addAttribute("message", "Hello Spring MVC Framework!");
//        return "http";
//    }

    @RequestMapping("/html")
    public String toHTML(User user){
        return "redirect:/statics/html/hello.html";
}


}
