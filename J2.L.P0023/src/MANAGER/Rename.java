package MANAGER;

import GUI.MyFileBrowserFrame;
import GUI.RenameDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Admin
 */
public final class Rename {

    public Rename(MyFileBrowserFrame mainFrame, RenameDialog findFrame) {
        findKeyword(mainFrame, findFrame);
        cancelFind(findFrame);
        checkEmptyFind(mainFrame, findFrame);
    }

    // set action when ok botton clicked
    public void findKeyword(MyFileBrowserFrame mainFrame, RenameDialog findFrame) {
        String pathParent = ((Node) mainFrame.getTree().getLastSelectedPathComponent()).getParent().getParent();
        String oldName = ((Node) mainFrame.getTree().getLastSelectedPathComponent()).getParent().getName();

        findFrame.getTxtFind().setText(oldName);
        findFrame.getFindButton().addActionListener((ActionEvent e) -> {
            String newName = findFrame.getTxtFind().getText();
            //check oldname equal newname to rename
            if (!oldName.equals(newName)) {
                mainFrame.setFileRename(new File(pathParent + "/" + findFrame.getTxtFind().getText()));
                mainFrame.setRename(true);
            }
            
            findFrame.setVisible(false);
        });
    }

    //when user click button cancel
    public void cancelFind(RenameDialog findFrame) {
        findFrame.getCancelButton().addActionListener((ActionEvent e) -> {
            findFrame.setVisible(false);
        });
    }

    //check user not input
    public void checkEmptyFind(MyFileBrowserFrame mainFrame, RenameDialog findFrame) {
        findFrame.getTxtFind().addCaretListener((CaretEvent e) -> {
            String oldName = ((Node) mainFrame.getTree().getLastSelectedPathComponent()).getParent().getName();
            if (findFrame.getTxtFind().getText().trim().isEmpty() || findFrame.getTxtFind().getText().equals(oldName)) {
                findFrame.getFindButton().setEnabled(false);
            } else {
                findFrame.getFindButton().setEnabled(true);
            }
        });
    }
}
