package JavaGroupAssignment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class ResultScreen extends JFrame implements ActionListener {
    private final ImageIcon congratsPic = new ImageIcon(getClass().getResource("image/congrats.png"));
    private final JButton exitBtn;
    private final JPanel panelWest, panelEast, panelCenter, panelCenterA, panelCenterB, panelSouth, panelSouthA, panelSouthB;
    private static JLabel Pic, teamScore1, teamScore2, congratsLabel, winner;
    private static Color clr;
    private static JTable table1, table2;

    public static void main(String[] args) {
        ResultScreen winFrame = new ResultScreen();
        winFrame.setSize(1000,660);
        winFrame.setVisible(true);
        winFrame.setResizable(false);
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setTitle("Congratulation!!!");
    }

    public ResultScreen() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
            titleLabel.setFont(new Font("Times New Roman",Font.BOLD,35));
            titleLabel.setOpaque(true);
            titleLabel.setBackground(new Color(235, 154, 26));
            titleLabel.setPreferredSize(new Dimension(1200,58));

        panelWest = new JPanel();
            panelWest.add(Pic = new JLabel(congratsPic));
            panelWest.setBorder(new EmptyBorder(80,25,0,25));

        panelEast = new JPanel();
            panelEast.add(exitBtn = new JButton("Exit"));
            panelEast.setBorder(new EmptyBorder(20, 20, 20, 20));
            exitBtn.setMargin(new Insets(5, 25, 5, 25));
            exitBtn.setFont(new Font("Verdana", Font.PLAIN, 18));

        panelCenter = new JPanel(new GridLayout(2, 1));
            panelCenterA = new JPanel(new GridLayout(1,2));
                panelCenterA.add(teamScore1 = new JLabel("Team 1 : "));
                    teamScore1.setFont(new Font("Verdana", Font.PLAIN, 25));
                panelCenterA.add(teamScore2 = new JLabel("Team 2 : "));
                    teamScore2.setFont(new Font("Verdana", Font.PLAIN, 25));
                panelCenterA.setBorder(new EmptyBorder(0,129,0,0));
            panelCenterB = new JPanel();
                panelCenterB.add(congratsLabel = new JLabel("Congratulation!!! ", JLabel.CENTER));
                    congratsLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
                panelCenterB.add(winner = new JLabel("", JLabel.CENTER));
                    winner.setFont(new Font("Verdana", Font.BOLD, 42));
                    winner.setForeground(new Color(153,0,0));

        panelCenter.add(panelCenterA);
        panelCenter.add(panelCenterB);
        panelCenter.setBorder(new EmptyBorder(50,0,80,0));

        panelSouthA = new JPanel();
            table1 = new JTable(3, 5);
            table1.setEnabled(false);
            table1.setRowHeight(36);
            table1.setFont(new Font("Verdana", Font.PLAIN, 21));
            table1.setBorder(BorderFactory.createLineBorder(Color.black));
        panelSouthB = new JPanel();
            table2 = new JTable(3,5);
            table2.setEnabled(false);
            table2.setRowHeight(36);
            table2.setFont(new Font("Verdana", Font.PLAIN, 21));
            table2.setBorder(BorderFactory.createLineBorder(Color.black));

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

            table1.setValueAt("Player 1",0,0);
            table1.setValueAt("Round 1",0,1);
            table1.setValueAt("Round 2",0,2);
            table1.setValueAt("Round 3",0,3);
            table1.setValueAt("Total",0,4);

            table2.setValueAt("Player 2",0,0);
            table2.setValueAt("Round 1",0,1);
            table2.setValueAt("Round 2",0,2);
            table2.setValueAt("Round 3",0,3);
            table2.setValueAt("Total",0,4);
        panelSouthA.add(table1);
        panelSouthB.add(table2);

        panelSouth = new JPanel();
            GridLayout gridL = new GridLayout(2,1);
            gridL.setVgap(24);
            panelSouth.setLayout(gridL);
        panelSouth.add(panelSouthA);
        panelSouth.add(panelSouthB);
        panelSouth.setBorder(new EmptyBorder(0,0,45,0));

        setAllBackground(new Color(255,229,204));
        add(titleLabel, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelEast, BorderLayout.EAST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        exitBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            dispose();
        }
    }

    public void setName(String t1p1, String t1p2, String t2p1, String t2p2) {
        table1.setValueAt("Team 1 : " + t1p1,1,0);
        table1.setValueAt("Team 2 : " + t2p1,2,0);
        table2.setValueAt("Team 1 : " + t1p2,1,0);
        table2.setValueAt("Team 2 : " + t2p2,2,0);
    }

    public void setResult(int[] team1point, int[] team2point ) {
        int teamOne, teamTwo, y=1, team1p1=0, team2p1=0, team1p2=0, team2p2=0;

        for(int x=0; x<3; x++) {
            table1.setValueAt(team1point[x], 1,(x+1));
            table1.setValueAt(team2point[x], 2, (x+1));
            team1p1 += team1point[x];
            team2p1 += team2point[x];
            table1.setValueAt(team1p1, 1, 4);
            table1.setValueAt(team2p1, 2, 4);
        }
        for(int x=3; x<6; x++) {
            table2.setValueAt(team1point[x], 1,(y));
            table2.setValueAt(team2point[x], 2, (y));
            team1p2 += team1point[x];
            team2p2 += team2point[x];
            table2.setValueAt(team1p2, 1, 4);
            table2.setValueAt(team2p2, 2, 4);
            y++;
        }
        teamOne = team1p1 + team1p2;
        teamTwo = team2p1 + team2p2;

        teamScore1.setText("Team 1 : " + teamOne);
        teamScore2.setText("Team 2 : " + teamTwo);

        if(teamOne > teamTwo){
            winner.setText("Team 1");
        }
        else if(teamOne < teamTwo){
            winner.setText("Team 2");
        }
        else if(teamOne == teamTwo){
            congratsLabel.setText("");
            winner.setText("It's a draw!!!");
            Pic.setIcon(new ImageIcon(getClass().getResource("image/tieGame.png")));
        }
    }

    public void setAllBackground (Color c) {
        clr = c;

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
