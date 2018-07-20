package com.mabellou;

import org.junit.Test;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {


    @Test
    public void shoudOpenFile(){
        File file = new File("pom.xml");
        assertThat(file.exists()).isTrue();
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        assertThat(file.isFile()).isTrue();
        assertThat(file.isDirectory()).isFalse();
        System.out.println("Return the number of byte in the file (long): " + file.length());
        System.out.println("Last modified time: " + Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Test
    public void deleteOnlyEmptyDirectory(){
        File dir = new File("testDir");
        boolean result = dir.mkdir();
        assertThat(result).isTrue();
        System.out.println("The result of creating a dir is: " + result);
        result = dir.delete();
        assertThat(result).isTrue();
        System.out.println("The file has been deleted correctly: " + result);

        File nonEmptydir = new File("testdirNotEmpty");
        // Constructor only with parent and child -> not more -> more only in Path
        File fileInDir = new File("testdirNotEmpty", "dirInDir");
        result = fileInDir.mkdirs();
        assertThat(result).isTrue();
        System.out.println("The result of creating a dir in dir is: " + result);

        result = nonEmptydir.delete();
        assertThat(result).isFalse();
        System.out.println("The file has not been deleted correctly: " + result);

        fileInDir.delete();
        nonEmptydir.delete();
    }

    @Test
    public void listFiles(){
        File file = new File(".");
        System.out.println(Arrays.toString(file.listFiles()));
    }
}
