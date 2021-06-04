package com.CompGraf7;

import com.CompGraf6.*;
import com.CompGraf6.Polygon;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;


public class main2 {
    public static void main(String[] args) {


        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);


        final GLCanvas glcanvas = new GLCanvas(capabilities);



        SkyBox s = new SkyBox();
        glcanvas.addGLEventListener(s);
        glcanvas.setSize(700, 700);


        final JFrame frame = new JFrame ("Drawing!!!");

        //канвас во фрэйм
        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);


    }
}
