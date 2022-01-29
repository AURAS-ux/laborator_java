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
    private JLabel gradeLabel;
    private JComboBox gradeBox;
    private JFrame owner;


    public JPanel getMainPanel(){return mainPanel;}
    public void _initUI(){
        owner.setSize(new Dimension(400,150));
        owner.setResizable(false);
        numeLabel.setForeground(Color.white);
        prenumeLabel.setForeground(Color.white);
        gradeLabel.setForeground(Color.white);
    }
    public GradeStudnetForm(JFrame owner,Profesor prof) {
        this.owner = owner;
        _initUI();
        FileDataManager fileDataManager = new FileDataManager();
        Curs[] curses=fileDataManager.createCoursesData();



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
                FileDisplay fd=new FileDisplay();
                boolean studFound=false;
                for(Curs c:curses) {
                    if (c.profu.equals(prof)) {
                        for (Student s : c.studenti) {
                            //System.out.println(s);
                            if (s.nume.equals(nameField.getText()) && s.prenume.equals(prenumeField.getText())) {
                                 c.nota.put(s, Integer.parseInt((String) gradeBox.getSelectedItem()));
                                studFound = true;
                            }
                        }
                    }
                }
                if(studFound){
                fd.displayCourses(curses);
                nameField.setText("");
                prenumeField.setText("");
                gradeBox.setSelectedIndex(0);
                }
                else JOptionPane.showMessageDialog(null,"Nu s a putut gasi studentul");
            }
        });
    }
}
