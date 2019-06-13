package com.bj186.ssm.controller;

import com.bj186.ssm.entity.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/selectUserController")
public class SelectUserController 	 {

@RequestMapping(value="/userCheck")
public String inputUser(Model model){
        /*
        inputUser 和下面的createUserBean 方法，都是封装bean对象并发送到前端页面的方式，任选一种都可以实现对应效果
         */
        HashMap<Integer, String> aihao = new HashMap<Integer, String>();
        aihao.put(1, "lq");
        aihao.put(2, "ymq");
        aihao.put(3, "tq");
        aihao.put(4, "yy");
        model.addAttribute("user", new UserBean());
        model.addAttribute("aihao", aihao);
        model.addAttribute("zhiye",new String[]{"js","xs","ys","qt"});
        return "UserAdd";
        }

@RequestMapping(value = "/createUserBean", method = RequestMethod.GET)
public ModelAndView createUserBean() {
        /*
        如果要使用spring的mvc form表单标签，那么就需要首先请求这个地址
        viewName 代表要跳转的表单地址jsp，前缀和后缀在spring-mvc.xml中约定，如果页面在webapp根目录就需要通过../../跳出来
        modelName 是Spring表单默认需要添加的key
        modelObject 创建一个空的bean对象发送给表单填装内容
         */
        return new ModelAndView("redirect:/statics/html/register.html", "command", new UserBean());
        }

@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
public String selectUser(UserBean userBean, ModelMap model) {
        model.addAttribute("username", userBean.getUsername());
        model.addAttribute("password", userBean.getPassword());
        System.out.println(userBean.getUsername());
        System.out.println(userBean.getPassword());
        /*
        模拟当数据处理失败的时候，返回输入页面，保留用户输入的信息
         */
        if (userBean.getPassword().equals("123456")) {
        return "../../well";
        } else {
            /*
            错误的时候，应该把用户输入的数据返回到输入页面，让用户修改
            Spring标签自动获取的key为command
             */
        model.addAttribute("command", userBean);
        return "../../user";
        }
        }

@ModelAttribute("checkboxesList")
public List<String> getWebFrameworkList() {
        // 组装一个复选框 多选
        List<String> checkboxesList = new ArrayList<>();
        checkboxesList.add("aa");
        checkboxesList.add("bb");
        checkboxesList.add("cc");
        return checkboxesList;
        }

@ModelAttribute("cityList")
public Map<String, String> getCountryList() {
        // 组装一个下拉菜单
        Map<String, String> cityList = new HashMap<>();
        cityList.put("a", "aaa");
        cityList.put("b", "bbb");
        cityList.put("c", "ccc");
        return cityList;
        }

@ModelAttribute("beanName")
public String getBeanName() {
        return "zhangsan";
        }
        }
