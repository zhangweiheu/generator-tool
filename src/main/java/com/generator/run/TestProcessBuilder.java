package com.generator.run;

import java.io.Console;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zhang on 2016/2/27.
 */
public class TestProcessBuilder {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String m = sc.nextLine();
        System.out.println(m);


        String exeDir = "C:\\Program Files\\Java\\jdk1.8.0_51\\bin"; // PngMate.exe文件存放目录
        String workDir = "f:\\";     // 工作目录
        String javaSource = "demo.java";    // 需要转换的源文件，于工作目录下
        String javaClass = "demo";    // 需要转换的源文件，于工作目录下

        List<String> command = new LinkedList<String>();
        command.add("javac.exe");
        command.add(javaSource);
        command.add("<");

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);
        Map<String, String> environment = processBuilder.environment();
        environment.put("Path", environment.get("Path") + exeDir);
        processBuilder.directory(new File(workDir));
        try {
            processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        processBuilder.command().clear();
        command.clear();
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        command.add("java.exe");
        command.add(javaClass);

        processBuilder.command(command);
        try {
            processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
