package site.clzblog.application.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.clzblog.application.primary.service.UserPrimaryService;
import site.clzblog.application.second.service.UserSecondService;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserApi {
    private final UserPrimaryService userPrimaryService;

    private final UserSecondService userSecondService;

    public UserApi(UserPrimaryService userPrimaryService, UserSecondService userSecondService) {
        this.userPrimaryService = userPrimaryService;
        this.userSecondService = userSecondService;
    }

    @Transactional
    @RequestMapping("insert")
    public String insert(String name1, String name2) {
        Integer insert = userPrimaryService.insert(name1);
        //int i = 1/0;
        Integer insert1 = userSecondService.insert(name2);
        log.info("{} {}", insert, insert1);
        return "success";
    }
}
