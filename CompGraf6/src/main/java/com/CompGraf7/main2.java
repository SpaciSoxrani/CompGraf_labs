package com.CompGraf7;

import com.CompGraf6.*;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;


public class main2 {
    public static void main(String[] args) {


        // TODO Auto-generated method stub
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        SkyBox r = new SkyBox();
        Sphere s = new Sphere();

        glcanvas.addGLEventListener(r);
        glcanvas.addGLEventListener(s);
        glcanvas.setSize(400, 400);

        final JFrame frame = new JFrame (" Textured Cube");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);


    }
}
