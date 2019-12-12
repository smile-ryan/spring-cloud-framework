package com.github.smile.ryan.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthPermissionEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthPermissionMapper
 * 描述：AuthPermissionMapper.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthPermissionMapper extends BaseMapper<AuthPermissionEntity> {

    @Select("select p.* "
        + "from auth_user u "
        + "left join auth_user_role ur on u.id = ur.user_id "
        + "left join auth_role r on ur.role_id = r.id "
        + "left join auth_role_permission rp on rp.role_id = r.id "
        + "left join auth_permission p on rp.permission_id = p.id "
        + "where "
        + "u.id = #{userId} and u.is_deleted = 0 and ur.is_deleted = 0 "
        + "and r.is_deleted = 0 and rp.is_deleted = 0 and p.is_deleted = 0")
    List<AuthPermissionEntity> findAllByUserId(@Param("userId") Long userId);


    @Select("select p.* "
        + "from auth_role r "
        + "left join auth_role_permission rp on rp.role_id = r.id "
        + "left join auth_permission p on rp.permission_id = p.id "
        + "where "
        + "u.id = #{userId} and u.is_deleted = 0 and ur.is_deleted = 0 "
        + "and r.is_deleted = 0 and rp.is_deleted = 0 and p.is_deleted = 0")
    List<AuthPermissionEntity> findAllByRoleId(@Param("roleId") Long roleId);

}
