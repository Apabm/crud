package zhytest.crud.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zhytest.crud.bean.Admin;
import zhytest.crud.bean.Cdk;
import zhytest.crud.bean.Employee;
import zhytest.crud.bean.Msg;
import zhytest.crud.service.AdminService;
import zhytest.crud.service.CdkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Zhy
 * @create 2021-12-07 14:49
 */
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    CdkService cdkService;

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "index";
    }

    //登入
    @PostMapping("/login")
    public String toMain(Admin admin, HttpSession session,Model model){

        if(adminService.countByLoginId(admin.getLoginId())==1){
            Admin admin1 = adminService.selectByLoginId(admin.getLoginId());

            if(admin.getLoginPassword().equals(admin1.getLoginPassword()) && admin1.getdId() == admin.getdId()){
                session.setAttribute("admin",admin);
                return "redirect:/main.html/"+admin.getdId();
            } else {
                model.addAttribute("msg","账号或密码错误");
                return "index";
            }
        }else {
            model.addAttribute("msg","账号或密码错误");
            return "index";
        }

    }


    @GetMapping("/main.html/{dId}")
    public String logSuccess(@PathVariable Integer dId,Model model){
        model.addAttribute("dId",dId);
        return "main";
    }

    @PostMapping("/registerId")
    public Msg checkRegister_loginId(@RequestParam String loginId) {
        Integer count = adminService.countByLoginId(loginId);
        if (count != 0) {
            return Msg.fail().add("about_exist","该账号已存在，重新输入");
        }else if(count==0){
            return Msg.fail().add("about_exist","该账号可用");
        }
        return null;

    }

    @ResponseBody
    @PostMapping("/register/admin")
    public Msg registerAdmin(Cdk cdk, Admin admin, HttpServletRequest request,@RequestParam("code")String vc){

        System.out.println(admin);
        String regId = "(^[a-zA-Z][a-zA-Z0-9_]{4,15}$)";
        String regPassword="(^[a-zA-Z]\\w{5,17}$)";

        String code = (String) request.getSession().getAttribute("verificationCode");
        request.getSession().removeAttribute("verificationCode");

        //验证验证码
        if(code.equals(vc)){
            //判断账号是否存在
            if(adminService.countByLoginId(admin.getLoginId())!=0){
                return Msg.fail().add("error_id","账号已存在");
            }

            //验证cdk是否存在，假如存在将其删除
            if(cdkService.deleteCdkNum(cdk.getCdkNum())==true){

                //防止别人抓包跳过前段检验
                if(admin.getLoginId().matches(regId) && admin.getLoginPassword().matches(regPassword)){
                    adminService.insertByLoginId(admin);
                    return Msg.success();
                }else {
                    return Msg.fail().add("error_id","账号或密码格式不正确");
                }
            }else {
                return Msg.fail().add("error_cdk","邀请码错误");
            }
        }else {
            return Msg.fail().add("error_code","验证码错误");
        }



    }



}
