package com.nbh.wxprojectcore.base;

/**
 * 基础枚举接口
 *
 * @version 1.0
 * @author nongbh 2019-01-11
 */
public interface BaseEnum<K,V> {
    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     *
     * @return 描述
     */
    V desc();
}
