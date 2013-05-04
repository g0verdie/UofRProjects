import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GUI {
	Hashtable<String,Graph.Edge> edges;
	Hashtable<String,Graph.Vertex> verts;
	Enumeration<Graph.Vertex> vel;
	Enumeration<Graph.Edge> eel;
	
	public Drawer draw1;
	private JFrame fr;
	
	public class Drawer extends JPanel {
		
		public Drawer(int x,int y) {
			setPreferredSize(new Dimension(x,y));
			setBackground(Color.white);
		}
		public void paint(Graphics g) {
			super.paintComponent(g);
			while(vel.hasMoreElements())
			{
				Graph.Vertex current = vel.nextElement();
				g.fillRect(current.x, current.y, 2, 2);
			}
			//System.out.println(edges.size());
			while(eel.hasMoreElements())
			{
				Graph.Edge current = eel.nextElement();
				if(current.flagged == 1)
					g.setColor(Color.RED);
				else if(current.flagged == 2)
				{
					//System.out.println("this is blue");
					g.setColor(Color.BLUE);
					//g.drawLine(edges.get(i).v1.x, edges.get(i).v1.y, edges.get(i).v2.x, edges.get(i).v2.y);
				}
				else
					g.setColor(Color.BLACK);
				g.drawLine(current.v1.x, current.v1.y, current.v2.x, current.v2.y);
			}
		}
	}
	
	public GUI(int x,int y, Hashtable<String,Graph.Vertex> v, Hashtable<String,Graph.Edge> e) 
	{
		Drawer draw1 = new Drawer(x,y);
		verts = v;
		edges = e;
		vel = verts.elements();
		eel = edges.elements();
		fr = new JFrame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.add(draw1);
		fr.pack();
		fr.setVisible(true);
	}
}


