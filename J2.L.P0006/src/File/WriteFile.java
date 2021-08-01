/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 *
 * @author Admin
 */
public class WriteFile {

    private String path;
    private String content;

    public WriteFile() {
    }

    public WriteFile(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public void Write() {
        try {
            PrintWriter fw = new PrintWriter(new FileOutputStream(path), true);
            String s[] = content.split("\n");
            for (int i = 0; i < s.length; i++) {
                String string = s[i];
                fw.print(string + "\r\n");
            }
            fw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
