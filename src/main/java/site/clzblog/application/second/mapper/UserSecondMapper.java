package site.clzblog.application.second.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import site.clzblog.application.domain.User;

public interface UserSecondMapper {
    @Select("select * from t_user")
    User selectAll();

    @Insert("insert into t_user values(null,#{name})")
    int insert(User user);
}
