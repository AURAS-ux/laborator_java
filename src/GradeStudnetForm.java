import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeStudnetForm {
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton gradeButton;
    private JLabel numeLabel;
    private JLabel prenumeLabel;
    private JTextField nameField;
    private JTextField prenumeField;
    private JFrame owner;


    public JPanel getMainPanel(){return mainPanel;}
    public void _initUI(){
        owner.setSize(new Dimension(400,120));
        owner.setResizable(false);
    }
    public GradeStudnetForm(JFrame owner,Profesor prof) {
        this.owner = owner;
        _initUI();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
        gradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
