package zhytest.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhytest.crud.bean.Department;
import zhytest.crud.bean.Msg;
import zhytest.crud.service.impl.DepartmentServiceImpl;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-11-23 14:01
 */

@Controller
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;


    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list = departmentService.getDepts();
        Msg depts = Msg.success().add("depts", list);
        return depts;
    }

    @RequestMapping(value = "/depts/{dId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getDeptById(@PathVariable Integer dId){
        Department dept = departmentService.getDeptById(dId);
        Msg msg = Msg.success().add("dept", dept);
        return msg;
    }






}
