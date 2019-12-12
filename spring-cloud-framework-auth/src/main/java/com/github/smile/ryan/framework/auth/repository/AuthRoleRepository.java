package com.github.smile.ryan.framework.auth.repository;

import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthRoleRepository
 * 描述：AuthRoleRepository.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthRoleRepository {

    @Select("select r.* "
        + "from auth_user u "
        + "left join auth_user_role ur on u.id = ur.user_id "
        + "left join auth_role r on ur.role_id = r.id "
        + "where "
        + "u.id = #{userId} and u.is_deleted = 0 and ur.is_deleted = 0 and r.is_deleted = 0")
    List<AuthRoleEntity> findAllByUserId(@Param("userId") Long userId);

}
