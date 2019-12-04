package com.github.smile.ryan.framework.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <pre>
 * 名称：BaseEntity
 * 描述：BaseEntity.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
public class BaseEntity implements Serializable {

  /**
   * ID
   */
  @NotNull
  @TableId
  private Long id;

  /**
   * 创建时间
   */
  private Date createdAt;

  /**
   * 创建人
   */
  private Long createdBy;

  /**
   * 更新时间
   */
  private Date updatedAt;

  /**
   * 更新人
   */
  private Long updatedBy;

}
