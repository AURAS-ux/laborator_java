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
    private JButton afiseazaNoteButton;
    private JButton calcMedie;
    private JButton afiseazaRestanteButton;
    public JFrame owner;

    public StudentForm(User u,JFrame owner) {
        FileDataManager fileDataManager = new FileDataManager();
        Curs[] curses=fileDataManager.createCoursesData();
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
                StringBuilder courseText= new StringBuilder();
                boolean studFound = false;
                int nota=0;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            studFound = true;
                            nota=c.nota.get(s);
                        }
                    }
                    if (studFound) {
                        if (nota != 0) {
                            courseText.append(c.nume);
                            courseText.append("\n");
                        }
                    }
                }
                preview_pane.setText(courseText.toString());
            }
        });



        afiseazaNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 StringBuilder courseText= new StringBuilder();
                boolean studFound = false;
                int nota=0;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                            studFound = true;
                            nota=c.nota.get(s);
                        }
                    }
                    if (studFound) {
                        if(nota!=0) {
                            courseText.append("Aveti nota " + nota + " la cursul " + c.nume);
                            courseText.append("\n");
                        }else {
                            courseText.append("Nu sunteti inscris la cursul " + c.nume);
                            courseText.append("\n");
                        }
                    }
                }
                preview_pane.setText(courseText.toString());
            }

        });


    calcMedie.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder courseText= new StringBuilder();
            boolean studFound=false;
            float medie =0.f;
            int nota=0;
            int sumNote=0;
            int nrNote=0;
            for(Curs c:curses){
                for(Student s: c.studenti){
                    if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                        studFound = true;
                        nota=c.nota.get(s);
                    }
                }
                if(studFound) {
                    sumNote += nota;
                    nrNote++;
                }
            }
            if(nota!=0) {
                medie = (float) sumNote / (float) nrNote;
                courseText.append("Media dvs este:" + medie);
            }
            preview_pane.setText(courseText.toString());
        }
    } );
        afiseazaRestanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder courseText= new StringBuilder();
                int nota=0;
                boolean studFound=false;
                for(Curs c:curses) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(finalUserFirstName) == 0 && s.prenume.compareTo(finalUserLastName) == 0) {
                        studFound = true;
                        nota = c.nota.get(s);
                        }
                    }
                    if (studFound) {
                        if (nota < 5) {
                            System.out.println(nota + c.nume);
                            courseText.append("Aveti restanta la cursul:" + c.nume);
                            courseText.append("\n");
                        }
                    }
                }
                preview_pane.setText(courseText.toString());
            }
        });
    }

    private void _initUI(){
        this.owner.setResizable(false);
        welcomeLabel.setForeground(Color.white);
        printCursuriButton.setForeground(Color.decode("#ecf8d1"));
        preview_pane.setForeground(Color.white);
        printLabel.setForeground(Color.white);
        afiseazaNoteButton.setForeground(Color.white);
        calcMedie.setForeground(Color.white);
        afiseazaRestanteButton.setForeground(Color.white);
        owner.setSize(new Dimension(500,650));
        preview_pane.setEditable(false);
    }
    public JPanel getPanel4(){
        return mainPanel;
    }
}
