package com.generator.run;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2016/3/19.
 */
public class ProgramUtil {

    /**
     * javac编译文件demo.java，确定是否有语法错误
     */

    public static String compileJava(String source) {

        /**输出java代码到目录*/
        File file = new File("f:\\demo.java");
        try {
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(source);
            out.flush();
            out.close();
        } catch (Exception e) {
            return "-1";
        }

        /**编译java文件*/
        String exeDir = "C:\\Program Files\\Java\\jdk1.8.0_51\\bin"; // PngMate.exe文件存放目录
        String workDir = "f:\\";     // 工作目录
        String javaSource = "demo.java";    // 需要转换的源文件，于工作目录下
        String javaClass = "demo";    // 需要转换的源文件，于工作目录下
        String testCaseInputFile = "input.txt";    // 测试用例输入，于工作目录下
        String testCaseOutputFile = "output.txt";    // 测试用例输出，于工作目录下
        String testCaseAnswerFile = "answer.txt";    // 测试用例输出，于工作目录下

        List<String> command = new LinkedList<String>();
        command.add("javac.exe");
        command.add(javaSource);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);
        Map<String, String> environment = processBuilder.environment();
        environment.put("Path", environment.get("Path") + exeDir);
        processBuilder.directory(new File(workDir));
        try {
            processBuilder.start();
        } catch (Exception e) {
            return e.toString();
        }

        processBuilder.command().clear();
        command.clear();

        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            return e.toString();
        }

        /**执行demo.java文件*/
        command.add("java.exe");
        command.add(javaClass);
        command.add("<");
        command.add(testCaseInputFile);
        command.add(">>");
        command.add(testCaseOutputFile);

        processBuilder.command(command);
        try {
            processBuilder.start();
        } catch (Exception e) {
            return e.toString();
        }

        /**比较结果文件是否一致*/

        File fileOutput = new File("f:\\output.txt");
        File fileanswer = new File("f:\\answer.txt");

        try{
            FileOutputStream fileOutputStream = new
            file.length()

        }catch (Exception e){

        }

        return "0";
    }

    /**java运行文件demo，测试输出是否和结果一致*/

}
