package JavaGroupAssignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TeamInitialization extends JFrame implements ActionListener {
    private final JLabel team1, team2, prompt1, prompt2, t1p1Label, t1p2Label, t2p1Label, t2p2Label;
    private final JPanel readyPanel, teamOnePanel, teamTwoPanel, team1p1, team1p2, team2p1, team2p2, btn1Panel, btn2Panel, notiPanel, readyBoth;
    private final JButton teamOneReady, teamTwoReady, startBtn;
    private final JTextField t1p1, t1p2, t2p1, t2p2;
    private final JLabel readyT1, readyT2, readyLabel;
    private final Font titleFont = new Font("Times New Roman", Font.BOLD, 35);
    private final Font contentFont = new Font("Verdana", Font.PLAIN, 25);
    private static String memberT1p1, memberT1p2, memberT2p1, memberT2p2;
    private static int status;
    private static Color clr;

    public static void main(String[] args){
        TeamInitialization window = new TeamInitialization();
        window.setSize(1000,660);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("LET'S PLAY!!");
    }

    public TeamInitialization(){
        JLabel titleLabel = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(235, 154, 26));
        titleLabel.setPreferredSize(new Dimension(1200,58));

        team1p1 = new JPanel();
            team1p1.add(t1p1Label = new JLabel("Player 1: "));
            team1p1.add(t1p1 = new JTextField(24));
        team1p2 = new JPanel();
            team1p2.add(t1p2Label = new JLabel("Player 2: "));
            team1p2.add(t1p2 = new JTextField(24));
        team2p1 = new JPanel();
            team2p1.add(t2p1Label = new JLabel("Player 1: "));
            team2p1.add(t2p1 = new JTextField(24));
        team2p2 = new JPanel();
            team2p2.add(t2p2Label = new JLabel("Player 2: "));
            team2p2.add(t2p2 = new JTextField(24));

        btn1Panel = new JPanel();
            btn1Panel.add(teamOneReady = new JButton("Enter"));
        btn2Panel = new JPanel();
            btn2Panel.add(teamTwoReady = new JButton("Enter"));

        teamOnePanel = new JPanel(new GridLayout(5,1));
        teamOnePanel.add(team1 = new JLabel("Team 1", JLabel.CENTER));
        teamOnePanel.add(prompt1 = new JLabel("Enter Player Names: ", JLabel.CENTER));
        teamOnePanel.add(team1p1);
        teamOnePanel.add(team1p2);
        teamOnePanel.add(btn1Panel);
        teamOnePanel.setBorder(new EmptyBorder(85,0,0,48));

        teamTwoPanel = new JPanel(new GridLayout(5,1));
        teamTwoPanel.add(team2 = new JLabel("Team 2", JLabel.CENTER));
        teamTwoPanel.add(prompt2 = new JLabel("Enter Player Names: ", JLabel.CENTER));
        teamTwoPanel.add(team2p1);
        teamTwoPanel.add(team2p2);
        teamTwoPanel.add(btn2Panel);
        teamTwoPanel.setBorder(new EmptyBorder(85,48,0,0));

        readyPanel = new JPanel();
        readyPanel.add(teamOnePanel);
        readyPanel.add(teamTwoPanel);

        notiPanel = new JPanel(new BorderLayout());
        notiPanel.add(readyT1 = new JLabel("", JLabel.CENTER), BorderLayout.NORTH);
        notiPanel.add(readyT2 = new JLabel("", JLabel.CENTER), BorderLayout.CENTER);
        notiPanel.add(readyBoth = new JPanel(), BorderLayout.SOUTH);
            readyBoth.add(readyLabel = new JLabel("Are you ready? "));
            readyBoth.add(startBtn = new JButton("START!!!"));
            startBtn.setEnabled(false);
        notiPanel.setBorder(new EmptyBorder(0,0,50,0));

        setColors(new Color(255,229,204));
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(readyPanel, BorderLayout.CENTER);
        add(notiPanel, BorderLayout.SOUTH);

        titleLabel.setFont(titleFont);
        team1.setFont(contentFont);
        team2.setFont(contentFont);
        prompt1.setFont(contentFont);
        prompt2.setFont(contentFont);
        t1p1Label.setFont(contentFont);
        t1p2Label.setFont(contentFont);
        t2p1Label.setFont(contentFont);
        t2p2Label.setFont(contentFont);
        readyT1.setFont(contentFont);
        readyT2.setFont(contentFont);
        readyLabel.setFont(contentFont);

        teamOneReady.addActionListener(this);
        teamTwoReady.addActionListener(this);
        startBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        memberT1p1 = t1p1.getText();    memberT1p2 = t1p2.getText();
        memberT2p1 = t2p1.getText();    memberT2p2 = t2p2.getText();

        if(e.getSource() == teamOneReady){
            if(memberT1p1.isEmpty() || memberT1p2.isEmpty())
                readyT1.setText("Team 1 : Please insert your name(s)!!");
            else {
                readyT1.setText("Hi Team 1 : [player 1] " + memberT1p1 + ", [player 2] " + memberT1p2);
                teamOneReady.setEnabled(false);
                status++;
            }
        }

        if(e.getSource() == teamTwoReady) {
            if(memberT2p1.isEmpty() || memberT2p2.isEmpty())
                readyT2.setText("Team 2 : Please insert your name(s)!!");
            else {
                readyT2.setText("Hi Team 2 : [player 1] " + memberT2p1 + ", [player 2] " + memberT2p2);
                teamTwoReady.setEnabled(false);
                status++;
            }
        }

        if(status >= 2){
            teamOneReady.setEnabled(true);
            teamTwoReady.setEnabled(true);
            startBtn.setEnabled(true);
        }

        if(e.getSource() == startBtn) {
            if(memberT1p1.isEmpty() || memberT1p2.isEmpty())
                readyT1.setText("Team 1 : Please insert your name(s)!!");
            else if(memberT2p1.isEmpty() || memberT2p2.isEmpty())
                readyT2.setText("Team 2 : Please insert your name(s)!!");
            else {
                this.setVisible(false);
                BattleScreen frame = new BattleScreen();
                frame.setSize(1000,660);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("BATTLE!!!");
                frame.setAllColor(clr);
                frame.setPlayerName(memberT1p1, memberT1p2, memberT2p1, memberT2p2);
            }
        }
    }

    public void setColors(Color c) {
        clr = c;

        readyPanel.setBackground(c);
        teamOnePanel.setBackground(c);
        teamTwoPanel.setBackground(c);
        team1p1.setBackground(c);
        team1p2.setBackground(c);
        team2p1.setBackground(c);
        team2p2.setBackground(c);
        btn1Panel.setBackground(c);
        btn2Panel.setBackground(c);
        notiPanel.setBackground(c);
        readyBoth.setBackground(c);
    }
}
