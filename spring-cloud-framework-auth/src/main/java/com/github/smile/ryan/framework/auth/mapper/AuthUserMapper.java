package com.github.smile.ryan.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthUserMapper
 * 描述：AuthUserMapper.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthUserMapper extends BaseMapper<AuthUserEntity> {

    @Select("select * from auth_user where username = #{username} and is_deleted = 0")
    AuthUserEntity findByUserName(@Param("username") String username);

}
