package zhytest.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zhy
 * @create 2021-12-12 18:01
 */
@Mapper
public interface RootMapper {
    Integer verifyIdAndPassword(@Param("id") String id,@Param("password") String password);
}
