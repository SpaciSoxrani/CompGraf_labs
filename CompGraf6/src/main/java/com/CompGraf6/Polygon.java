package com.CompGraf6;


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Polygon implements GLEventListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glBegin(GL2.GL_POLYGON);

        gl.glVertex3f(0f,0.5f,0f);
        gl.glVertex3f(-0.5f,0.2f,0f);
        gl.glVertex3f(-0.5f,-0.2f,0f);
        gl.glVertex3f(0f,-0.5f,0f);
        gl.glVertex3f(0f,0.5f,0f);
        gl.glVertex3f(0.5f,0.2f,0f);
        gl.glVertex3f(0.5f,-0.2f,0f);
        gl.glVertex3f(0f,-0.5f,0f);

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

}
