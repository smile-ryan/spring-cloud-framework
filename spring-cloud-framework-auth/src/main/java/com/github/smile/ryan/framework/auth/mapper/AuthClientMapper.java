package com.github.smile.ryan.framework.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthClientMapper
 * 描述：AuthClientMapper.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthClientMapper extends BaseMapper<AuthClientEntity> {

    @Select("select * from auth_client where client_id = #{clientId} and is_deleted = 0")
    AuthClientEntity findClientByClientId(@Param("clientId") String clientId);

}
