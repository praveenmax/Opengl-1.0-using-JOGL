package indiedev.opengl.camera;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
public abstract class AbstractCamera 
{
	 float cam_x=0f;
	 float cam_y=0f;
	 float cam_z=0f;
	 float cam_angle;
	 float cam_radius;
	 float cam_radian;
	 String cam_type="none";
	 
	/*
	 * Sets the camera view and gluLookAt parameters 
	 */
	public void setCam(GL gl,GLU glu,GLJPanel panel)
	{
		float aspect_ratio=(float)panel.getWidth()/(float)panel.getHeight();
		//loads the projection matrix
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		
		//sets the glulookat method from glu
		
		glu.gluPerspective(45,aspect_ratio,1,1000);
		glu.gluLookAt(cam_x,cam_y,cam_z,0,0,0,0,1,0);
		
		//sets back to model-view matrix
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		//confirmation
		System.out.println("Panel(w,h):"+panel.getWidth()+","+panel.getHeight());
		System.out.println("Camera set-up complete(x,y,z)"+cam_x+","+cam_y+","+cam_z);
		
	}

	public void refreshCam_Pos(GLU glu,GL gl)
	{
		gl.glMatrixMode(GL.GL_PROJECTION_MATRIX);
		gl.glLoadIdentity();
		glu.gluLookAt(cam_x,cam_y,cam_z,0,0,0,0,1,0);
		//gl.glMatrixMode(GL.GL_MODELVIEW);
		//gl.glLoadIdentity();
		//System.out.println("refreshing cam");
	}
	public String getCamType()
	{
		return cam_type;
	}
	
	public void moveCam()
	{
		System.out.println("Method doesnt exist for this camera:"+this.getCamType());
	}
	
	public void moveCam(float temp_x,float temp_y)
	{
		System.out.println("For sphere only");
	}
	
	public void zoomCam(float temp_z)
	{
		System.out.println("For sphere only");
	}
	
	public abstract void drawCamPath(GL gl);
	
	public float getCam_xpos()
	{
		return this.cam_x; 
	}
	
	public float getCam_ypos()
	{
		return this.cam_y;
	}
	
	public float getCam_zpos()
	{
		return this.cam_z;
	}
}
