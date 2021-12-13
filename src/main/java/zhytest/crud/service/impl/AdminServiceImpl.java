package zhytest.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhytest.crud.bean.Admin;
import zhytest.crud.mapper.AdminMapper;
import zhytest.crud.service.AdminService;

/**
 * @author Zhy
 * @create 2021-12-07 1:55
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Integer countByLoginId(String loginId) {

        return adminMapper.countByLoginId(loginId);
    }

    @Override
    public Admin selectByLoginId(String loginId) {
        return adminMapper.selectByLoginId(loginId);
    }

    @Override
    public void insertByLoginId(Admin admin) {
        adminMapper.insertByLoginId(admin);
    }
}
