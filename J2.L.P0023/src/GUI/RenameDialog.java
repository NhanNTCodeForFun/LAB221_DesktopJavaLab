package GUI;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

/**
 *
 * @author Admin
 */
public class RenameDialog extends JDialog {

    private final JLabel label = new JLabel("New name:");
    private final JTextField txtFind = new JTextField(20);
    private final JButton findButton = new JButton("Rename");
    private final JButton cancelButton = new JButton("Cancel");

    public RenameDialog(Frame owner, boolean modal) {
        super(owner, modal);
        createAndShowGUI();
        setResizable(false);
    }

    private void createAndShowGUI() {
        ButtonGroup buttonGroup = new ButtonGroup();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(txtFind))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(findButton)
                        .addComponent(cancelButton)
                )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(label)
                        .addComponent(txtFind)
                        .addComponent(findButton))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup())
                        .addComponent(cancelButton)
                )
        );

        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JTextField getTxtFind() {
        return txtFind;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
