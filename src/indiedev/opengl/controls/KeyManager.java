package indiedev.opengl.controls;

import indiedev.opengl.JRenderPanel;
import indiedev.opengl.camera.AbstractCamera;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

/*
 * Camera controls:-
 * ------------------
 *TO MOVE CAM=Change the angle.(Cylindrical and spherical)
 *Buttons used:-
 *-------------
 *UP,DOWN,LEFT,RIGHT
 *xy-UD
 *yz-UD	
 *xz-LR 
 *
 *Spherical-cam
 *---------------
 *UD-change x-angle
 *LR-change y-angle
 *
 */
public class KeyManager implements KeyListener
{
	AbstractCamera temp_cam;
	JRenderPanel currpanel;
	//gets the current thru base_frame
	public KeyManager(JRenderPanel panel)
	{
		currpanel=panel;
	}

	public void keyPressed(KeyEvent e) 
	{
		//gets the current camera from the gljpanel
		temp_cam=currpanel.getCurrentCam();
		
		if(temp_cam.getCamType().equals("cyCam_xz") || temp_cam.getCamType().equals("cyCam_yz"))
		{
			System.out.println("changing along XY and YZ ");
		}
		else
		if(temp_cam.getCamType().equals("cyCam_xz"))
		{
			System.out.println("changing along XZ only ");
		}
		else if(temp_cam.getCamType().equals("Spherical"))
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				//System.out.println("Spherical cam-UP");
				temp_cam.moveCam(0f,1f);
				break;
				
			case KeyEvent.VK_DOWN:
				temp_cam.moveCam(0f,-1f);
				//System.out.println("Spherical cam-DOWN");
				break;
				
			case KeyEvent.VK_RIGHT:
				temp_cam.moveCam(1f,0f);
				//System.out.println("Spherical cam-RIGHT");
				break;
				
			case KeyEvent.VK_LEFT:
				temp_cam.moveCam(-1f,0f);
				//System.out.println("Spherical cam-LEFT");
				break;
				
			case KeyEvent.VK_A:
				temp_cam.zoomCam(-0.01f);
				break;
				
			case KeyEvent.VK_Z:
				temp_cam.zoomCam(0.01f);
				break;
				
			case KeyEvent.VK_S:
				temp_cam.zoomCam(-0.1f);
				break;
				
			case KeyEvent.VK_X:
				temp_cam.zoomCam(0.1f);
				break;
			
			case KeyEvent.VK_F1:
				JOptionPane.showMessageDialog(null, "Controls:\n---------------\n"+"Arrow keys : Movement.\n"+"A,Z : Zoom IN/OUT.");
				break;
			}
		}
		//System.out.println("Current cam:"+temp_cam.getCamType());
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
