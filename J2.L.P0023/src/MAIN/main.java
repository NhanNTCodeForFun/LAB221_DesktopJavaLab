package MAIN;

import GUI.MyFileBrowserFrame;
import MANAGER.Tree;

/**
 *
 * @author Admin
 */
public class main {

    public static void main(String[] args) {
        MyFileBrowserFrame myFileBrowserFrame = new MyFileBrowserFrame("File Browser");
        myFileBrowserFrame.setVisible(true);
        Tree tree = new Tree(myFileBrowserFrame);
    }
}
