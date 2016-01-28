package org.mybatis.generator.config.xml;

import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.XMLParserException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Chris Mee
 * @Email micuncang@36kr.com 2015年11月11日
 */
public class KrConfigurationParser extends ConfigurationParser {
    public static String jdbcConnection_driverClass;
    public static String jdbcConnection_connectionURL;
    public static String jdbcConnection_userId;
    public static String jdbcConnection_password;

    public static String javaModelGenerator_targetPackage;
    public static String javaModelGenerator_targetProject;

    public static String sqlMapGenerator_targetPackage;
    public static String sqlMapGenerator_targetProject;

    public static String javaClientGenerator_targetPackage;
    public static String javaClientGenerator_targetProject;

    public static void initTargetProject(String targetProject) {
        KrConfigurationParser.javaModelGenerator_targetProject = targetProject;
        KrConfigurationParser.sqlMapGenerator_targetProject = targetProject;
        KrConfigurationParser.javaClientGenerator_targetProject = targetProject;
    }

    public KrConfigurationParser(List<String> warnings) {
        super(warnings);
    }

    public void check() {
        if (jdbcConnection_driverClass == null || jdbcConnection_connectionURL == null || jdbcConnection_userId == null
                || jdbcConnection_password == null) {
            throw new IllegalArgumentException("JDBCConnectionConfiguration args can't be null;");
        }

        if (javaModelGenerator_targetPackage == null || javaModelGenerator_targetProject == null) {
            throw new IllegalArgumentException("JavaModelGeneratorConfiguration args can't be null;");
        }

        if (sqlMapGenerator_targetPackage == null || sqlMapGenerator_targetProject == null) {
            throw new IllegalArgumentException("SqlMapGeneratorConfiguration args can't be null;");
        }

        if (javaClientGenerator_targetPackage == null || javaClientGenerator_targetProject == null) {
            throw new IllegalArgumentException("JavaClientGeneratorConfiguration args can't be null;");
        }
    }

    public Configuration parseConfiguration(File inputFile) throws IOException, XMLParserException {
        check();
        Configuration configuration = super.parseConfiguration(inputFile);
        List<Context> contexts = configuration.getContexts();
        if (contexts != null && contexts.size() > 0) {
            for (Context context : contexts) {
                JDBCConnectionConfiguration jDBCConnectionConfiguration = context.getJdbcConnectionConfiguration();
                if (jDBCConnectionConfiguration != null) {
                    jDBCConnectionConfiguration.setConnectionURL(jdbcConnection_connectionURL);
                    jDBCConnectionConfiguration.setDriverClass(jdbcConnection_driverClass);
                    jDBCConnectionConfiguration.setPassword(jdbcConnection_password);
                    jDBCConnectionConfiguration.setUserId(jdbcConnection_userId);
                }

                JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = context.getJavaClientGeneratorConfiguration();
                if (javaClientGeneratorConfiguration != null) {
                    javaClientGeneratorConfiguration.setTargetPackage(javaClientGenerator_targetPackage);
                    javaClientGeneratorConfiguration.setTargetProject(javaClientGenerator_targetProject);
                }

                SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = context.getSqlMapGeneratorConfiguration();
                if (sqlMapGeneratorConfiguration != null) {
                    sqlMapGeneratorConfiguration.setTargetPackage(sqlMapGenerator_targetPackage);
                    sqlMapGeneratorConfiguration.setTargetProject(sqlMapGenerator_targetProject);
                }

                JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context.getJavaModelGeneratorConfiguration();
                if (javaModelGeneratorConfiguration != null) {
                    javaModelGeneratorConfiguration.setTargetPackage(javaModelGenerator_targetPackage);
                    javaModelGeneratorConfiguration.setTargetProject(javaModelGenerator_targetProject);
                }
            }

        }

        return configuration;
    }
}
