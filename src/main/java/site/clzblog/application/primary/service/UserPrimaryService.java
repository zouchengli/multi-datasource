package site.clzblog.application.primary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.clzblog.application.domain.User;
import site.clzblog.application.primary.mapper.UserPrimaryMapper;

@Service
public class UserPrimaryService {
    private final UserPrimaryMapper userPrimaryMapper;

    public UserPrimaryService(UserPrimaryMapper userPrimaryMapper) {
        this.userPrimaryMapper = userPrimaryMapper;
    }

    @Transactional(transactionManager = "primaryDataSourceTransactionManager")
    public Integer insert(String name) {
        User user = new User(0, name);
        return userPrimaryMapper.insert(user);
    }

}
