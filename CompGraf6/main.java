package com.CompGraf6;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

public class main {
    public static void main(String[] args) {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);


        final GLCanvas glcanvas = new GLCanvas(capabilities);
        //сами фигуры
        
        Square square = new Square();
        Polygon polygon = new Polygon();
        Circle circle = new Circle();
        CubeRotation cubeRotation = new CubeRotation();
        Sphere sphere = new Sphere();
        Triangle3D triangle3D = new Triangle3D();

        glcanvas.addGLEventListener(triangle3D);
        glcanvas.setSize(400, 400);


        final JFrame frame = new JFrame ("Drawing!!!");

        //канвас во фрэйм
        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
        animator.start();
    }
}
