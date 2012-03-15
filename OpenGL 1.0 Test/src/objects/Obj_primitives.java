package objects;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Obj_primitives {
	Texture earthTexture,moonTexture;
	BufferedImage br,br_moon;
	GLUquadric sphere;
	float rot_x=0f;
	public Obj_primitives() throws IOException
	{
		//load the texture
		try {
			
			br= ImageIO.read(new File("src/earth_map.jpg"));
			earthTexture = TextureIO.newTexture(br,false);
			br_moon=ImageIO.read(new File("src/moon.jpg"));
			moonTexture=TextureIO.newTexture(br_moon,false);
		
		} catch (IOException e) {
			System.out.println("Cannot load the texture");
			e.printStackTrace();
		}
	}
	public void createObjects(GL gl,GLU glu)
	{
		sphere=glu.gluNewQuadric();
        glu.gluQuadricTexture(sphere, true);
		glu.gluQuadricDrawStyle(sphere,GLU.GLU_FILL);//fill,line,point
		glu.gluQuadricNormals(sphere, GLU.GLU_FILL);
        glu.gluQuadricOrientation(sphere, GLU.GLU_OUTSIDE);
		
	}
	public void drawObjects(GL gl,GLU glu)
	{
		earthTexture.enable();
		earthTexture.bind();
		gl.glRotatef(90,1,0,0);
		gl.glRotatef(rot_x+=0.5f,0,0,1);
		glu.gluSphere(sphere , 2f,40, 40);
		earthTexture.disable();
		
		//moon
		moonTexture.enable();
		moonTexture.bind();
		gl.glTranslatef(5f,0f,0f);
		gl.glRotatef(rot_x,0,0,1);
		glu.gluSphere(sphere , 0.2f,10, 10);
		moonTexture.disable();
		
	}
}
