import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.border.*;

public class LoginForm {
    public LoginForm(JFrame owner) {
        this.owner = owner;
        this.initUI();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Am apasat butonul de login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));
                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType() ==UserAccountType.TEACHER) {
                            owner.setContentPane(new TeacherForm(owner,Application.getInstance().currentUser).getPanel1());

                        }else if (Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT){
                            owner.setContentPane(new StudentForm(Application.getInstance().currentUser,owner).getPanel4());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==registerButton){
                    System.out.println("Am apasat butonul de register");
                    try {
                        mainPanel.setVisible(false);
                        owner.setContentPane(new RegisterForm(owner).getPanel3());
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        });
    }

    private void initUI(){
        this.owner.setSize(new Dimension(500,250));
        this.owner.setResizable(false);
        this.registerButton.setForeground(Color.WHITE);
        this.lblUsername.setForeground(Color.white);
        this.passField.setForeground(Color.white);
        this.btnLogin.setForeground(Color.white);
        ImageIcon ico = new ImageIcon("UserLogin.png");
        imageLabel.setIcon(scaleImg(ico,50,50));
        Border border = imageLabel.getBorder();
        Border margin = new EmptyBorder(0,0,0,100);
        imageLabel.setBorder(new CompoundBorder(border, margin));
    }
    private ImageIcon scaleImg(ImageIcon im, int weight,int height){
        Image newImg = im.getImage();
        newImg= newImg.getScaledInstance(weight,height, Image.SCALE_AREA_AVERAGING);
        ImageIcon newIco = new ImageIcon(newImg);
        return newIco;
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public Dimension getOwnerDim(){return new Dimension(500,250);}
    public boolean isOpen(){return this.lblUsername.isShowing();}

    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton registerButton;
    private JLabel passField;
    private JLabel imageLabel;
    private JFrame owner;
}
