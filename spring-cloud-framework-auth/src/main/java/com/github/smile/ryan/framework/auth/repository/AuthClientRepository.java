package com.github.smile.ryan.framework.auth.repository;

import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthClientRepository
 * 描述：AuthClientRepository.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthClientRepository {

    @Select("select * from auth_client where client_id = #{clientId} and is_deleted = 0")
    AuthClientEntity findClientByClientId(@Param("clientId") String clientId);

}
