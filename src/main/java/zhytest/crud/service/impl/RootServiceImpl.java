package zhytest.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhytest.crud.mapper.RootMapper;
import zhytest.crud.service.RootService;

/**
 * @author Zhy
 * @create 2021-12-12 18:17
 */
@Service
public class RootServiceImpl implements RootService {

    @Autowired
    RootMapper rootMapper;

    @Override
    public Integer verifyIdAndPassword(String id, String password) {
        return rootMapper.verifyIdAndPassword(id,password);
    }
}
