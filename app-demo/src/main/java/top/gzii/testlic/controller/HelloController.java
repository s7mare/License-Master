package top.gzii.testlic.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gzii.license.annotation.ValidLicense;

@RequestMapping("/test")
@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "Hello,"+name;
    }

    @GetMapping("/login")
    @ValidLicense
    public String login(String username,String password){
        return "登录成功:"+username;
    }

}
