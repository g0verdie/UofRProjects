import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener
{
        private Board board;
        private JFrame game;
        private JPanel panel;
        private JButton[][] grid;
        private JButton test;
        
        private int numClicks = 0;

        public GUI(Board board) 
        {
        	game = new JFrame("Tic-Tac-Toe RG Edition");
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	game.setSize(500,500);
           
        	panel = new JPanel(new GridLayout(3,3));
        	game.add(panel);
        	
            Container content = game.getContentPane();
            content.setBackground(Color.lightGray);
        	
        	grid = new JButton[3][3];
        	for(int i = 0; i < 3; i++)
        	{
        		grid[i] = new JButton[3];
        		for(int j = 0; j < 3; j++)
        		{
        			grid[i][j] = new JButton((board.isEmpty(i,j) ? " " : grid[i][j]).toString());
        			panel.add(grid[i][j]);
        			grid[i][j].setVisible(true);
        		}
        	}
        	
        	game.setVisible(true);
                
        }

        public void actionPerformed(ActionEvent e) 
        {
        }

        public static void main(String[] args) 
        {
                GUI myWindow = new GUI(new Board());
        }
        
        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}

}