package com.github.smile.ryan.framework.auth.model.request;

import cn.hutool.core.util.IdUtil;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * <pre>
 * 名称：BaseRequest
 * 描述：BaseRequest.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
public class BaseRequest implements Serializable {

    private String id = IdUtil.fastUUID();

    private Date timestamp = new Date();

}
