package site.clzblog.application.primary.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import site.clzblog.application.domain.User;

public interface UserPrimaryMapper {
    @Select("select * from t_user")
    User selectAll();

    @Insert("insert into t_user values(null,#{name})")
    int insert(User user);
}
