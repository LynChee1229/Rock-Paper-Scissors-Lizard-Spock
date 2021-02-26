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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class ResultScreen extends JFrame implements ActionListener {

    // declare GUI components
    private JButton exitBtn;
    private JPanel panelWest, panelEast, panelCenter, panelCenterA, panelCenterB, panelSouth, panelSouthA, panelSouthB;
    private JLabel Pic, teamScore1, teamScore2, congratsLabel, winningTeam;

    // create constant variables
    private final ImageIcon congratsPic = new ImageIcon(getClass().getResource("image/congrats.png"));

    // declare global variables (shared across all instances)
    private static Color clr;
    private static JTable table1, table2;

    public static void main(String[] args) {
        ResultScreen winFrame = new ResultScreen();
        winFrame.setSize(1000,660);
        winFrame.setVisible(true);                                          // make the frame appear on the screen
        winFrame.setResizable(false);                                       // disable frame resizing
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // specify the option for the close button (Exit the application)
        winFrame.setTitle("Congratulation!!!");
    }

    // constructor without parameter (will be invoked when the object of ResultScreen has been created)
    public ResultScreen() {
        setLayout(new BorderLayout());

        // set the "title banner" (north panel)
        JLabel titleLabel = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
            titleLabel.setFont(new Font("Times New Roman",Font.BOLD,35));
            titleLabel.setOpaque(true);                                         // paint every pixel (no transparent pixels)
            titleLabel.setBackground(new Color(235, 154, 26));
            titleLabel.setPreferredSize(new Dimension(1200,58));

        // west panel (default flow layout) - display the congrats picture
        panelWest = new JPanel();
            panelWest.add(Pic = new JLabel(congratsPic));
            panelWest.setBorder(new EmptyBorder(80,25,0,25));           // transparent border for set the margin

        // east panel (default flow layout) - display the exit button
        panelEast = new JPanel();
            panelEast.add(exitBtn = new JButton("Exit"));
            panelEast.setBorder(new EmptyBorder(20, 20, 20, 20));       // transparent border for set the margin
            exitBtn.setMargin(new Insets(5, 25, 5, 25));                // set the distance between the border of button and the text of button (padding)
            exitBtn.setFont(new Font("Verdana", Font.PLAIN, 18));

        // center panel (grid layout)
        panelCenter = new JPanel(new GridLayout(2, 1));

            // part A for display both teams score
            panelCenterA = new JPanel(new GridLayout(1,2));
                panelCenterA.add(teamScore1 = new JLabel("Team 1 : "));
                    teamScore1.setFont(new Font("Verdana", Font.PLAIN, 25));
                panelCenterA.add(teamScore2 = new JLabel("Team 2 : "));
                    teamScore2.setFont(new Font("Verdana", Font.PLAIN, 25));
                panelCenterA.setBorder(new EmptyBorder(0,129,0,0));         // transparent border for set the margin

            // part B for display the winning team
            panelCenterB = new JPanel();
                panelCenterB.add(congratsLabel = new JLabel("Congratulation!!! ", JLabel.CENTER));
                    congratsLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
                panelCenterB.add(winningTeam = new JLabel("", JLabel.CENTER));
                    winningTeam.setFont(new Font("Verdana", Font.BOLD, 42));
                    winningTeam.setForeground(new Color(153,0,0));                   // style the font color

        panelCenter.add(panelCenterA);
        panelCenter.add(panelCenterB);
        panelCenter.setBorder(new EmptyBorder(50,0,80,0));          // transparent border for set the margin


        // south panel (grid layout) - display both team result in tables
        panelSouth = new JPanel();
            GridLayout gridL = new GridLayout(2,1);
            gridL.setVgap(24);          // Set the vertical gap between components to the specified value
            panelSouth.setLayout(gridL);

            // set up tableA for team one result
            panelSouthA = new JPanel();
                table1 = new JTable(3, 5);
                table1.setEnabled(false);       // (disable) used for show result only, not for any input
                table1.setRowHeight(36);
                table1.setFont(new Font("Verdana", Font.PLAIN, 21));
                table1.setBorder(BorderFactory.createLineBorder(Color.black));

            // set up tableB for team two result
            panelSouthB = new JPanel();
                table2 = new JTable(3,5);
                table2.setEnabled(false);       // (disable) used for show result only, not for any input
                table2.setRowHeight(36);
                table2.setFont(new Font("Verdana", Font.PLAIN, 21));
                table2.setBorder(BorderFactory.createLineBorder(Color.black));

                // set the center alignment (and column width) for all elements inside the table
                DefaultTableCellRenderer ctr = new DefaultTableCellRenderer();
                ctr.setHorizontalAlignment(SwingConstants.CENTER);
                for (int columnIndex = 0; columnIndex < table1.getColumnCount(); columnIndex++) {
                    table1.getColumnModel().getColumn(columnIndex).setCellRenderer(ctr);
                    table1.getColumnModel().getColumn(columnIndex).setPreferredWidth(160);
                    table2.getColumnModel().getColumn(columnIndex).setCellRenderer(ctr);
                    table2.getColumnModel().getColumn(columnIndex).setPreferredWidth(160);
                }
                table1.getColumnModel().getColumn(0).setPreferredWidth(290);
                table2.getColumnModel().getColumn(0).setPreferredWidth(290);

                // name the column header of team one's result table
                table1.setValueAt("Player 1",0,0);
                table1.setValueAt("Round 1",0,1);
                table1.setValueAt("Round 2",0,2);
                table1.setValueAt("Round 3",0,3);
                table1.setValueAt("Total",0,4);

                // name the column header of team two's result table
                table2.setValueAt("Player 2",0,0);
                table2.setValueAt("Round 1",0,1);
                table2.setValueAt("Round 2",0,2);
                table2.setValueAt("Round 3",0,3);
                table2.setValueAt("Total",0,4);

            panelSouthA.add(table1);
            panelSouthB.add(table2);
        panelSouth.add(panelSouthA);
        panelSouth.add(panelSouthB);
        panelSouth.setBorder(new EmptyBorder(0,0,45,0));        // transparent border for set the margin


        setAllBackground(new Color(255,229,204));       // call the method used to set the background color of the entire frame

        add(titleLabel, BorderLayout.NORTH);        // added to frame
        add(panelWest, BorderLayout.WEST);          // added to frame
        add(panelEast, BorderLayout.EAST);          // added to frame
        add(panelCenter, BorderLayout.CENTER);      // added to frame
        add(panelSouth, BorderLayout.SOUTH);        // added to frame

        exitBtn.addActionListener(this);         // for handling action event (exit the program)
    }

    // handle all the actions of components
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            System.exit(0);          //  cause the Java Virtual Machine to terminate completely.
        }
    }

    // set all players' name (passed from previous frame)
    public void setName(String t1p1, String t1p2, String t2p1, String t2p2) {
        table1.setValueAt("Team 1 : " + t1p1,1,0);
        table1.setValueAt("Team 2 : " + t2p1,2,0);
        table2.setValueAt("Team 1 : " + t1p2,1,0);
        table2.setValueAt("Team 2 : " + t2p2,2,0);
    }

    // display the game result (passed from previous frame)
    public void setResult(int[] team1point, int[] team2point ) {

        // declare needed local variables
        int teamOne, teamTwo, y=1, team1p1=0, team2p1=0, team1p2=0, team2p2=0;

        // retrieve points from array and set into both table (Player One for both team)
        for(int x=0; x<3; x++) {
            table1.setValueAt(team1point[x], 1,(x+1));
            table1.setValueAt(team2point[x], 2, (x+1));
            team1p1 += team1point[x];
            team2p1 += team2point[x];
            table1.setValueAt(team1p1, 1, 4);
            table1.setValueAt(team2p1, 2, 4);
        }

        // retrieve points from array and set into both table (Player Two for both team)
        for(int x=3; x<6; x++) {
            table2.setValueAt(team1point[x], 1,(y));
            table2.setValueAt(team2point[x], 2, (y));
            team1p2 += team1point[x];
            team2p2 += team2point[x];
            table2.setValueAt(team1p2, 1, 4);
            table2.setValueAt(team2p2, 2, 4);
            y++;
        }

        // calculate the total points for each team
        teamOne = team1p1 + team1p2;
        teamTwo = team2p1 + team2p2;

        // display the total points for each team
        teamScore1.setText("Team 1 : " + teamOne);
        teamScore2.setText("Team 2 : " + teamTwo);

        // check and display the winning team (might be a tie)
        if(teamOne > teamTwo){
            winningTeam.setText("Team 1");
        }
        else if(teamOne < teamTwo){
            winningTeam.setText("Team 2");
        }
        else if(teamOne == teamTwo){
            congratsLabel.setText("");              // hide the congrats label if it's a tie
            winningTeam.setText("It's a draw!!!");
            Pic.setIcon(new ImageIcon(getClass().getResource("image/tieGame.png")));        // change the congrats picture to a tie picture
        }
    }

    // method used to set the background color of the entire frame
    public void setAllBackground (Color c) {
        clr = c;            // assign the color to global variable (can be passed to next frame later)

        panelWest.setBackground(c);
        panelEast.setBackground(c);
        panelCenter.setBackground(c);
        panelCenterA.setBackground(c);
        panelCenterB.setBackground(c);
        panelSouth.setBackground(c);
        panelSouthA.setBackground(c);
        panelSouthB.setBackground(c);
    }
}
