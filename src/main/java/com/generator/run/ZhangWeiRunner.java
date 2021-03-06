package com.generator.run;

/**
 * 运行前的配置文件
 *
 * @author zhangwei 2015年11月9日
 */
public class ZhangWeiRunner {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.setConfigFile(ZhangWeiRunner.class.getClassLoader().getResource("graduation_generatorConfig.xml").getFile());
        runner.setContextIds("context1");
        runner.setTargetProject("D:\\GraduationProject\\exams-system-core\\src\\main\\java");
        runner.setJavaModelTargetPackage("com.online.exams.system.core.model");
        String mapperPackage = "com.online.exams.system.core.mapper";
        runner.setSqlMapTargetPackage(mapperPackage);
        runner.setJavaClientTargetPackage(mapperPackage);

        //设置生成的表
        runner.addFullyQualifiedTable("question");
        runner.addFullyQualifiedTable("tag");
        runner.addFullyQualifiedTable("paper");
        runner.addFullyQualifiedTable("user");
        runner.run();
    }
}
