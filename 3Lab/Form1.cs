using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompGraf3
{
    public partial class Form1 : Form
    {
        Graphics g;
        Cube cube;
        public Form1()
        {
            InitializeComponent();
            this.g = panel1.CreateGraphics();
        }


        private void button2_Click(object sender, EventArgs e)
        {
            g.Clear(Color.White);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var Mx = panel1.Width / 2;
            var My = panel1.Height / 7;
            Pen p = new Pen(Color.Blue, 1);

            double x, y, t, a, b, z1, z2, z3;
            double PI = (float)3.14159;
            double rd = (float)0.3535534;
            long px, py;
            float px0 = 0, py0 = 0;
            for (int i = 0; i <= 450; i++)
            {
                t = i * PI / 180.0;
                a = 0.3 * t;
                b = 10 * t;
                z1 = a;
                z2 = Math.Cos(b);
                z3 = Math.Sin(b);
                x = z2 - rd * z3;
                y = z1 - rd * z3;
                px = (int)Math.Round(Mx + 100 * x);
                py = (int)Math.Round(My + 100 * y);
                if (i == 0)
                {
                    px0 = px;
                    py0 = py;
                }
                g.DrawLine(p, px0, py0, px, py);
                px0 = px;
                py0 = py;
            }

        }

        private void Sphere_Click(object sender, EventArgs e)
        {
            float x, y, t, a, b, z1, z2, z3;
            float rd = (float)0.3535534;
            long px, py;
            var PI = (float)3.14159;
            var Mx = panel1.Width / 2;
            var My = panel1.Height / 2;
            Pen pn = new Pen(Color.Red, 1);

            float px0 = 0, py0 = 0;
            for (var g1 = 0; g1 <= 620; g1++)
            {
                t = g1 * PI / (float)180.0;
                a = (float)0.3 * t;
                b = (float)10 * t;
                z1 = (float)Math.Sin(b);
                z2 = (float)Math.Cos(b) * (float)Math.Cos(a);
                z3 = (float)Math.Cos(b) * (float)Math.Sin(a);
                x = z2 - rd * z3;
                y = z1 - rd * z3;
                px = (long)Math.Round(Mx + 100 * x);
                py = (long)Math.Round(My + 100 * y);
                if (g1 == 0)
                {
                    px0 = px;
                    py0 = py;
                }
                g.DrawLine(pn, px0, py0, px, py);
                px0 = px;
                py0 = py;
            }
        }

        private void Surface_Click(object sender, EventArgs e)
        {
            long xx;
            long yy;
            double z1, z2, z3;

            var Mx = panel1.Width / 2;
            var My = panel1.Height / 4;

            var rd = 0.3535534;


            Pen pn = new Pen(Color.DarkMagenta, 1);

            float px0, py0;
            z3 = -1.2;

            while (z3 <= 1.2)
            {
                z2 = -1.2;
                z1 = 2 * Math.Exp(-z2 * z2 - z3 * z3);
                xx = (long)Math.Round(Mx + 100.0 * z2 - (rd * 100) * z3);
                yy = (long)Math.Round(My + 100.0 * z1 - (rd * 100) * z3);
                px0 = xx;
                py0 = yy;
                while (z2 <= 1.2)
                {
                    z1 = 2 * Math.Exp(-z2 * z2 - z3 * z3);
                    xx = (long)Math.Round(Mx + 100.0 * z2 - (rd * 100) * z3);
                    yy = (long)Math.Round(My + 100.0 * z1 - (rd * 100) * z3);
                    g.DrawLine(pn, px0, py0, xx, yy);
                    z2+= 0.04;
                    px0 = xx;
                    py0 = yy;
                }
                z3+=0.12;
            }
        }

        private void Cube_Click(object sender, EventArgs e)
        {
            cube = new Cube();
            cube.FinaleDraw(panel1, g);
        }

        private void MCube_Click(object sender, EventArgs e)
        {
            cube.Draw_MCube(panel1, g);
        }

        private void MoveCube_Click(object sender, EventArgs e)
        {
            cube.DrawMoveCube(g);
        }

        private void RotationCube_Click(object sender, EventArgs e)
        {
            cube.DrawRotationCube(g, panel1);
        }
    }

}
