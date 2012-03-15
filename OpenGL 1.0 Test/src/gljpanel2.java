
import javax.media.opengl.*;

import objects.*;
import javax.media.opengl.glu.*;
import java.awt.*;
import java.io.IOException;

import Camera.AbstractCamera;
import Camera.sphericalCam;

import com.sun.opengl.util.FPSAnimator;

import wireframe.Mywireframe;
final class gljpanel2 extends GLJPanel implements GLEventListener
{
	//temp stufffs
//	float x=0f,y=0f;
	//Render looper
	FPSAnimator fpsanim;
	GLU glu;
	//camera class
	AbstractCamera main_cam;
	//wireframe
	Mywireframe wf;
	//objects
	Obj_primitives obj_sphere;
	gljpanel2()
	{
		//other classes
		main_cam=new sphericalCam();
		
		//pre-display set-up
		wf=new Mywireframe(10,10);
		
		
		addGLEventListener(this);
		setPreferredSize(new Dimension(800,600));
		fpsanim=new FPSAnimator(this,60,true);
		fpsanim.start();
	}
	public void init(final GLAutoDrawable drawable)
	{
		final GL gl=drawable.getGL();//gets the rendering context
		glu=new GLU();
		
		//clear attributes
		gl.glClearColor(0f,0f,0f,1f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		
		//perspective correction
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT,GL.GL_NICEST);
		//clear the depth
		
		gl.glEnable(GL.GL_DEPTH_TEST);

        gl.glClearDepth(1.0f);                      

        gl.glDepthFunc(GL.GL_LEQUAL);
		
		//AA attributes
		gl.glEnable(GL.GL_LINE_SMOOTH|GL.GL_POINT_SMOOTH);
		gl.glHint(GL.GL_LINE_SMOOTH,GL.GL_NICEST);
		gl.glHint(GL.GL_POINT_SMOOTH,GL.GL_NICEST);
		
		//POLYGON MODE
		gl.glPolygonMode(GL.GL_FRONT,GL.GL_FILL);
		
		//setting the camera
		main_cam.setCam(gl, glu, this);
		
		try {
			obj_sphere=new Obj_primitives();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		obj_sphere.createObjects(gl, glu);
		//confirmation
		System.out.println("Opengl context created");
		
	}
	
	public void display(final GLAutoDrawable drawable) {
		final GL gl=drawable.getGL();
		glu=new GLU();
		gl.glLoadIdentity();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);

		main_cam.refreshCam_Pos(glu,gl);
		//glu.gluLookAt(x+=0.5f,y+=0.5f,0f,0,0,0,0,1,0);
		//drawing the wire-frame
		wf.wire_xy(gl);
		//DEFAULT	wf.wire_xz(gl);
		wf.wire_yz(gl);
		wf.wire_xz(gl);
//		gl.glColor3f(1f,1f,1f);
//		gl.glBegin(GL.GL_LINES);
//			gl.glColor3f(1f,1f,0f);
//			
//			gl.glVertex3f(0f,0f,0f);
//			gl.glVertex3f(5f,0f,0f);
//			
//			gl.glColor3f(1,1,1);
//			gl.glVertex3f(0,0,0);
//			gl.glVertex3f(2,0,0);
//		gl.glEnd();
		
		//drawing objects
		gl.glColor3f(1,1,1);
		obj_sphere.drawObjects(gl, glu);
		
		origin_point(gl);//	origin
		gl.glLoadIdentity();
		gl.glFlush();
	}
	
	public void origin_point(final GL gl)
	{
		
		gl.glColor3f(1f,0f,0f);
		gl.glBegin(GL.GL_POINTS);
			gl.glVertex3f(0f,0f,0f);
		gl.glEnd();
		
		
		
	}
	
	public void displayChanged(final GLAutoDrawable drawable, final boolean modeChanged,
			final boolean deviceChanged) {
		
	}
	public void reshape(final GLAutoDrawable drawable, final int x, final int y, final int width,
			final int height) {
		
	}
	//used to set a new camera when needed
	public void setCurrentCam(final AbstractCamera temp_cam)
	{
		main_cam=temp_cam;
	}
	public AbstractCamera getCurrentCam()
	{
		return main_cam;
	}
}
