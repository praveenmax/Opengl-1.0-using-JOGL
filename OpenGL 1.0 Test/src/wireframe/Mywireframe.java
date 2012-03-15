package wireframe;
import javax.media.opengl.*;

public class Mywireframe 
{
	private int grid_1,grid_2;
	static String grid_type="null";
	
	public Mywireframe(int x,int y) throws null_gridsize
	{
		if(x==0||y==0)
		{
			throw new null_gridsize(x,y);
		}
		else
		{
			grid_1=x;
			grid_2=y;
		}
	}
	
	public void wire_xy(GL gl)
	{
		grid_type="XY-plane";
	//	System.out.println("Grid Size:(x,y)"+grid_1+","+grid_2);

	gl.glBegin(GL.GL_LINES);
			gl.glColor3f(1f,0f,0f);
			for (int i = -grid_1; i <= grid_1; i += 1) {

				// for ver_lines axis(x-axis)
				//here y=constant ,x -varies
				gl.glVertex3f(-i, -grid_2, 0);
				gl.glVertex3f(-i, grid_2, 0);
			}
			gl.glEnd();
			
			gl.glBegin(GL.GL_LINES);
			for (int i = -grid_2; i <= grid_2; i += 1) {

				// for hor_lines-axis(y-axis)
				//here x =constant,y-varies
				gl.glVertex3f(-grid_1, -i, 0);
				gl.glVertex3f(grid_1, -i, 0);
			}

			
		gl.glEnd();
		
	
		
	//	System.out.println("drawing wire");

		
	}
	public void wire_xz(GL gl)
	{
		grid_type="XZ-plane";
		//for XZ plane
		gl.glBegin(GL.GL_LINES);
		gl.glColor3f(0f,1f,0f);
		
		for (int i = -grid_1; i <= grid_1; i += 1) {

			// for ver_lines axis(x-axis)
			//here Z=constant ,x -varies
			gl.glVertex3f(-i,0, -grid_2);
			gl.glVertex3f(-i,0, grid_2);
		}

		for (int i = -grid_2; i <= grid_2; i += 1) {

			// for hor_lines-axis(y-axis)
			//here x =constant,z-varies
			gl.glVertex3f(-grid_1,0 ,-i);
			gl.glVertex3f(grid_1,0, -i);
		}

		
	gl.glEnd();
	

		
	}
	public void wire_yz(GL gl)
	{
		
		grid_type="YZ-plane";
		//for XZ plane
		gl.glBegin(GL.GL_LINES);
		gl.glColor3f(0f,0f,1f);
		
		for (int i = -grid_1; i <= grid_1; i += 1) {

			// for ver_lines axis(x-axis)
			//here z=constant ,y -varies
			gl.glVertex3f(0,-i, -grid_2);
			gl.glVertex3f(0,-i, grid_2);
		}

		for (int i = -grid_2; i <= grid_2; i += 1) {

			// for hor_lines-axis(y-axis)
			//here y =constant,z-varies
			gl.glVertex3f(0,-grid_1 ,-i);
			gl.glVertex3f(0,grid_1, -i);
		}

		
	gl.glEnd();
	//System.out.println("drawing wire");
	}
	
	private class null_gridsize extends RuntimeException
	{
		null_gridsize(int x,int y)
		{
			System.out.println("The specified grid-size is 0!=(x,y):("+x+","+y+")");
		}
		
		
	}

}
