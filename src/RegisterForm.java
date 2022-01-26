import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JButton backToLoginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel userName;
    private JTextField NameField;
    private JTextField prenumeField;
    private JLabel prenumField;
    private JLabel welcomeLabel;
    private JFrame owner;


    public JPanel getPanel3(){
        return mainPanel;
    }

    private void _initUI(){
        usernameLabel.setForeground(Color.white);
        passwordLabel.setForeground(Color.white);
        registerButton.setForeground(Color.white);
        backToLoginButton.setForeground(Color.white);
        userName.setForeground(Color.white);
        prenumField.setForeground(Color.white);
        welcomeLabel.setForeground(Color.white);
    }

    public RegisterForm(JFrame owner) {
        this.owner = owner;
        owner.setSize(new Dimension(525,300));
        this._initUI();
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==backToLoginButton){
                    mainPanel.setVisible(false);
                    owner.setContentPane(new LoginForm(owner).getMainPanel());
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==registerButton){
                    int res = Application.getInstance().register(usernameField.getText(), new String(passwordField.getPassword()),NameField.getText(),prenumeField.getText());
                    if(res==0){
                        JOptionPane.showMessageDialog(null,"Error");
                    }else if(res==-1){
                        JOptionPane.showMessageDialog(null,"User already exists");
                    }else if(res == -2 || res == -3){
                        usernameField.setText("");
                        passwordField.setText("");
                        NameField.setText("");
                        prenumeField.setText("");
                    }
                }
            }
        });
    }


}
