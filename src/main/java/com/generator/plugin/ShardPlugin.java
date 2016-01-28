package com.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.TableConfiguration;

import com.mysql.jdbc.StringUtils;

/**
 * 类上添加散表字段
 * 
 * @author Chris Mee
 * @Email micuncang@36kr.com 2015年5月14日
 */
public class ShardPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        TableConfiguration tc = introspectedTable.getTableConfiguration();
        String shardKey = tc.getProperty("shardBy");
        if (!StringUtils.isEmptyOrWhitespaceOnly(shardKey)) {
            StringBuffer sb = new StringBuffer();
            sb.append("@ShardBy(\"").append(shardKey).append("\")");
            topLevelClass.addAnnotation(sb.toString());
            // @ShardBy注解可以作为基本类型并入common-util, 此处不进行引入强制
            topLevelClass.addImportedType("");
        }
        return true;
    }

    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Field shardField = new Field();
        shardField.setVisibility(JavaVisibility.PROTECTED);
        shardField.setType(FullyQualifiedJavaType.getIntInstance());
        shardField.setName("shardIndex");
        shardField.setInitializationString("-1");
        topLevelClass.addField(shardField);

        Method shardIndexSetter = new Method();
        shardIndexSetter.setVisibility(JavaVisibility.PUBLIC);
        shardIndexSetter.setName("setShardIndex");
        shardIndexSetter.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "shardIndex"));
        shardIndexSetter.addBodyLine("this.shardIndex = shardIndex;");
        topLevelClass.addMethod(shardIndexSetter);

        Method shardIndexGetter = new Method();
        shardIndexGetter.setVisibility(JavaVisibility.PUBLIC);
        shardIndexGetter.setReturnType(FullyQualifiedJavaType.getIntInstance());
        shardIndexGetter.setName("getShardIndex");
        shardIndexGetter.addBodyLine("return shardIndex;");
        topLevelClass.addMethod(shardIndexGetter);
        return true;
    }
}
