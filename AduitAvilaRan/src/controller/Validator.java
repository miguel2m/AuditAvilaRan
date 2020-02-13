/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;


/**
 *
 * @author Miguelangel
 */
public class Validator {

    /**
     * 
     * @param fileString
     * @return If string is a file and  Readable
     */
    public static boolean isFile(String fileString) {
        Path file = Paths.get(fileString);
        return Files.isRegularFile(file) & Files.isReadable(file);
    }
    
    /**
     * 
     * @param fileString
     * @return If string is a file and  Readable
     */
    public static boolean isDirectory(String fileString) {
        Path file = Paths.get(fileString);
        return Files.isDirectory(file) & Files.isReadable(file);
    }
    
}
