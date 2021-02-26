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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleScreen extends JFrame implements ActionListener {

    // declare GUI components
    private JPanel bigPanel, gamePanel, miniP1, miniP2, panelBtn1, panelBtn2, panelLeft, panelRight, pTable, pFooter;
    private JLabel roundTitle, pic1, pic2, name1, name2;
    private JButton btn1, btn2, btnNext, nextPage;
    private JTable result;

    // create constant variables
    private final ImageIcon[] imageArray = {
            new ImageIcon(getClass().getResource("image/rock.jfif")),
            new ImageIcon(getClass().getResource("image/paper.jfif")),
            new ImageIcon(getClass().getResource("image/scissors.jfif")),
            new ImageIcon(getClass().getResource("image/lizard.jfif")),
            new ImageIcon(getClass().getResource("image/spock.jfif")),
    };
    private final ImageIcon defaultPicture = new ImageIcon(getClass().getResource("image/rule.jfif"));      // default picture
    private final String style = "Verdana";

    // declare/initialize global variables (shared across all instances)
    private static int round = 1, num = 1, p=1, q=1, index1, index2, winNum1=0, winNum2=0, btnP1=0, btnP2=0, plyNum=0, pointIndex=0;
    private static int[] teamOnePoint = new int[6];
    private static int[] teamTwoPoint = new int[6];
    private static String playerT1P1, playerT1P2, playerT2P1, playerT2P2;
    private static Color clr;

    public static void main (String [] args) {
        // set the frame
        BattleScreen frame = new BattleScreen();
        frame.setSize(1000,660);
        frame.setVisible(true);                                         // make the frame appear on the screen
        frame.setResizable(false);                                      // disable frame resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // specify the option for the close button (Exit the application)
        frame.setTitle("BATTLE!!!");
    }

    // constructor without parameter (will be invoked when the object of BattleScreen has been created)
    public BattleScreen() {
        setLayout(new BorderLayout());

        // set the "title banner" (north panel)
        JLabel title = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
            title.setFont(new Font("Times New Roman",Font.BOLD,35));
            title.setOpaque(true);                                          // paint every pixel (no transparent pixels)
            title.setBackground(new Color(235, 154, 26));
            title.setPreferredSize(new Dimension(1200,58));

        // center panel (border layout)
        bigPanel = new JPanel(new BorderLayout());

            // north of bigPanel (a label for the round title)
            roundTitle = new JLabel("Round " + round, JLabel.CENTER);
                roundTitle.setFont(new Font(style,Font.PLAIN,29));
                roundTitle.setPreferredSize(new Dimension(1200,85));

            // center of bigPanel (grid layout)
            JPanel bodyPanel = new JPanel(new GridLayout(2,1));

                // first row of bodyPanel
                gamePanel = new JPanel(new BorderLayout());

                    // set up needed components
                    name1 = new JLabel();
                        name1.setFont(new Font(style, Font.PLAIN, 21));
                        name1.setAlignmentX(Component.CENTER_ALIGNMENT);
                    name2 = new JLabel();
                        name2.setFont(new Font(style, Font.PLAIN, 21));
                        name2.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panelBtn1 = new JPanel();
                        panelBtn1.add(btn1 = new JButton("BOOM!"));
                    panelBtn2 = new JPanel();
                        panelBtn2.add(btn2 = new JButton("BOOM!"));

                    pic1 = new JLabel(defaultPicture);      // set the default picture to the label
                    pic2 = new JLabel(defaultPicture);      // set the default picture to the label
                // after set up all the needed components, the following codes is for assign them to be displayed on the frame

                    // west of game panel (grid layout) 
                    // first column is for player name & button
                    // second column is for display the picture
                    panelLeft = new JPanel(new GridLayout(1,2));
                        // first column of panelLeft (box layout) - group the player One name & button
                        miniP1 = new JPanel();
                            miniP1.setLayout(new BoxLayout(miniP1, BoxLayout.Y_AXIS));               // Y_AXIS : top-to-bottom
                            miniP1.setBorder(new EmptyBorder(85,0,0,0));        // transparent border for set the margin
                            miniP1.add(name1);      miniP1.add(panelBtn1);
                    panelLeft.add(miniP1);
                    panelLeft.add(pic1);

                    // east of game panel (grid layout) 
                    // first column is for display the picture
                    // second column is for player name & button
                    panelRight = new JPanel(new GridLayout(1,2));
                        // second column of panelRight (box layout) - group the player Two name & button
                        miniP2 = new JPanel(new GridLayout(2,1));
                            miniP2.setLayout(new BoxLayout(miniP2, BoxLayout.Y_AXIS));               // Y_AXIS : top-to-bottom
                            miniP2.setBorder(new EmptyBorder(85,0,0,0));        // transparent border for set the margin
                            miniP2.add(name2);      miniP2.add(panelBtn2);
                    panelRight.add(pic2);
                    panelRight.add(miniP2);

                gamePanel.add(panelLeft, BorderLayout.WEST);
                gamePanel.add(panelRight, BorderLayout.EAST);


                // second row of bodyPanel
                pTable = new JPanel();

                    // set up a table for display the result
                    result = new JTable(3,5);
                        result.setBorder(BorderFactory.createLineBorder(Color.black));
                        result.setFont(new Font(style,Font.PLAIN,21));
                        result.setEnabled(false);           // (disable) used for show result only, not for any input
                        result.setRowHeight(58);

                        // set the center alignment (and column width) for all elements inside the table
                        DefaultTableCellRenderer ctr = new DefaultTableCellRenderer();
                        ctr.setHorizontalAlignment(SwingConstants.CENTER);
                        for(int x=0; x<result.getColumnCount(); x++) {
                            result.getColumnModel().getColumn(x).setPreferredWidth(160);
                            result.getColumnModel().getColumn(x).setCellRenderer(ctr);
                        }
                        result.getColumnModel().getColumn(0).setPreferredWidth(290);

                        // name the column header of result table
                        result.setValueAt("Player 1",0,0);
                        result.setValueAt("Round 1",0,1);
                        result.setValueAt("Round 2",0,2);
                        result.setValueAt("Round 3",0,3);
                        result.setValueAt("Total",0,4);

                pTable.add(result);

            bodyPanel.add(gamePanel);
            bodyPanel.add(pTable);

            // south of bigPanel (included two button)
            pFooter = new JPanel();
                pFooter.add(btnNext = new JButton("NEXT PLAYER"));
                btnNext.setVisible(false);              // not visible button (appear after players One have finish their games)
                pFooter.add(nextPage = new JButton("PROCEED"));
                nextPage.setVisible(false);             // not visible button (appear after all players have finish their games, go to see result)
                pFooter.setBorder(new EmptyBorder(0,0,24,0));       // transparent border for set the margin

        bigPanel.add(roundTitle, BorderLayout.NORTH);
        bigPanel.add(bodyPanel, BorderLayout.CENTER);
        bigPanel.add(pFooter, BorderLayout.SOUTH);


        setAllColor(new Color(255,229,204));    // call the method used to set the background color of the entire frame

        add(title, BorderLayout.NORTH);         // added to frame
        add(bigPanel, BorderLayout.CENTER);     // added to frame

        btn1.addActionListener(this);               // for handling action event (get a picture for player One randomly)
        btn2.addActionListener(this);               // for handling action event (get a picture for player Two randomly)
        btnNext.addActionListener(this);            // for handling action event (proceed from player One to player Two)
        nextPage.addActionListener(this);           // for handling action event (go to next page for view result)
    }

    // handle all the actions of components
    public void actionPerformed(ActionEvent e) {
        // create a new random number generator
        Random r = new Random();

        // num: for check the number of both player click the 'BOOM' button
        // should be 3 times for Team One; 3 times for Team Two (if no tie considered)
        if(num<=6){
            
            // if event occurred on the btn1
            if(e.getSource() == btn1) {
                index1 = r.nextInt(imageArray.length);       // get a random number as index between 0-4 (the length of imageArray)
                pic1.setIcon(imageArray[index1]);            // get the picture of the certain index
                num++;                                       // accumulate the number of both player click the 'BOOM' button
                btnP1++;                                     // check if the Team One player has clicked the button
                if(btnP1 != 0){
                    btn1.setEnabled(false);     }            // disable the button (enable after both players clicked the 'BOOM')
            }

            // if event occurred on the btn2
            if(e.getSource() == btn2) {
                index2 = r.nextInt(imageArray.length);       // get a random number as index between 0-4 (the length of imageArray)
                pic2.setIcon(imageArray[index2]);            // get the picture of the certain index
                num++;                                       // accumulate the number of both player click the 'BOOM' button
                btnP2++;                                     // check if the Team Two player has clicked the button
                if(btnP2 != 0) {
                    btn2.setEnabled(false);                  // disable the button (enable after both players clicked the 'BOOM')
                }
            }

            // if both team player have clicked the 'BOOM' button
            if(num%2 != 0) {
                int win = getResult(index1,index2);          // call the getResult method to return a winning result (passing both indexes)

                // if Team One win the game
                if (win == 0) {
                    openDialog(win);        // show each match result with a JDialog
                    result.setValueAt("1",p++,q);       // display the result to the table
                    result.setValueAt("0",p--,q++);     // display the result to the table
                    winNum1++;                                // accumulate the total number of win
                    teamOnePoint[pointIndex] = 1;             // assign the value to point array (later pass to next frame for display) win
                    teamTwoPoint[pointIndex] = 0;             // assign the value to point array (later pass to next frame for display) lose
                    pointIndex++;
                }
                // if Team Two win the game
                else if(win == 1) {
                    openDialog(win);        // show each match result with a JDialog
                    result.setValueAt("0",p++,q);       // display the result to the table
                    result.setValueAt("1",p--,q++);     // display the result to the table
                    winNum2++;                                // accumulate the total number of win
                    teamOnePoint[pointIndex] = 0;             // assign the value to point array (later pass to next frame for display) lose
                    teamTwoPoint[pointIndex] = 1;             // assign the value to point array (later pass to next frame for display) win
                    pointIndex++;
                }
                // if it is a tie
                else if(win == 2) {
                    round--;                // avoid go to next round if it is a tie (related to LINE 237: "++round").
                    openDialog(win);        // show each match result with a JDialog
                    num = num-2;            // remove the num of button clicked (result didn't been considered)

                    // prompt players to replay a round if it is a tie:
                    JOptionPane.showMessageDialog(getContentPane(), "It's a TIE. Let's retry!!!", "TIED ROUND",
                            JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("image/tie.png")));
                }

                // enable both buttons so players can 'BOOM' for next round
                btn1.setEnabled(true);      btnP1 = 0;
                btn2.setEnabled(true);      btnP2 = 0;

                if(round<3)    roundTitle.setText("Round " + ++round);          // modify the round title

                // if both buttons have been clicked for six times (Players ONE have finished their games)
                if(num>6) {
                    result.setValueAt(winNum1,p++,q);           // display the result for Team One player
                    result.setValueAt(winNum2,p--,q++);         // display the result for Team Two player

                    plyNum++;           // condition for display the proceed button (initially is zero)

                    if(plyNum == 1)         btnNext.setVisible(true);       // (appeared) Players One have done, proceed to Players Two
                    else                    nextPage.setVisible(true);      // (appeared) Players Two have also done, proceed to next Frame (Result)
                }
            }
        }

        // if event occurred on the next player button (Players One have done, proceed to Players Two)
        // make a concept of 'refresh' the frame (but actually just re-display some of the components, not repaint the whole frame)
        if(e.getSource() == btnNext) {
            btnNext.setVisible(false);                  // make nextBtn not visible (no longer be used)
            round = 1; num = 1; p=1; q=1;               // reinitialize all the needed condition variables
            winNum1=0; winNum2=0; btnP1=0; btnP2=0;     // reinitialize all the needed condition variables
            roundTitle.setText("Round " + round);       // reset the round title to be First Round
            pic1.setIcon(defaultPicture);   pic2.setIcon(defaultPicture);       // re-assign the default pic for Players Two
            for(int i=1; i<=2; i++){
                for(int ii=1; ii<result.getColumnCount(); ii++){
                    result.setValueAt("",i,ii); } }         // set the point elements of the result table to be empty
            name1.setText(playerT1P2);                  // display Team One Player Two name above the button
            name2.setText(playerT2P2);                  // display Team Two Player Two name above the button
            result.setValueAt("Player 2",0,0);
            result.setValueAt("Team 1 : " + playerT1P2, 1, 0);      // display Team One Player Two name into the table
            result.setValueAt("Team 2 : " + playerT2P2, 2, 0);      // display Team Two Player Two name into the table
        }

        // if event occurred on the next Page button (Players Two have also done, proceed to next frame for view result)
        if(e.getSource() == nextPage) {
            this.setVisible(false);     // hide current frame

            // set up a new frame (next)
            ResultScreen winFrame = new ResultScreen();
            winFrame.setSize(1000,660);
            winFrame.setVisible(true);
            winFrame.setResizable(false);
            winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            winFrame.setTitle("Congratulation!!!");
            winFrame.setAllBackground(clr);         // call the method of ResultScreen class to set the background color of the next frame

            winFrame.setName(playerT1P1, playerT1P2, playerT2P1, playerT2P2);   // call the method of ResultScreen class to set the players' name
            winFrame.setResult(teamOnePoint,teamTwoPoint);                      // call the method of ResultScreen class to set the game result
        }
    }

    // method used for display each match result by using JDialog
    public void openDialog(int win) {
        String[] picName = {"ROCK", "PAPER", "SCISSORS", "LIZARD", "SPOCK"};

        // set a JDialog
        // APPLICATION_MODAL : block all top-level windows from the same Java application except those from its own child hierarchy.
        JDialog singleMatch = new JDialog(this,"Result", Dialog.ModalityType.APPLICATION_MODAL);
            singleMatch.setSize(800,290);
            singleMatch.setLayout(new GridLayout(1,4));

            // first column: generated pic + name for team one
            JPanel dialogOnePic = new JPanel(new BorderLayout());
                JLabel result1 = new JLabel(imageArray[index1]);
                JLabel ply1 = new JLabel(picName[index1], JLabel.CENTER);
                ply1.setFont(new Font(style,Font.PLAIN,24));
            dialogOnePic.add(result1, BorderLayout.CENTER);
            dialogOnePic.add(ply1, BorderLayout.SOUTH);
            
            // second column: result (win/lose) for team one
            JLabel dialogOneResult = new JLabel();
                dialogOneResult.setOpaque(true);
                dialogOneResult.setHorizontalAlignment(SwingConstants.CENTER);
                dialogOneResult.setFont(new Font(style,Font.BOLD,29));

            // third column: result (win/lose) for team two  
            JLabel dialogTwoResult = new JLabel();
                dialogTwoResult.setHorizontalAlignment(SwingConstants.CENTER);
                dialogTwoResult.setOpaque(true);
                dialogTwoResult.setFont(new Font(style,Font.BOLD,29));
                
            // forth column: generated pic + name for team two
            JPanel dialogTwoPic = new JPanel(new BorderLayout());
                JLabel result2 = new JLabel(imageArray[index2]);
                JLabel ply2 = new JLabel(picName[index2], JLabel.CENTER);
                ply2.setFont(new Font(style,Font.PLAIN,24));
            dialogTwoPic.add(result2, BorderLayout.CENTER);
            dialogTwoPic.add(ply2, BorderLayout.SOUTH);
            
            
        // win==0 >>> Team One win
        // win==1 >>> Team Two win
        // win==2 >>> It is a tie
        // styling the JDialog based on the winning result
        switch (win) {
            case 0 :    dialogOnePic.setBackground(new Color(204,255,204));
                        dialogOneResult.setBackground(new Color(204,255,204));
                        dialogOneResult.setText("WIN!!");
                        dialogTwoPic.setBackground(new Color(255,204,204));
                        dialogTwoResult.setBackground(new Color(255,204,204));
                        dialogTwoResult.setText("LOSE~~");
                        break;

            case 1 :    dialogTwoPic.setBackground(new Color(204,255,204));
                        dialogTwoResult.setBackground(new Color(204,255,204));
                        dialogTwoResult.setText("WIN!!");
                        dialogOnePic.setBackground(new Color(255,204,204));
                        dialogOneResult.setBackground(new Color(255,204,204));
                        dialogOneResult.setText("LOSE~~");
                        break;

            case 2 :    dialogOnePic.setBackground(new Color(204,204,255));
                        dialogTwoPic.setBackground(new Color(204,204,255));
                        dialogOneResult.setBackground(new Color(204,204,255));
                        dialogOneResult.setText("--TIE--");
                        dialogTwoResult.setBackground(new Color(204,204,255));
                        dialogTwoResult.setText("--TIE--");
                        break;
        }
        singleMatch.add(dialogOnePic);          // added to dialog for display
        singleMatch.add(dialogOneResult);       // added to dialog for display
        singleMatch.add(dialogTwoResult);       // added to dialog for display
        singleMatch.add(dialogTwoPic);          // added to dialog for display

        singleMatch.setVisible(true);       // make the frame appear on the screen
    }

    // method use for getting the winner result based on two indexes
    public int getResult(int a, int b) {
        int win=0;

        if(a == 0) {
            if(b == 2 || b == 3)            win = 0;
            else if(b == 1 || b == 4)       win = 1;
            else                            win = 2;
        }
        else if(a == 1){
            if(b == 0 || b == 4)            win = 0;
            else if(b == 2 || b == 3)       win = 1;
            else                            win = 2;
        }
        else if(a == 2){
            if(b == 1 || b == 3)            win = 0;
            else if(b == 0 || b == 4)       win = 1;
            else                            win = 2;
        }
        else if(a == 3){
            if(b == 1 || b == 4)            win = 0;
            else if(b == 0 || b == 2)       win = 1;
            else                            win = 2;
        }
        else if(a == 4){
            if(b == 0 || b == 2)            win = 0;
            else if(b == 1 || b == 3)       win = 1;
            else                            win = 2;
        }
        return win;
    }

    // method used for set the players' name (receive from previous frame)
    public void setPlayerName(String t1p1, String t1p2, String t2p1, String t2p2) {
        playerT1P1 = t1p1;  playerT1P2 = t1p2;          // assigned to static variables of this frame
        playerT2P1 = t2p1;  playerT2P2 = t2p2;          // assigned to static variables of this frame
        name1.setText(playerT1P1);          // displayed above the button
        name2.setText((playerT2P1));        // displayed above the button
        result.setValueAt("Team 1 : " + playerT1P1, 1, 0);      // displayed in the table
        result.setValueAt("Team 2 : " + playerT2P1, 2, 0);      // displayed in the table
    }

    // method used to set the background color of the entire frame
    public void setAllColor(Color clr) {
        this.clr = clr;         // assign the color to global variable (can be passed to next frame later)

        bigPanel.setBackground(clr);
        gamePanel.setBackground(clr);
        panelLeft.setBackground(clr);
        panelRight.setBackground(clr);
        pTable.setBackground(clr);
        miniP1.setBackground(clr);
        miniP2.setBackground(clr);
        panelBtn1.setBackground(clr);
        panelBtn2.setBackground(clr);
        pFooter.setBackground(clr);
    }
}
