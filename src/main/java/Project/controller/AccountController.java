package Project.controller;

import Project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/login")
    public ModelAndView login (){
        return new ModelAndView("login");
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        return new ModelAndView("login");
    }

    @GetMapping("/user")
    public ModelAndView user(){
        return new ModelAndView("user");
    }

}
