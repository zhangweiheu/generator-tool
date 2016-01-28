package com.generator.run;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Runner {

    private boolean overwrite = true;
    private Set<String> fullyQualifiedTables = new HashSet<String>();
    private Set<String> contextIds = new HashSet<String>();
    private boolean verbose = true;
    private List<String> warnings = new ArrayList<String>();
    private String configFile;
    private String targetProject;
    private String javaClientTargetProject;
    private String javaModelTargetProject;
    private String sqlMapTargetProject;
    private String javaClientTargetPackage;
    private String javaModelTargetPackage;
    private String sqlMapTargetPackage;
    private String username;
    private String password;
    private String jdbcDriverClass;
    private String jdbcConnectionUrl;


    public List<String> getWarnings() {
        return warnings;
    }

    private boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    private boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 后置处理解析完毕的配置，给最后一次覆盖配置的机会
     * 
     * @param config
     */
    protected void postProcessConfiguration(Configuration config) {
        List<Context> contexts = config.getContexts();
        for (Context ctx : contexts) {
            JavaClientGeneratorConfiguration javaClient = ctx.getJavaClientGeneratorConfiguration();
            if (isNotEmpty(getJavaClientTargetProject())) {
                javaClient.setTargetProject(getJavaClientTargetProject());
            }
            if (isNotEmpty(getJavaClientTargetPackage())) {
                javaClient.setTargetPackage(getJavaClientTargetPackage());
            }

            JavaModelGeneratorConfiguration javaModel = ctx.getJavaModelGeneratorConfiguration();
            if (isNotEmpty(getJavaModelTargetProject())) {
                javaModel.setTargetProject(getJavaModelTargetProject());
            }
            if (isNotEmpty(getJavaModelTargetPackage())) {
                javaModel.setTargetPackage(getJavaModelTargetPackage());
            }

            SqlMapGeneratorConfiguration sqlMap = ctx.getSqlMapGeneratorConfiguration();
            if (isNotEmpty(getSqlMapTargetProject())) {
                sqlMap.setTargetProject(getSqlMapTargetProject());
            }
            if (isNotEmpty(getSqlMapTargetPackage())) {
                sqlMap.setTargetPackage(getSqlMapTargetPackage());
            }

            JDBCConnectionConfiguration jdbc = ctx.getJdbcConnectionConfiguration();
            if (isNotEmpty(getJdbcConnectionUrl())) {
                jdbc.setConnectionURL(getJdbcConnectionUrl());
            }
            if (isNotEmpty(getJdbcDriverClass())) {
                jdbc.setDriverClass(getJdbcDriverClass());
            }
            if (isNotEmpty(getPassword())) {
                jdbc.setPassword(getPassword());
            }
            if (isNotEmpty(getUsername())) {
                jdbc.setUserId(getUsername());
            }
        }

    }

    public void run() {
        try {
            File configFile = new File(getConfigFile());
            ConfigurationParser cp = new ConfigurationParser(getWarnings());
            Configuration config = cp.parseConfiguration(configFile);
            postProcessConfiguration(config);
            DefaultShellCallback callback = new DefaultShellCallback(isOverwrite());
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, getWarnings());
            ProgressCallback progressCallback = isVerbose() ? new VerboseProgressCallback() : null;
            myBatisGenerator.generate(progressCallback, getContextIds(), getFullyQualifiedTables());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> getContextIds() {
        return contextIds;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public void addFullyQualifiedTable(String fullyQualifiedTable) {
        this.fullyQualifiedTables.add(fullyQualifiedTable);
    }

    public void setContextIds(String... contextIds) {
        for (String c : contextIds) {
            this.contextIds.add(c);
        }
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setWarnings(String... warnings) {
        for (String c : warnings) {
            this.warnings.add(c);
        }
    }


    public String getConfigFile() {
        return configFile;
    }


    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }


    public String getTargetProject() {
        return targetProject;
    }


    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }


    public String getJavaClientTargetProject() {
        if (javaClientTargetProject == null) {
            return getTargetProject();
        }
        return javaClientTargetProject;
    }


    public void setJavaClientTargetProject(String javaClientTargetProject) {
        this.javaClientTargetProject = javaClientTargetProject;
    }


    public String getJavaModelTargetProject() {
        if (javaModelTargetProject == null) {
            return getTargetProject();
        }
        return javaModelTargetProject;
    }


    public void setJavaModelTargetProject(String javaModelTargetProject) {
        this.javaModelTargetProject = javaModelTargetProject;
    }


    public String getSqlMapTargetProject() {
        if (sqlMapTargetProject == null) {
            return getTargetProject();
        }
        return sqlMapTargetProject;
    }


    public void setSqlMapTargetProject(String sqlMapTargetProject) {
        this.sqlMapTargetProject = sqlMapTargetProject;
    }


    public String getJavaClientTargetPackage() {
        return javaClientTargetPackage;
    }


    public void setJavaClientTargetPackage(String javaClientTargetPackage) {
        this.javaClientTargetPackage = javaClientTargetPackage;
    }


    public String getJavaModelTargetPackage() {
        return javaModelTargetPackage;
    }


    public void setJavaModelTargetPackage(String javaModelTargetPackage) {
        this.javaModelTargetPackage = javaModelTargetPackage;
    }


    public String getSqlMapTargetPackage() {
        return sqlMapTargetPackage;
    }


    public void setSqlMapTargetPackage(String sqlMapTargetPackage) {
        this.sqlMapTargetPackage = sqlMapTargetPackage;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getJdbcDriverClass() {
        return jdbcDriverClass;
    }


    public void setJdbcDriverClass(String jdbcDriverClass) {
        this.jdbcDriverClass = jdbcDriverClass;
    }


    public String getJdbcConnectionUrl() {
        return jdbcConnectionUrl;
    }


    public void setJdbcConnectionUrl(String jdbcConnectionUrl) {
        this.jdbcConnectionUrl = jdbcConnectionUrl;
    }

    public Set<String> getFullyQualifiedTables() {
        return fullyQualifiedTables;
    }


    public void setFullyQualifiedTables(Set<String> fullyQualifiedTables) {
        this.fullyQualifiedTables = fullyQualifiedTables;
    }
}
