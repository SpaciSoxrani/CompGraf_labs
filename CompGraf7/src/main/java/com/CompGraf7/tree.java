package com.CompGraf7;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class tree implements GLEventListener {
    public tree(){}

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glBegin(GL2.GL_QUADS);

        gl.glColor3f(1f,0f,0f); //red color
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f( -1.0f, 1.0f, -1.0f);
        gl.glVertex3f( -1.0f, 1.0f, 1.0f );
        gl.glVertex3f( 1.0f, 1.0f, 1.0f );
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
