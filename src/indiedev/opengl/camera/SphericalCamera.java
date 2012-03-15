package indiedev.opengl.camera;

import javax.media.opengl.GL;

public class SphericalCamera extends AbstractCamera 
{
	/*
	 * Camera stages:-
	 * -----------------
	 * creating->setting up
	 * moving->updating values
	 * 
	 */
	 float x_angle=90f;
	 float y_angle=90f;
	 
	public SphericalCamera() 
	{
		cam_type="Spherical";
		cam_radius=4f;//default radius
		cam_x=0;
		cam_y=0;
		cam_z=cam_radius;
	}
	
	public void moveCam(float temp_x,float temp_y)
	{
		/*
		 * its moved based x_angle and y_angle
		 * X-axis=radian_i ~
		 * Y-axis=radian_j ~
		 * Z-axis=radius(refer top)
		 */
		float radian_i,radian_j;
		if(x_angle>360 || x_angle<-360)
		{
			x_angle=0;
		}
		if(y_angle>360 || y_angle<-360)
		{
			y_angle=0;
		}
		//converts the given angle to radian
		radian_i=(float)Math.toRadians(x_angle+=temp_x);
		radian_j=(float)Math.toRadians(y_angle+=temp_y);
		//System.out.println("x,y:"+x_angle+","+y_angle);
		//computes the position
		cam_x=cam_radius*(float)Math.cos(radian_i)*(float)Math.sin(radian_j);
		cam_z=cam_radius*(float)Math.sin(radian_i)*(float)Math.sin(radian_j);
		cam_y=cam_radius*(float)Math.cos(radian_j);
		//cam_x=10;cam_y=10;
		//System.out.println("Sp-Cam(x,y,z):"+cam_x+","+cam_y+","+cam_z);
		//System.out.println("cam radius:"+cam_radius);
	
	}
	
	public void zoomCam(float temp_z)
	{
		if(cam_radius<0.002f)
		{
			cam_radius=0.002f;
		}
		else
		{
			cam_radius+=temp_z;
			float radian_i,radian_j;
			
			//converts the given angle to radian
			radian_i=(float)Math.toRadians(x_angle);
			radian_j=(float)Math.toRadians(y_angle);
			//computes the position
			cam_x=cam_radius*(float)Math.cos(radian_i)*(float)Math.sin(radian_j);
			cam_z=cam_radius*(float)Math.sin(radian_i)*(float)Math.sin(radian_j);
			cam_y=cam_radius*(float)Math.cos(radian_j);
			System.out.println("cam radius:"+cam_radius);
		}
	}
	
	public void drawCamPath(GL gl)
	{
		/*
		 * 3 parameters:-
		 *        rho-sphere radius
		 *        theta-angle b/w x,y
		 *        phi-angle b/w radius point,z-axis
		 *  
		 */
		float x,y,z,radian_i,radian_j;
		gl.glBegin(GL.GL_POINTS);
		
			for(int i=0;i<360;i+=10)//controls latitudes
			{//for all the angles from 0 to 360
				
				radian_i=(float)Math.toRadians(x_angle);
				
				for(int j=0;j<360;j+=10)//controls longitudes
				{
	
					radian_j=(float)Math.toRadians(y_angle);
					
					x=cam_radius*(float)Math.cos(radian_i)*(float)Math.sin(radian_j);
					z=cam_radius*(float)Math.sin(radian_i)*(float)Math.sin(radian_j);
					y=cam_radius*(float)Math.cos(radian_i);
					
					gl.glColor3f(1f,1f,1f);
					gl.glVertex3f(x,y,z);
				}
			}
		gl.glEnd();
	}
}
