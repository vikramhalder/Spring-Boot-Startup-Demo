package com.example.demo;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Rename {

    private static final String PACKAGE_NAME_NEW = "com.example.demo";
    private static final String PACKAGE_NAME_OLD = "com.example.boilerplate";

    private static List<String> llFile = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        fileReplace(Paths.get(
                new File("pom.xml").getAbsolutePath()),
                new String[]{String.format("<mainClass>%s.Main</mainClass>", PACKAGE_NAME_OLD)},
                new String[]{String.format("<mainClass>%s.Main</mainClass>", PACKAGE_NAME_NEW)}
        );
        Thread.sleep(2000);

        fileReplace(Paths.get(
                new File("src/main/resources/hibernate.cfg.xml").getAbsolutePath()),
                new String[]{String.format("<mapping class=\"%s.Model", PACKAGE_NAME_OLD)},
                new String[]{String.format("<mapping class=\"%s.Model", PACKAGE_NAME_NEW)}
        );
        Thread.sleep(2000);

        setPackageName();
        Thread.sleep(2000);

        listFile(new File("src/main/java/com/example").getAbsolutePath());
        for (String file : llFile) {
            fileReplace(
                    Paths.get(file),
                    new String[]{"package " + PACKAGE_NAME_OLD, "import " + PACKAGE_NAME_OLD},
                    new String[]{"package " + PACKAGE_NAME_NEW, "import " + PACKAGE_NAME_NEW}
            );
            Thread.sleep(1000);
        }
    }

    private static void fileReplace(Path path, String oldStr[], String newStr[]) {
        try {
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            for (int x = 0; x < oldStr.length; x++) {
                content = content.replaceAll(oldStr[x], newStr[x]);
            }
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("Replace " + path.toString() + " " + oldStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void setPackageName() {
        try {
            String oldDir = new File("src/main/java/" + PACKAGE_NAME_OLD.replaceAll("\\.", "/")).getAbsolutePath();
            String newDir = oldDir.replace(PACKAGE_NAME_OLD.replaceAll("\\.", "\\\\"), PACKAGE_NAME_NEW.replaceAll("\\.", "\\\\"));
            Files.move(Paths.get(oldDir), Paths.get(newDir), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Package Rename");
        } catch (Exception ex) {
            System.out.println("Package " + ex.getMessage());
        }
    }

    public static void listFile(String directoryName) {
        List<String> list = new ArrayList<>();
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                llFile.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                listFile(file.getAbsolutePath());
            }
        }
    }
}
