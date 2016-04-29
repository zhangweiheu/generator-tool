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
        runner.setTargetProject("D:\\GraduationProject\\SSH\\src\\main\\java");
        runner.setJavaModelTargetPackage("model");
        String mapperPackage = "mapper";
        runner.setSqlMapTargetPackage(mapperPackage);
        runner.setJavaClientTargetPackage(mapperPackage);

        runner.addFullyQualifiedTable("user");
        runner.addFullyQualifiedTable("activity");
        runner.addFullyQualifiedTable("news");
        runner.addFullyQualifiedTable("sign_activity");
        runner.run();
    }
}
