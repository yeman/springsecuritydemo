package com.yjt.springcloud.demodb.controller;

import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username",principal.getName());
        return "user";
    }

    @GetMapping("/getuser")
    @ResponseBody
    public User user(@RequestParam("username") String username){
      return userService.getByUsername(username);
    }
}
