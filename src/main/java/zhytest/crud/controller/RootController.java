package zhytest.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zhytest.crud.bean.Root;
import zhytest.crud.service.RootService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Zhy
 * @create 2021-12-12 18:24
 */
@Controller
public class RootController {

    @Autowired
    RootService rootService;

    @GetMapping("/rootlog")
    public String rootLogin(){

        return "rootlog";
    }

    @PostMapping("/root")
    public String verifyIdAndPassword(Root root, HttpSession session, Model model){
        Integer integer = rootService.verifyIdAndPassword(root.getId(), root.getPassword());
        if(integer.intValue() == 1){
            session.setAttribute("admin",root);
            return "redirect:/root.html";
        }else {
            model.addAttribute("msg","账号或密码错误");
            return "rootlog";
        }


    }

    @GetMapping("/root.html")
    public String logSuccess(){
        return "root";
    }



}
