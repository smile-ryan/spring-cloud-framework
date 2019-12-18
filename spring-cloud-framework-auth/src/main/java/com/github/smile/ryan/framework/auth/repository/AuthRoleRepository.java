package com.github.smile.ryan.framework.auth.repository;

import com.github.smile.ryan.framework.auth.model.entity.AuthRoleEntity;
import com.github.smile.ryan.framework.auth.model.request.AuthRoleRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
@Repository
public interface AuthRoleRepository {

    @Select("select r.* "
        + "from auth_user u "
        + "left join auth_user_role ur on u.id = ur.user_id "
        + "left join auth_role r on ur.role_id = r.id "
        + "where "
        + "u.id = #{userId} and u.is_deleted = 0 and ur.is_deleted = 0 and r.is_deleted = 0")
    List<AuthRoleEntity> findAllByUserId(@Param("userId") Long userId);

    @Select("<script>"
        + "select r.* "
        + "from auth_user u "
        + "left join auth_user_role ur on u.id = ur.user_id "
        + "left join auth_role r on ur.role_id = r.id "
        + "where "
        + "u.is_deleted = 0 and ur.is_deleted = 0 and r.is_deleted = 0 "
        + "<if test = '#{role.userId} != null'>"
        + "and u.id = #{role.userId} "
        + "</if>"
        + "<if test = '#{role.clientId} != null'>"
        + "and r.client_id = #{role.clientId} "
        + "</if>"
        + "</script>")
    List<AuthRoleEntity> findRolesByParameters(@Param("role") AuthRoleRequest roleRequest);

}
