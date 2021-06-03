package com.CompGraf7;

import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class SkyBox implements GLEventListener {

    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    private float xrot,yrot,zrot;
    private int texture;

    @Override
    public void display(GLAutoDrawable drawable) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(0f, 0f, -4.0f);

        gl.glRotatef(xrot, 1.0f, 1.0f, 1.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glBegin(GL2.GL_QUADS);

        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, -1.2f);

        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);

        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, 1.2f);

        // Right face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, -1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.2f, -1.2f, 1.2f);

        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, -1.2f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.2f, -1.2f, 1.2f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, 1.2f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.2f, 1.2f, -1.2f);
        gl.glEnd();
        gl.glFlush();

        //change the speeds here
        xrot += .1f;
        yrot += .1f;
        zrot += .1f;
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

            File im = new File("C:\\Users\\Stacy\\CompGraf6\\src\\main\\java\\com\\CompGraf7\\img\\img_1.png");
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

}
