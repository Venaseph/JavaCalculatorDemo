package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JPanel implements ActionListener{  // remember to extend JPanel
    
    public static final int WIDTH = 320;  // for JFrame WIDTH
    public static final int HEIGHT = 480;  // for JFrame HEIGHT
    
    private GridBagLayout layout;  // creates GB layout
    private GridBagConstraints gbc;  // allows positioning of buttons
    
    private JButton[] numberButtons;  // array for num buttons creation
    private JButton[] opButtons;  // array for other buttons creation
    
    private JTextField field;
    
    private double num1, num2, ans;
    private int op;
    
    // [0] = gridx. [1] = gridy. [2] = gridwidth. [3] = gridheight
    private int[][] numConstraints = new int [][] { // MDA to create number buttons with for loop
        {0,5,2,1},
        {0,4,1,1},
        {1,4,1,1},
        {2,4,1,1},
        {0,3,1,1},
        {1,3,1,1},
        {2,3,1,1},
        {0,2,1,1},
        {1,2,1,1},
        {2,2,1,1},        
    };

    // [0] = gridx. [1] = gridy. [2] = gridwidth. [3] = gridheight    
    private int[][] opConstraints = new int[][] {  // MDA to create non-number buttons with for loop
        {2,5,1,1},
        {3,4,1,2},
        {3,3,1,1},
        {3,2,1,1},
        {3,1,1,1},
        {2,1,1,1},
        {1,1,1,1},
        {0,1,1,1},
    };
    
    public Calculator() { // default constructor
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // sets JFrame size
        
        layout = new GridBagLayout(); //  instan new GridBagLayout
        layout.columnWidths = new int[] {80,80,80,80};  // sets widths of grid columns
        layout.rowHeights = new int[] {80, 80, 80, 80, 80, 80};  // sets height of grid rows
        setLayout(layout);
        
        gbc = new GridBagConstraints();  
        
        numberButtons = new JButton[10];  // instans num buttons and set constraints from MDA
        for(int i =0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton("" + i);
            numberButtons[i].addActionListener(this);
            
            gbc.gridx = numConstraints[i][0];
            gbc.gridy = numConstraints[i][1];
            gbc.gridwidth = numConstraints[i][2];
            gbc.gridheight = numConstraints[i][3];
            gbc.fill = GridBagConstraints.BOTH;  // fill display area entirely
            gbc.insets = new Insets(2,2,2,2);  // padding between grid and component, pixels from (top,bottom,left,right)
            
            add(numberButtons[i], gbc);
        }
        
        opButtons = new JButton[8];
        
        opButtons[0] = new JButton("."); 
        opButtons[1] = new JButton("=");
        opButtons[2] = new JButton("+");
        opButtons[3] = new JButton("-");
        opButtons[4] = new JButton("*");
        opButtons[5] = new JButton("/");
        opButtons[6] = new JButton("+/-");
        opButtons[7] = new JButton("C");
        
        for(int i =0; i < opButtons.length; i++) {
            gbc.gridx = opConstraints[i][0];
            gbc.gridy = opConstraints[i][1];
            gbc.gridwidth = opConstraints[i][2];
            gbc.gridheight = opConstraints[i][3];
			// gbc.fill = GridBagConstraints.BOTH;
			// gbc.insets = new Insets(2,2,2,2);
            opButtons[i].addActionListener(this);            
            
            add(opButtons[i], gbc);
        }        
        
        field = new JTextField();
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // sets border around JTextField
        field.setEditable(false);
        field.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        
        add(field, gbc);       
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");  // Frame Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Clean close of program Frame
        frame.setResizable(false);  // Lock frame size
        frame.setLayout(new BorderLayout()); // BorderLayout to center
        frame.add(new Calculator(), BorderLayout.CENTER);  // add instance of class and center it
        frame.pack(); // set size of frame to size of panel
        frame.setLocationRelativeTo(null); // centers frame on screen
        frame.setVisible(true);  // makes it visible
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < numberButtons.length; i++) {
            if(e.getSource() == numberButtons[i]) {
                field.setText(field.getText() + i);
            }
        }
        
        if(e.getSource() == opButtons[0] && !field.getText().contains(".")) {
            field.setText(field.getText() + ".");
        }
        
        if(e.getSource() == opButtons[6]) {
            field.setText("" + (-1 * Double.parseDouble(field.getText())));
        }
        
        if(e.getSource() == opButtons[7]) {
            field.setText("");
        }
        
        if(e.getSource() == opButtons[2]) {
            num1 = Double.parseDouble(field.getText());
            op = 1;
            field.setText("");
        }
        
        if(e.getSource() == opButtons[3]) {
            num1 = Double.parseDouble(field.getText());
            op = 2;
            field.setText("");
        }
        
        if(e.getSource() == opButtons[4]) {
            num1 = Double.parseDouble(field.getText());
            op = 3;
            field.setText("");
        }
        
        if(e.getSource() == opButtons[5]) {
            num1 = Double.parseDouble(field.getText());
            op = 4;
            field.setText("");
        }
        
        if(e.getSource() == opButtons [1]) {
            num2 = Double.parseDouble(field.getText());
            
            if(op == 1) {
                ans = num1 + num2;
            } else if(op == 2) {
                ans = num1 - num2;
            } else if(op == 3) {
                ans = num1 * num2;
            } else if(op == 4) {
                ans = num1 / num2;
            }
            
            op = 0;
            field.setText("" + ans);
        }
    }

}
