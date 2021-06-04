package com.CompGraf7;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GLCapabilities;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class SkyBox implements GLEventListener, MouseMotionListener, MouseListener {

    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    private float xrot,yrot,zrot;
    private int texture;
    private int textureBottom;
    GLUT glut;
    int moving,startx,starty;
    float angx=0,angy=0,angz=0;

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef( 0f, 0f, -5.0f ); // Move the triangle
        gl.glBegin(GL2.GL_QUADS);

        // Back Face
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, -1.2f);

        // Top Face
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);

        gl.glEnd();
        gl.glFlush();

        gl.glBegin(GL2.GL_QUADS);
        // Bottom Face
        //gl.glColor3f( 0.0f,1f, 1f );
        gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glVertex3f( 1.2f, -1.2f, -1.2f);
        gl.glVertex3f( 1.2f, -1.2f, 1.2f);
        gl.glVertex3f(-1.2f, -1.2f, 1.2f);
        gl.glEnd();
        gl.glFlush();

        gl.glBegin(GL2.GL_QUADS);
        // Right face
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, 1.2f);

        // Left Face
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glEnd();
        gl.glFlush();


        //earth
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f( 0.0f,0.7f, 0.7f  ); //sky blue (blue +green)
        gl.glVertex3f(-1.2f, -1.2f, 0.9f);
        gl.glVertex3f( -1.0f, -1.0f, -1.2f );
        gl.glVertex3f( -0.5f, -1.2f, 0.9f );
        //white triangles


        gl.glColor3f( 0.0f,1f, 1f ); //sky blue (blue +green)
        gl.glVertex3f(-0.5f, -1.2f, 0.9f);
        gl.glVertex3f( -1.0f, -1.0f, -1.2f);
        gl.glVertex3f( -0.3f, -1.0f, -1.2f );

        gl.glColor3f( 0.0f,0.7f, 0.7f); //sky blue (blue +green)
        gl.glVertex3f(-0.5f, -1.2f, 0.9f);
        gl.glVertex3f( -0.3f, -1.0f, -1.2f);
        gl.glVertex3f( 0.2f, -1.2f, 0.9f );

        gl.glColor3f( 0.0f,1f, 1f); //sky blue (blue +green)
        gl.glVertex3f(0.2f, -1.2f, 0.9f);
        gl.glVertex3f( -0.3f, -1.0f, -1.2f);
        gl.glVertex3f( 0.5f, -1.2f, 0.9f );

        gl.glColor3f( 0.0f,0.7f, 0.7f); //sky blue (blue +green)
        gl.glVertex3f(-0.3f, -1.0f, -1.2f);
        gl.glVertex3f( 0.5f, -1.2f, 0.9f);
        gl.glVertex3f( 0.7f, -1.2f, -1.2f );

        gl.glColor3f( 0.0f,1f, 1f); //sky blue (blue +green)
        gl.glVertex3f(0.7f, -1.2f, -1.2f);
        gl.glVertex3f( -0.3f, -1.0f, -1.2f);
        gl.glVertex3f( -0.7f, -1.2f, -1.2f );

        gl.glColor3f( 0.0f,0.7f, 0.7f); //sky blue (blue +green)
        gl.glVertex3f(-1.2f, -1.2f, 0.9f);
        gl.glVertex3f( -1.0f, -1.0f, -1.2f );
        gl.glVertex3f( -1.2f, -1.1f, -1.2f );

        gl.glEnd();

        gl.glFlush();

        gl.glBegin(GL2.GL_QUADS);

        // tree
        gl.glColor3f( 1.0f,0.0f, 0.0f);
        gl.glVertex3f(0.8f, -1.2f, -0.9f);
        gl.glVertex3f(0.85f, -1.2f, -0.7f);
        gl.glVertex3f( 0.85f, -1.0f, -0.7f);
        gl.glVertex3f( 0.8f, -1.0f, -0.9f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(0.85f, -1.2f, -0.7f);
        gl.glVertex3f(0.93f, -1.2f, -0.9f);
        gl.glVertex3f( 0.93f, -1.0f, -0.9f);
        gl.glVertex3f( 0.85f, -1.0f, -0.7f);

        gl.glColor3f( 1.0f,0.9f, 0.0f);
        gl.glVertex3f(0.6f, -1.0f, -0.9f);
        gl.glVertex3f(0.85f, -1.0f, -0.6f);
        gl.glVertex3f( 0.85f, -0.7f, -0.6f);
        gl.glVertex3f( 0.7f, -0.7f, -0.9f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(0.85f, -1.0f, -0.6f);
        gl.glVertex3f(1.1f, -1.0f, -0.9f);
        gl.glVertex3f( 1.0f, -0.7f, -0.9f);
        gl.glVertex3f( 0.85f, -0.7f, -0.6f);

        gl.glColor3f( 1.0f,0.9f, 0.0f);
        gl.glVertex3f(0.6f, -0.7f, -0.9f);
        gl.glVertex3f(0.85f, -0.7f, -0.6f);
        gl.glVertex3f( 0.85f, -0.4f, -0.6f);
        gl.glVertex3f( 0.85f, -0.4f, -0.9f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(0.85f, -0.7f, -0.6f);
        gl.glVertex3f(1.1f, -0.7f, -0.9f);
        gl.glVertex3f( 0.85f, -0.4f, -0.9f);
        gl.glVertex3f( 0.85f, -0.4f, -0.6f);

        gl.glEnd();
        gl.glFlush();

        gl.glBegin(GL2.GL_QUADS);

        // tree2
        gl.glColor3f( 1.0f,0.0f, 0.0f);
        gl.glVertex3f(0.9f, -1.2f, -0.6f);
        gl.glVertex3f(0.95f, -1.2f, -0.4f);
        gl.glVertex3f( 0.95f, -1.0f, -0.4f);
        gl.glVertex3f( 0.9f, -1.0f, -0.6f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(0.95f, -1.2f, -0.4f);
        gl.glVertex3f(1.03f, -1.2f, -0.6f);
        gl.glVertex3f( 1.03f, -1.0f, -0.6f);
        gl.glVertex3f( 0.95f, -1.0f, -0.4f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(0.7f, -1.0f, -0.6f);
        gl.glVertex3f(0.95f, -1.0f, -0.3f);
        gl.glVertex3f( 0.95f, -0.7f, -0.3f);
        gl.glVertex3f( 0.8f, -0.7f, -0.6f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(0.95f, -1.0f, -0.3f);
        gl.glVertex3f(1.2f, -1.0f, -0.6f);
        gl.glVertex3f( 1.1f, -0.7f, -0.6f);
        gl.glVertex3f( 0.95f, -0.7f, -0.3f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(0.7f, -0.7f, -0.6f);
        gl.glVertex3f(0.95f, -0.7f, -0.3f);
        gl.glVertex3f( 0.95f, -0.4f, -0.3f);
        gl.glVertex3f( 0.95f, -0.4f, -0.6f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(0.95f, -0.7f, -0.3f);
        gl.glVertex3f(1.2f, -0.7f, -0.6f);
        gl.glVertex3f( 0.95f, -0.4f, -0.6f);
        gl.glVertex3f( 0.95f, -0.4f, -0.3f);

        gl.glEnd();
        gl.glFlush();

        gl.glBegin(GL2.GL_QUADS);

        // tree3
        gl.glColor3f( 1.0f,0.0f, 0.0f);
        gl.glVertex3f(-0.1f, -1.0f, -0.6f);
        gl.glVertex3f(-0.05f, -1.0f, -0.4f);
        gl.glVertex3f( -0.05f, -0.8f, -0.4f);
        gl.glVertex3f( -0.1f, -0.8f, -0.6f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(-0.05f, -1.0f, -0.4f);
        gl.glVertex3f(0.03f, -1.0f, -0.6f);
        gl.glVertex3f( 0.03f, -0.8f, -0.6f);
        gl.glVertex3f( -0.05f, -0.8f, -0.4f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(-0.3f, -0.8f, -0.6f);
        gl.glVertex3f(-0.05f, -0.8f, -0.3f);
        gl.glVertex3f( -0.05f, -0.5f, -0.3f);
        gl.glVertex3f( -0.2f, -0.5f, -0.6f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(-0.05f, -0.8f, -0.3f);
        gl.glVertex3f(0.2f, -0.8f, -0.6f);
        gl.glVertex3f( 0.1f, -0.5f, -0.6f);
        gl.glVertex3f( -0.05f, -0.5f, -0.3f);

        gl.glColor3f( 1.0f,1.0f, 0.0f);
        gl.glVertex3f(-0.3f, -0.5f, -0.6f);
        gl.glVertex3f(-0.05f, -0.5f, -0.3f);
        gl.glVertex3f( -0.05f, -0.2f, -0.3f);
        gl.glVertex3f( -0.05f, -0.2f, -0.6f);

        gl.glColor3f( 1.0f,0.7f, 0.0f);
        gl.glVertex3f(-0.05f, -0.5f, -0.3f);
        gl.glVertex3f(0.2f, -0.5f, -0.6f);
        gl.glVertex3f( -0.05f, -0.2f, -0.6f);
        gl.glVertex3f( -0.05f, -0.2f, -0.3f);

        gl.glEnd();
        gl.glFlush();
        //sphere



    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // method body
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();


        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        //
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{

            File im = new File("C:\\Users\\Stacy\\CompGraf6\\src\\main\\java\\com\\CompGraf7\\img\\img.png");
            Texture t = TextureIO.newTexture(im, true);
            texture= t.getTextureObject(gl);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
        if(height == 0)
        height = 1;

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void LoadTexture(String path){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        moving=1;
        startx=e.getX();
        starty=e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        moving=0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x,y;

        x=e.getX();
        y=e.getY();
        angy=angy+(x-startx);
        angz=angz+(y-starty);
        startx=x;
        starty=y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
