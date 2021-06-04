package com.CompGraf6;


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Circle implements GLEventListener {
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        int numVertices = 50;
        double radius = 0.5;
        gl.glColor3f(30, 30, 30);

        gl.glBegin(GL2.GL_POLYGON);
        {
            double angle = 0;
            double angleIncrement = 2 * Math.PI / numVertices;
            for (int i = 0; i < numVertices; i++) {
                angle = i * angleIncrement;
                double x = radius * Math.cos(angle);
                double y = radius * Math.sin(angle);
                gl.glVertex2d(x, y);
            }
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
