package org.mybatis.generator.codegen.mybatis3;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.util.ArrayList;
import java.util.List;

/**
 * 去除XML文件的Merge特质
 * 
 * 需要在配置文件中配置相应的targetRuntime
 * 
 * @author Chris Mee
 * @Email micuncang@36kr.com 2015年5月13日
 */
public class NoMergeIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {
    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();

        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf =
                    new GeneratedXmlFile(document, getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(), context
                            .getSqlMapGeneratorConfiguration().getTargetProject(), false, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }

        return answer;
    }
}
