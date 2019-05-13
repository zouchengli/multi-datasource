package site.clzblog.application.second.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.clzblog.application.domain.User;
import site.clzblog.application.second.mapper.UserSecondMapper;

@Service
public class UserSecondService {
    private final UserSecondMapper userSecondMapper;

    public UserSecondService(UserSecondMapper userSecondMapper) {
        this.userSecondMapper = userSecondMapper;
    }

    //@Transactional(transactionManager = "secondDataSourceTransactionManager")
    public Integer insert(String name) {
        User user = new User(0, name);
        return userSecondMapper.insert(user);
    }
}
