/**
 * 
 */
package com.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 可以指定将某个字段放在BlobModel Class中，提高查询针对性，将不必每次都查询的字段放到BlobModel Class
 * 
 * @author tanxianwen 2015年1月27日
 */
public class MyIntrospectedColumn extends IntrospectedColumn {

    @Override
    public boolean isBLOBColumn() {
        if (null != properties && "true".equals(properties.getProperty("forceInBlobModel", "false"))) {
            return true;
        }
        return super.isBLOBColumn();
    }
}
