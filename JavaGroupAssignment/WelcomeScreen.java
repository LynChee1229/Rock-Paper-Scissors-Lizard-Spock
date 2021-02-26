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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame implements ActionListener {

    // declare GUI components
    private JButton proceedBtn;
    private JComboBox colorChoices;
    private JLabel ChildContent1,ChildContent2;
    private JPanel contentPanel,ChildContent3, btnPanel;

    // create constant variables
    private final Font titleFont = new Font("Times New Roman", Font.BOLD,35);
    private final Font fontInContent = new Font("Verdana", Font.PLAIN, 25);

    // initialize global variable (shared across all instances)
    private static Color clr = new Color(255,229,204);

    public static void main (String[]args){
        // set the frame
        WelcomeScreen frame = new WelcomeScreen();
        frame.setSize(1000,660);
        frame.setVisible(true);                                     // make the frame appear on the screen
        frame.setResizable(false);                                  // disable frame resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // specify the option for the close button (Exit the application)
        frame.setTitle("WELCOME!!!");
    }

    // constructor without parameter (will be invoked when the object of WelcomeScreen has been created)
    public WelcomeScreen(){
        setLayout(new BorderLayout());

        // set the "title banner" (north panel)
        JLabel title = new JLabel("Rock-Paper-Scissors-Lizard-Spock", JLabel.CENTER);
            title.setFont(titleFont);
            title.setOpaque(true);                                      // paint every pixel (no transparent pixels)
            title.setBackground(new Color(235, 154, 26));
            title.setPreferredSize(new Dimension(1200,58));

        // center panel (grid layout)
        contentPanel = new JPanel(new GridLayout(3,1));

            // first row of center panel
            ChildContent1 = new JLabel("Welcome!");
                ChildContent1.setFont(fontInContent);
                ChildContent1.setHorizontalAlignment(JLabel.CENTER);

            // second row of center panel
            ChildContent2 = new JLabel("Please select the background color before you proceed (compulsory): ");
                ChildContent2.setFont(fontInContent);
                ChildContent2.setHorizontalAlignment(JLabel.CENTER);

            // third row of center panel
            ChildContent3 = new JPanel();
                colorChoices = new JComboBox(new Object[]{"Blue","Red","Yellow","Green","Grey"});
                colorChoices.setPreferredSize(new Dimension(120,29));
            ChildContent3.add(colorChoices);

        contentPanel.setBorder(new EmptyBorder(85,0,120,0));        // transparent border for set the margin
        contentPanel.add(ChildContent1);
        contentPanel.add(ChildContent2);
        contentPanel.add(ChildContent3);


        // south panel (default flow layout)
        btnPanel = new JPanel();
            proceedBtn = new JButton("PROCEED");
            proceedBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
            proceedBtn.setPreferredSize(new Dimension(158,42));
            proceedBtn.setBackground(clr);                                                  // set the color for button itself
            proceedBtn.setEnabled(false);                                                   // disable the button (enable after selecting color)
        btnPanel.setBorder(new EmptyBorder(30, 30, 100, 30));          // transparent border for set the margin
        btnPanel.add(proceedBtn);


        setColor(clr);      // call the method used to set the background color of the entire frame

        add(title,BorderLayout.NORTH);                         // added to frame
        add(contentPanel,BorderLayout.CENTER);                 // added to frame
        add(btnPanel,BorderLayout.SOUTH);                      // added to frame

        colorChoices.addActionListener(this);               // for handling action event (change background color)
        proceedBtn.addActionListener(this);                 // for handling action event (go to next frame)
    }

    // handle all the actions of components
    public void actionPerformed(ActionEvent e) {

        // get player's choice
        String color = (String) colorChoices.getSelectedItem();

        // if event occurred on the comboBox (player choose color)
        if(e.getSource()==colorChoices){
            switch (color){
                case "Blue":    clr = new Color(153,204,255);
                                break;
                case "Red":     clr = new Color(255,153,153);
                                break;
                case "Yellow":  clr = new Color(255,255,153);
                                break;
                case "Green":   clr = new Color(204,255,153);
                                break;
                case "Grey":    clr = new Color(200,200,200);
                                break;
            }

            setColor(clr);      // set the background color based on player's choice

            proceedBtn.setEnabled(true);    // (enable) allow player go to next page after selecting color
        }

        // if event occurred on the proceed button (player want to register name)
        if (e.getSource()==proceedBtn){

            this.setVisible(false);         // hide current frame

            // set up a new frame (next)
            TeamInitialization window = new TeamInitialization();
            window.setSize(1000,660);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setTitle("LET'S PLAY!!");
            window.setColors(clr);      // call the method of TeamInitialization class to set the background color of the next frame
        }
    }

    // method used to set the background color of the entire frame
    public void setColor(Color c) {
        ChildContent1.setBackground(c);
        ChildContent2.setBackground(c);
        ChildContent3.setBackground(c);
        contentPanel.setBackground(c);
        btnPanel.setBackground(c);
    }
}
