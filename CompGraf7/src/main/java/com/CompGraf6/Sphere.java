package com.CompGraf6;


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Sphere implements GLEventListener {
    private GLU glu = new GLU();
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //lighting
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-0.2f, 0, -3, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.0f, 0.2f, 1f};
        float[] lightColorSpecular = {0.9f, 0.9f, 0.8f, 1f};

        //параметры света
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);


        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);

        float[] rgba = {0.0f, 1.0f, 0.0f};
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);


        //сама отрисовка сферы
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        final float radius = 0.378f;
        final int slices = 30;
        final int stacks = 30;
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

}
