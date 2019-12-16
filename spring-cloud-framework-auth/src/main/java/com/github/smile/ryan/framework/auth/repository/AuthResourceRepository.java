package com.github.smile.ryan.framework.auth.repository;

import com.github.smile.ryan.framework.auth.model.entity.AuthResourceEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 名称：AuthResourceRepository
 * 描述：AuthResourceRepository.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Repository
public interface AuthResourceRepository {

    @Select("select * from auth_resource where id = #{id} and is_deleted = 0")
    AuthResourceEntity findOneById(@Param("id") Long id);

}
