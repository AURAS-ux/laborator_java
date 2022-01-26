import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StudentForm {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton printCursuriButton;
    private JTextPane preview_pane;
    private JLabel printLabel;
    public JFrame owner;

    public StudentForm(User u,JFrame owner) {
        this.owner=owner;
        String userFirstName = new String();
        String userLastName = new String();
        for(Map.Entry<String,String>user:u.menuStrategy.getAccountHolderInformation().entrySet()){
            userFirstName = user.getKey();
            userLastName = user.getValue();
        }
        welcomeLabel.setText("Welcome "+ userFirstName +" "+ userLastName);
        _initUI();
        String finalUserFirstName = userFirstName;
        String finalUserLastName = userLastName;
        printCursuriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDataManager fileDataManager = new FileDataManager();
                Curs[] curses=fileDataManager.createCoursesData();
                StringBuilder courseText= new StringBuilder();
                boolean studFound = false;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            studFound = true;
                        }
                    }
                    if (studFound) {
                        courseText.append(c.nume);
                        courseText.append("\n");
                    }
                }
                preview_pane.setText(courseText.toString());
            }
        });
    }

    private void _initUI(){
        welcomeLabel.setForeground(Color.white);
        printCursuriButton.setForeground(Color.decode("#ecf8d1"));
        preview_pane.setForeground(Color.white);
        printLabel.setForeground(Color.white);
        owner.setSize(new Dimension(500,430));
    }
    public JPanel getPanel4(){
        return mainPanel;
    }
}
