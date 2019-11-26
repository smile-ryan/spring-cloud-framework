package com.github.smile_ryan.spring.cloud.framework.auth.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * 名称: BeanUtils
 * 描述: 对象处理工具类
 * </pre>
 *
 * @author Ryan Chen
 * @since 1.0.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {


    public static void copyProperties(Object src, Object target) {
        copyProperties(src, target, true);
    }

    /**
     * 拷贝对象，忽略Null值
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object src, Object target, boolean ignore) {
        String[] nullValueFields = new String[]{};
        if (ignore) {
            nullValueFields = getNullValueFields(src);
        }
        BeanUtils.copyProperties(src, target, nullValueFields);
    }

    /**
     * 获取Null值字段
     *
     * @param src 源对象
     * @return Null值字段数组
     */
    private static String[] getNullValueFields(Object src) {
        final BeanWrapper source = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = source.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = source.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
