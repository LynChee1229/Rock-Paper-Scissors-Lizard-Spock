/*
 * Class Section: TT6V & TC1V
 * Trimester 2 2020/21
 * Members:
 * Chan Lin Chee         |   1191202546  |   016 357 7219
 * Chew Zhi Peng         |   1191202464  |   016 640 1837
 * Ho Ko Ee              |   1191202709  |   012 533 0788
 * Matthew Labial John   |   1191202516  |   010 555 9530
 * Yong Jing Ping        |   1191202279  |   010 795 9861
 */



// group all of the related classes
package JavaGroupAssignment;

// import necessary packages for needed classes
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TeamInitialization extends JFrame implements ActionListener {

    // declare GUI components
    private JLabel team1, team2, playerName1, playerName2, t1p1Label, t1p2Label, t2p1Label, t2p2Label;
    private JPanel readyPanel, teamOnePanel, teamTwoPanel, team1p1, team1p2, team2p1, team2p2, btn1Panel, btn2Panel, notiPanel, readyBoth;
    private JButton teamOneReady, teamTwoReady, startBtn;
    private JTextField t1p1, t1p2, t2p1, t2p2;
    private JLabel readyT1, readyT2, readyLabel;

    // create constant variables
    private final Font titleFont = new Font("Times New Roman", Font.BOLD, 35);
    private final Font contentFont = new Font("Verdana", Font.PLAIN, 25);

    // declare global variables (shared across all instances)
    private static String memberT1p1, memberT1p2, memberT2p1, memberT2p2;
    private static int status;
    private static Color clr;

    public static void main(String[] args){
        // set the frame
        TeamInitialization window = new TeamInitialization();
        window.setSize(1000,660);
        window.setVisible(true);                                        // make the frame appear on the screen
        window.setResizable(false);                                     // disable frame resizing
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          // specify the option for the close button (Exit the application)
        window.setTitle("LET'S PLAY!!");
    }

    // constructor without parameter (will be invoked when the object of TeamInitialization has been created)
    public TeamInitialization(){
        setLayout(new BorderLayout());

        // set the "title banner" (north panel)
        JLabel titleLabel = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
            titleLabel.setFont(titleFont);
            titleLabel.setOpaque(true);                                         // paint every pixel (no transparent pixels)
            titleLabel.setBackground(new Color(235, 154, 26));
            titleLabel.setPreferredSize(new Dimension(1200,58));

        // center panel (default flow layout)
        readyPanel = new JPanel();

            // set up needed components for team one's part   (left side)
            team1p1 = new JPanel();
                team1p1.add(t1p1Label = new JLabel("Player 1: "));
                team1p1.add(t1p1 = new JTextField(24));
            team1p2 = new JPanel();
                team1p2.add(t1p2Label = new JLabel("Player 2: "));
                team1p2.add(t1p2 = new JTextField(24));
            btn1Panel = new JPanel();
                btn1Panel.add(teamOneReady = new JButton("Enter"));

            // set up needed components for team two's part (right side)
            team2p1 = new JPanel();
                team2p1.add(t2p1Label = new JLabel("Player 1: "));
                team2p1.add(t2p1 = new JTextField(24));
            team2p2 = new JPanel();
                team2p2.add(t2p2Label = new JLabel("Player 2: "));
                team2p2.add(t2p2 = new JTextField(24));
            btn2Panel = new JPanel();
                btn2Panel.add(teamTwoReady = new JButton("Enter"));

            // left side : set up a panel to contain all the components for team one's registration (grid layout)
            teamOnePanel = new JPanel(new GridLayout(5,1));
                teamOnePanel.add(team1 = new JLabel("Team 1", JLabel.CENTER));
                teamOnePanel.add(playerName1 = new JLabel("Enter Player Names: ", JLabel.CENTER));
                teamOnePanel.add(team1p1);
                teamOnePanel.add(team1p2);
                teamOnePanel.add(btn1Panel);
                teamOnePanel.setBorder(new EmptyBorder(85,0,0,48));             // transparent border for set the margin

            // right side : set up a panel to contain all the components for team two's registration (grid layout)
            teamTwoPanel = new JPanel(new GridLayout(5,1));
                teamTwoPanel.add(team2 = new JLabel("Team 2", JLabel.CENTER));
                teamTwoPanel.add(playerName2 = new JLabel("Enter Player Names: ", JLabel.CENTER));
                teamTwoPanel.add(team2p1);
                teamTwoPanel.add(team2p2);
                teamTwoPanel.add(btn2Panel);
                teamTwoPanel.setBorder(new EmptyBorder(85,48,0,0));             // transparent border for set the margin

        readyPanel.add(teamOnePanel);
        readyPanel.add(teamTwoPanel);


        // south panel (border layout)
        notiPanel = new JPanel(new BorderLayout());
        notiPanel.add(readyT1 = new JLabel("", JLabel.CENTER), BorderLayout.NORTH);     // label prepared for display team one players' name
        notiPanel.add(readyT2 = new JLabel("", JLabel.CENTER), BorderLayout.CENTER);    // label prepared for display team two players' name
        notiPanel.add(readyBoth = new JPanel(), BorderLayout.SOUTH);                        // panel prepared for contain text and button
            readyBoth.add(readyLabel = new JLabel("Are you ready? "));
            readyBoth.add(startBtn = new JButton("START!!!"));
            startBtn.setEnabled(false);             // disable button (enable after all players have registered their name)
        notiPanel.setBorder(new EmptyBorder(0,0,50,0));     // transparent border for set the margin


        setColors(new Color(255,229,204));      // call the method used to set the background color of the entire frame

        add(titleLabel, BorderLayout.NORTH);            // added to frame
        add(readyPanel, BorderLayout.CENTER);           // added to frame
        add(notiPanel, BorderLayout.SOUTH);             // added to frame

        // style the font for necessary components
        titleLabel.setFont(titleFont);
        team1.setFont(contentFont);
        team2.setFont(contentFont);
        playerName1.setFont(contentFont);
        playerName2.setFont(contentFont);
        t1p1Label.setFont(contentFont);
        t1p2Label.setFont(contentFont);
        t2p1Label.setFont(contentFont);
        t2p2Label.setFont(contentFont);
        readyT1.setFont(contentFont);
        readyT2.setFont(contentFont);
        readyLabel.setFont(contentFont);

        teamOneReady.addActionListener(this);           // for handling action event (team one register name)
        teamTwoReady.addActionListener(this);           // for handling action event (team two register name)
        startBtn.addActionListener(this);               // for handling action event (go to next page)
    }

    // handle all the actions of components
    public void actionPerformed(ActionEvent e) {

        // get the names of all players
        memberT1p1 = t1p1.getText();    memberT1p2 = t1p2.getText();
        memberT2p1 = t2p1.getText();    memberT2p2 = t2p2.getText();

        // if event occurred on the team one button (team one done register)
        if(e.getSource() == teamOneReady){
            
            // check if any text field is empty, prompt player to insert name (Otherwise, display players' name)
            if(memberT1p1.isEmpty() || memberT1p2.isEmpty())
                readyT1.setText("Team 1 : Please insert your name(s)!!");
            else {
                readyT1.setText("Hi Team 1 : [player 1] " + memberT1p1 + ", [player 2] " + memberT1p2);
                teamOneReady.setEnabled(false);         // (disable) cannot modify name until all players done insert their names
                status++;                               // no text field empty, means team two ready
            }
        }

        // if event occurred on the team two button (team two done register)
        if(e.getSource() == teamTwoReady) {

            // check if any text field is empty, prompt player to insert name (Otherwise, display players' name)
            if(memberT2p1.isEmpty() || memberT2p2.isEmpty())
                readyT2.setText("Team 2 : Please insert your name(s)!!");
            else {
                readyT2.setText("Hi Team 2 : [player 1] " + memberT2p1 + ", [player 2] " + memberT2p2);
                teamTwoReady.setEnabled(false);         // (disable) cannot modify name until all players done insert their names
                status++;                               // no text field empty, means team two ready
            }
        }

        // if both team are ready
        if(status >= 2){
            teamOneReady.setEnabled(true);      // (enable) allow team one players to modify name
            teamTwoReady.setEnabled(true);      // (enable) allow team two players to modify name
            startBtn.setEnabled(true);          // (enable) allow players go to next page when ready
        }

        // if event occurred on the start button (player want to start the game after registration)
        if(e.getSource() == startBtn) {

            // double check if any player's name is empty
            if(memberT1p1.isEmpty() || memberT1p2.isEmpty())
                readyT1.setText("Team 1 : Please insert your name(s)!!");
            else if(memberT2p1.isEmpty() || memberT2p2.isEmpty())
                readyT2.setText("Team 2 : Please insert your name(s)!!");
            // if both team are ready:
            else {
                this.setVisible(false);         // hide current frame

                // set up a new frame (next)
                BattleScreen frame = new BattleScreen();
                frame.setSize(1000,660);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("BATTLE!!!");
                frame.setAllColor(clr);     // call the method of BattleScreen class to set the background color of the next frame

                // call the method of BattleScreen class to set the players' name
                frame.setPlayerName(memberT1p1, memberT1p2, memberT2p1, memberT2p2);
            }
        }
    }

    // method used to set the background color of the entire frame
    public void setColors(Color c) {
        clr = c;        // assign the color to global variable (can be passed to next frame later)

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
