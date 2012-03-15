
import javax.swing.*;


import java.awt.*;
public final class base_frame extends JFrame
{
	Container c;
	gljpanel2 panel;
	//other classes
	final keyb keyB;
	
		base_frame()
		{
			super("Open-GL 1.0 using JOGL                                Press F1 for controls");
			c=getContentPane();
			//c.setLayout(new FlowLayout());
			//other classes
			panel=new gljpanel2();
			
			
			//controllers
			keyB=new keyb(panel);
			
			addKeyListener(keyB);
			
			//container adding
			c.add(panel);
			//set visual
			pack();
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		}
		public static void main(String args[])
		{
			base_frame bframe=new base_frame();
			bframe.setAlwaysOnTop(true);
			bframe.setVisible(true);
		}
		
		//returns the current OPENGL context
		public gljpanel2 getGLPanel()
		{
			return panel;
		}
}
