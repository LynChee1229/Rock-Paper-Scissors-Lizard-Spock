package JavaGroupAssignment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame implements ActionListener {
    private final JButton proceedBtn;
    private final JComboBox colorChoices;
    private final JPanel theFirstRow, contentPanel,ChildContent1,ChildContent2,ChildContent3, btnPanel;
    private final Font titleFont = new Font("Times New Roman", Font.BOLD,35);
    private final Font fontInContent = new Font("Verdana", Font.PLAIN, 25);
    private static Color clr = new Color(255,229,204);

    public static void main (String[]args){
        WelcomeScreen frame = new WelcomeScreen();
        frame.setSize(1000,660);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WELCOME!!!");
    }

    public WelcomeScreen(){
        JLabel title = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
        title.setFont(titleFont);
        title.setOpaque(true);
        title.setBackground(new Color(235, 154, 26));
        title.setPreferredSize(new Dimension(1200,58));

        contentPanel = new JPanel(new GridLayout(2,1));

            theFirstRow = new JPanel(new GridLayout(3,1));

                ChildContent1 = new JPanel();
                JLabel welcome = new JLabel("Welcome!");
                welcome.setFont(fontInContent);
                welcome.setHorizontalAlignment(JLabel.CENTER);
                ChildContent1.add(welcome);

                ChildContent2 = new JPanel();
                JLabel sentence = new JLabel("Please select the background color before you proceed (compulsory): ");
                sentence.setFont(fontInContent);
                sentence.setHorizontalAlignment(JLabel.CENTER);
                ChildContent2.add(sentence);

                ChildContent3 = new JPanel();
                colorChoices = new JComboBox(new Object[]{"Blue","Red","Yellow","Green","Grey"});
                colorChoices.addActionListener(this);
                colorChoices.setPreferredSize(new Dimension(120,29));
                ChildContent3.add(colorChoices);

            theFirstRow.add(ChildContent1);
            theFirstRow.add(ChildContent2);
            theFirstRow.add(ChildContent3);

        contentPanel.setBorder(new EmptyBorder(50,0,0,0));
        contentPanel.add(theFirstRow);

        btnPanel = new JPanel();
        proceedBtn = new JButton("PROCEED");
        proceedBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        proceedBtn.setPreferredSize(new Dimension(158,42));
        proceedBtn.setBackground(clr);
        proceedBtn.setEnabled(false);
        proceedBtn.addActionListener(this);
        btnPanel.setBorder(new EmptyBorder(30, 30, 100, 30));
        btnPanel.add(proceedBtn);

        setColor(clr);
        setLayout(new BorderLayout());
        add(title,BorderLayout.NORTH);
        add(contentPanel,BorderLayout.CENTER);
        add(btnPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String color = (String) colorChoices.getSelectedItem();
        if(e.getSource()==colorChoices){
            switch (color){
                case "Blue":    clr = new Color(153,204,255);
                                setColor(clr);
                                break;
                case "Red":     clr = new Color(255,153,153);
                                setColor(clr);
                                break;
                case "Yellow":  clr = new Color(255,255,153);
                                setColor(clr);
                                break;
                case "Green":   clr = new Color(204,255,153);
                                setColor(clr);
                                break;
                case "Grey":    clr = new Color(200,200,200);
                                setColor(clr);
                                break;
            }
            proceedBtn.setEnabled(true);
        }

        if (e.getSource()==proceedBtn){
            this.setVisible(false);
            TeamInitialization window = new TeamInitialization();
            window.setSize(1000,660);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setTitle("LET'S PLAY!!");
            window.setColors(clr);
        }
    }

    public void setColor(Color c) {
        ChildContent1.setBackground(c);
        ChildContent2.setBackground(c);
        ChildContent3.setBackground(c);
        theFirstRow.setBackground(c);
        contentPanel.setBackground(c);
        btnPanel.setBackground(c);
    }
}
