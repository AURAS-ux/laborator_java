import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TeacherForm {
    public JPanel getPanel1() {
        return mainPanel;
    }
    public void _initUI(){
        this.owner.setSize(new Dimension(600,600));
        this.owner.setResizable(false);
        ImageIcon ico = new ImageIcon("D:\\JAVA\\laborator_java\\teacher.jpg");
        teacherIcon.setIcon(scaleImg(ico,400,300));
        Border border = teacherIcon.getBorder();
        Border margin = new EmptyBorder(0,0,0,150);
        teacherIcon.setBorder(new CompoundBorder(border, margin));
        welcomeLabel.setForeground(Color.white);
    }

    private ImageIcon scaleImg(ImageIcon im, int weight,int height){
        Image newImg = im.getImage();
        newImg= newImg.getScaledInstance(weight,height, Image.SCALE_AREA_AVERAGING);
        ImageIcon newIco = new ImageIcon(newImg);
        return newIco;
    }

    public TeacherForm(JFrame owner, User u) {
        this.owner=owner;
        _initUI();

        FileDataManager fileDataManager = new FileDataManager();
        Curs[] curses=fileDataManager.createCoursesData();
        this.owner=owner;
        String userFirstName = new String();
        String userLastName = new String();
        for(Map.Entry<String,String>user:u.menuStrategy.getAccountHolderInformation().entrySet()){
            userFirstName = user.getKey();
            userLastName = user.getValue();
        }
        StringBuffer text = new StringBuffer();
        text.append("Welcome "+"\n"+ userFirstName +" "+ userLastName);
        welcomeLabel.setText(text.toString());
        _initUI();
        String finalUserFirstName = userFirstName;
        String finalUserLastName = userLastName;
        listeazaCursuriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder courseText= new StringBuilder();
                boolean profFound=false;
                for(Curs c:curses) {
                    if(c.profu.equals(new Profesor(finalUserFirstName,finalUserLastName))){
                        courseText.append(c.nume);
                        courseText.append("\n");
                        profFound=true;
                    }
                }
                if(!profFound)
                    courseText.append("Nu sunteti inregistrat la niciun curs");
                JOptionPane.showMessageDialog(null,courseText);
            }
        });
        listeazaStudentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder courseText= new StringBuilder();
                boolean profFound=false;
                for(Curs c:curses) {
                    if(c.profu.equals(new Profesor(finalUserFirstName,finalUserLastName))) {
                        for (Student s : c.studenti) {
                            courseText.append("STUDENT:" + s.nume+" "+s.prenume);
                            courseText.append("\n");

                        }
                        profFound = true;
                    }
                }
                if(!profFound)
                    courseText.append("Nu aveti studenti inscrisi la acest curs");
                JOptionPane.showMessageDialog(null,courseText);
            }
        });
        noteazaStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder courseText= new StringBuilder();
                boolean profFound=false;
                Profesor prof=new Profesor();
                for(Curs c:curses) {
                    if(c.profu.equals(new Profesor(finalUserFirstName,finalUserLastName))) {
                        profFound = true;
                        prof=c.profu;
                    }
                }
                if(profFound) {
                    mainPanel.setVisible(false);
                    owner.setContentPane(new GradeStudnetForm(owner,prof).getMainPanel());
                    System.out.println(prof);
                }else {
                    JOptionPane.showMessageDialog(null,"Nu sunteti inregistrat la niciun curs");
                }
            }
        });
    }

    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel welcomeLabel;
    private JButton listeazaCursuriButton;
    private JButton noteazaStudentButton;
    private JButton listeazaStudentiButton;
    private JLabel teacherIcon;
    private JFrame owner;
}
