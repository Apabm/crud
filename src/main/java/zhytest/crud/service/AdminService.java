package zhytest.crud.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.apache.ibatis.annotations.Param;
import zhytest.crud.bean.Admin;

/**
 * @author Zhy
 * @create 2021-12-07 1:54
 */
public interface AdminService extends IService<Admin> {
    Integer countByLoginId( String loginId);
    Admin selectByLoginId(String loginId);
    void insertByLoginId(Admin admin);
}
