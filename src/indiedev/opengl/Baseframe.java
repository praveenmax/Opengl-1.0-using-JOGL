package indiedev.opengl;

import indiedev.opengl.controls.KeyManager;

import javax.swing.*;


import java.awt.*;
public final class Baseframe extends JFrame
{
	Container c;
	JRenderPanel panel;
	//other classes
	final KeyManager keyB;
	
	Baseframe()
	{
		super("Open-GL 1.0 using JOGL                                Press F1 for controls");
		c=getContentPane();
		//c.setLayout(new FlowLayout());
		//other classes
		panel=new JRenderPanel();
		
		//controllers
		keyB=new KeyManager(panel);
		addKeyListener(keyB);
		
		c.add(panel);
		
		//set visual
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[])
	{
		Baseframe bframe=new Baseframe();
		bframe.setAlwaysOnTop(true);
		bframe.setVisible(true);
	}
	
	//returns the current OPENGL context
	public JRenderPanel getGLPanel()
	{
		return panel;
	}
}
