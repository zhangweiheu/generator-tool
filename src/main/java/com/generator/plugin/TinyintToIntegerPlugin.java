package com.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * 把数据库中的tinyint自动映射为Integer
 * 
 * @author tanxw
 *
 */
public class TinyintToIntegerPlugin extends PluginAdapter {
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable intrp) {
		List<IntrospectedColumn> list = intrp.getAllColumns();
		if (null != list) {
			for (IntrospectedColumn col : list) {
				if (col.getJdbcTypeName().equalsIgnoreCase("TINYINT")
						&& col.getFullyQualifiedJavaType().getFullyQualifiedName().equals(Byte.class.getName())) {
					col.setFullyQualifiedJavaType(new FullyQualifiedJavaType(Integer.class.getName()));
				}
			}
		}
	}
}
