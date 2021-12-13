package zhytest.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zhytest.crud.bean.Admin;

/**
 * @author Zhy
 * @create 2021-12-07 1:52
 */

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    Integer countByLoginId(@Param("login_id") String loginId);
    Admin selectByLoginId(@Param("login_id") String loginId);
    void insertByLoginId(Admin admin);
}
