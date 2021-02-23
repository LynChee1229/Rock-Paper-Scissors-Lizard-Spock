package JavaGroupAssignment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleScreen extends JFrame implements ActionListener {
    private final JPanel panel, panelCenter, miniP1, miniP2, p1, p2, pLeft, pRight, pTable, pFooter;
    private final JLabel roundTitle, pic1, pic2, name1, name2;
    private final JButton btn1, btn2, btnNext, nextPage;
    private final JTable result;
    private final ImageIcon[] img1 = {
            new ImageIcon(getClass().getResource("image/rock.jfif")),
            new ImageIcon(getClass().getResource("image/paper.jfif")),
            new ImageIcon(getClass().getResource("image/scissors.jfif")),
            new ImageIcon(getClass().getResource("image/lizard.jfif")),
            new ImageIcon(getClass().getResource("image/spock.jfif")),
    };
    private final ImageIcon origin = new ImageIcon(getClass().getResource("image/rule.jfif"));
    private final String style = "Verdana";
    private static int round = 1, num = 1, p=1, q=1, index1, index2, winNum1=0, winNum2=0, btnP1=0, btnP2=0, plyNum=0, pointIndex=0;
    private static int[] teamOnePoint = new int[6];
    private static int[] teamTwoPoint = new int[6];
    private static String playerT1P1, playerT1P2, playerT2P1, playerT2P2;
    private static Color clr;

    public static void main (String [] args) {
        BattleScreen frame = new BattleScreen();
        frame.setSize(1000,660);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BATTLE!!!");
    }

    public BattleScreen() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
        title.setFont(new Font("Times New Roman",Font.BOLD,35));
        title.setOpaque(true);
        title.setBackground(new Color(235, 154, 26));
        title.setPreferredSize(new Dimension(1200,58));

        roundTitle = new JLabel("Round " + round, JLabel.CENTER);
        roundTitle.setFont(new Font(style,Font.PLAIN,29));
        roundTitle.setPreferredSize(new Dimension(1200,85));

        name1 = new JLabel();
        name1.setFont(new Font(style, Font.PLAIN, 21));
        name2 = new JLabel();
        name2.setFont(new Font(style, Font.PLAIN, 21));

        pic1 = new JLabel(origin);
        pic2 = new JLabel(origin);

        p1 = new JPanel();
        p1.add(btn1 = new JButton("BOOM!"));
        p2 = new JPanel();
        p2.add(btn2 = new JButton("BOOM!"));

        miniP1 = new JPanel();
        miniP1.setLayout(new BoxLayout(miniP1, BoxLayout.Y_AXIS));
        name1.setAlignmentX(Component.CENTER_ALIGNMENT);
        miniP1.setBorder(new EmptyBorder(85,0,0,0));
        miniP1.add(name1);      miniP1.add(p1);

        miniP2 = new JPanel(new GridLayout(2,1));
        miniP2.setLayout(new BoxLayout(miniP2, BoxLayout.Y_AXIS));
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);
        miniP2.setBorder(new EmptyBorder(85,0,0,0));
        miniP2.add(name2);      miniP2.add(p2);

        pLeft = new JPanel(new GridLayout(1,2));
        pLeft.add(miniP1);
        pLeft.add(pic1);
        pRight = new JPanel(new GridLayout(1,2));
        pRight.add(pic2);
        pRight.add(miniP2);

        panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(pLeft, BorderLayout.WEST);
        panelCenter.add(pRight, BorderLayout.EAST);

        result = new JTable(3,5);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setEnabled(false);
        result.setRowHeight(58);

        DefaultTableCellRenderer ctr = new DefaultTableCellRenderer();
        ctr.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0; x<result.getColumnCount(); x++) {
            result.getColumnModel().getColumn(x).setPreferredWidth(160);
            result.getColumnModel().getColumn(x).setCellRenderer(ctr);
        }
        result.getColumnModel().getColumn(0).setPreferredWidth(290);
        result.setFont(new Font(style,Font.PLAIN,21));
        result.setValueAt("Player 1",0,0);
        result.setValueAt("Round 1",0,1);
        result.setValueAt("Round 2",0,2);
        result.setValueAt("Round 3",0,3);
        result.setValueAt("Total",0,4);

        pTable = new JPanel();
        pTable.add(result);

        JPanel bodyPanel = new JPanel(new GridLayout(2,1));
        bodyPanel.add(panelCenter);
        bodyPanel.add(pTable);

        pFooter = new JPanel();
        pFooter.add(btnNext = new JButton("NEXT PLAYER"));
        btnNext.setVisible(false);
        pFooter.add(nextPage = new JButton("PROCEED"));
        nextPage.setVisible(false);
        pFooter.setBorder(new EmptyBorder(0,0,24,0));

        panel = new JPanel(new BorderLayout());
        panel.add(roundTitle, BorderLayout.NORTH);
        panel.add(bodyPanel, BorderLayout.CENTER);
        panel.add(pFooter, BorderLayout.SOUTH);

        setAllColor(new Color(255,229,204));
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btnNext.addActionListener(this);
        nextPage.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Random r = new Random();

        if(num<=6){
            if(e.getSource() == btn1) {
                index1 = r.nextInt(img1.length);
                pic1.setIcon(img1[index1]);
                num++;
                btnP1++;
                if(btnP1 != 0){
                    btn1.setEnabled(false);     }
            }

            if(e.getSource() == btn2) {
                index2 = r.nextInt(img1.length);
                pic2.setIcon(img1[index2]);
                num++;
                btnP2++;
                if(btnP2 != 0) {
                    btn2.setEnabled(false);
                }
            }

            if(num%2 != 0) {
                int win = getResult(index1,index2);
                if (win == 0) {
                    openDialog(win);
                    result.setValueAt("1",p++,q);
                    result.setValueAt("0",p--,q++);
                    winNum1++;
                    teamOnePoint[pointIndex] = 1;
                    teamTwoPoint[pointIndex] = 0;
                    pointIndex++;
                }
                else if(win == 1) {
                    openDialog(win);
                    result.setValueAt("0",p++,q);
                    result.setValueAt("1",p--,q++);
                    winNum2++;
                    teamOnePoint[pointIndex] = 0;
                    teamTwoPoint[pointIndex] = 1;
                    pointIndex++;
                }
                else if(win == 2) {
                    round--;
                    openDialog(win);
                    num = num-2;
                    JOptionPane.showMessageDialog(getContentPane(), "It's a TIE. Let's retry!!!", "TIED ROUND",
                            JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("image/tie.png")));
                }
                btn1.setEnabled(true);  btn2.setEnabled(true);
                btnP1 = 0;  btnP2 = 0;
                if(round<3)    roundTitle.setText("Round " + ++round);

                if(num>6) {
                    result.setValueAt(winNum1,p++,q);
                    result.setValueAt(winNum2,p--,q++);
                    plyNum++;

                    if(plyNum == 1)         btnNext.setVisible(true);
                    else                    nextPage.setVisible(true);
                }
            }
        }

        if(e.getSource() == btnNext) {
            btnNext.setVisible(false);
            round = 1; num = 1; p=1; q=1;
            winNum1=0; winNum2=0; btnP1=0; btnP2=0;
            roundTitle.setText("Round " + round);
            pic1.setIcon(origin);   pic2.setIcon(origin);
            for(int i=1; i<=2; i++){
                for(int ii=1; ii<result.getColumnCount(); ii++){
                    result.setValueAt("",i,ii); } }
            name1.setText(playerT1P2);
            name2.setText(playerT2P2);
            result.setValueAt("Player 2",0,0);
            result.setValueAt("Team 1 : " + playerT1P2, 1, 0);
            result.setValueAt("Team 2 : " + playerT2P2, 2, 0);
        }

        if(e.getSource() == nextPage) {
            this.setVisible(false);
            ResultScreen winFrame = new ResultScreen();
            winFrame.setSize(1000,660);
            winFrame.setVisible(true);
            winFrame.setResizable(false);
            winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            winFrame.setTitle("Congratulation!!!");
            winFrame.setAllBackground(clr);

            winFrame.setName(playerT1P1, playerT1P2, playerT2P1, playerT2P2);
            winFrame.setResult(teamOnePoint,teamTwoPoint);
        }
    }

    public void openDialog(int win) {
        JDialog singleMatch = new JDialog(this,"Result", Dialog.ModalityType.APPLICATION_MODAL);

        singleMatch.setSize(800,290);
        singleMatch.setLayout(new GridLayout(1,4));

        String[] picName = {"ROCK", "PAPER", "SCISSORS", "LIZARD", "SPOCK"};

        JLabel result1 = new JLabel(img1[index1]);
        JLabel ply1 = new JLabel(picName[index1], JLabel.CENTER);
        ply1.setFont(new Font(style,Font.PLAIN,24));
        JPanel diaLeft = new JPanel(new BorderLayout());
        diaLeft.add(result1, BorderLayout.CENTER);
        diaLeft.add(ply1, BorderLayout.SOUTH);

        JLabel result2 = new JLabel(img1[index2]);
        JLabel ply2 = new JLabel(picName[index2], JLabel.CENTER);
        ply2.setFont(new Font(style,Font.PLAIN,24));
        JPanel diaRight = new JPanel(new BorderLayout());
        diaRight.add(result2, BorderLayout.CENTER);
        diaRight.add(ply2, BorderLayout.SOUTH);

        JLabel rLeft = new JLabel();
        rLeft.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel rRight = new JLabel();
        rRight.setHorizontalAlignment(SwingConstants.CENTER);
        rLeft.setOpaque(true);
        rRight.setOpaque(true);
        rLeft.setFont(new Font(style,Font.BOLD,29));
        rRight.setFont(new Font(style,Font.BOLD,29));

        switch (win) {
            case 0 :    diaLeft.setBackground(new Color(204,255,204));
                        rLeft.setBackground(new Color(204,255,204));
                        rLeft.setText("WIN!!");
                        diaRight.setBackground(new Color(255,204,204));
                        rRight.setBackground(new Color(255,204,204));
                        rRight.setText("LOSE~~");
                        break;

            case 1 :    diaRight.setBackground(new Color(204,255,204));
                        rRight.setBackground(new Color(204,255,204));
                        rRight.setText("WIN!!");
                        diaLeft.setBackground(new Color(255,204,204));
                        rLeft.setBackground(new Color(255,204,204));
                        rLeft.setText("LOSE~~");
                        break;

            case 2 :    diaLeft.setBackground(new Color(204,204,255));
                        diaRight.setBackground(new Color(204,204,255));
                        rLeft.setBackground(new Color(204,204,255));
                        rLeft.setText("--TIE--");
                        rRight.setBackground(new Color(204,204,255));
                        rRight.setText("--TIE--");
                        break;
        }
        singleMatch.add(diaLeft);       singleMatch.add(rLeft);
        singleMatch.add(rRight);        singleMatch.add(diaRight);
        singleMatch.setVisible(true);
    }

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

    public void setPlayerName(String t1p1, String t1p2, String t2p1, String t2p2) {
        playerT1P1 = t1p1;  playerT1P2 = t1p2;
        playerT2P1 = t2p1;  playerT2P2 = t2p2;
        name1.setText(playerT1P1);
        name2.setText((playerT2P1));
        result.setValueAt("Team 1 : " + playerT1P1, 1, 0);
        result.setValueAt("Team 2 : " + playerT2P1, 2, 0);
    }

    public void setAllColor(Color clr) {
        this.clr = clr;

        panel.setBackground(clr);
        panelCenter.setBackground(clr);
        pLeft.setBackground(clr);
        pRight.setBackground(clr);
        pTable.setBackground(clr);
        miniP1.setBackground(clr);
        miniP2.setBackground(clr);
        p1.setBackground(clr);
        p2.setBackground(clr);
        pFooter.setBackground(clr);
    }
}
