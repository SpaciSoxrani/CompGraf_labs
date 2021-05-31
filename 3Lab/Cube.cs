using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompGraf3
{

    class Cube
    {
        public struct poi
        {
            public float x, y, z;
            public poi(float x1, float y1, float z1) { x = x1; y = y1; z = z1; }
        };

        public int Mx, My;
        private const double PI = 3.14159;
        private const double rd = 0.3535534;
        public poi[] cb;
        public poi[] cub;
        public poi[] cubs;
        public Point[] cubpro;
        int cbh;
        private double s = 1.0;
        double a = 0, sn = 1.0, cs = 0, h = PI / 20, snh, csh;
        poi z;
        public void cbinit()
        {
            cb = new poi[16];
            cb[0] = new poi(0, 0, 0);
            cb[1] = new poi(1, 0, 0);
            cb[2] = new poi(1, 1, 0);
            cb[3] = new poi(0, 1, 0);
            cb[4] = new poi(0, 0, 0);
            cb[5] = new poi(0, 0, 1);
            cb[6] = new poi(1, 0, 1);
            cb[7] = new poi(1, 0, 0);
            cb[8] = new poi(1, 0, 1);
            cb[9] = new poi(1, 1, 1);
            cb[10] = new poi(1, 1, 0);
            cb[11] = new poi(1, 1, 1);
            cb[12] = new poi(0, 1, 1);
            cb[13] = new poi(0, 1, 0);
            cb[14] = new poi(0, 1, 1);
            cb[15] = new poi(0, 0, 1);
        }

        public void ProCub()
        {
            int i;
            cubpro = new Point[16];
            for (i = 0; i < 16; i++)
            {
                cubpro[i] = new Point();
                cubpro[i].X = (int)Math.Round(cub[i].y - rd * (float)cub[i].z);
                cubpro[i].Y = (int)Math.Round(cub[i].x - rd * (float)cub[i].z);
            }
        }

        public void DrawCube(Graphics gcb, Pen pn)
        {
            int i;
            int px0, py0, px, py;
            px0 = (int)cubpro[0].X;
            py0 = (int)cubpro[0].Y;
            for (i = 0; i < 16; i++)
            {
                px = cubpro[i].X;
                py = cubpro[i].Y;
                gcb.DrawLine(pn, px0, py0, px, py);
                px0 = px;
                py0 = py;
            }
        }

        public void FinaleDraw(Panel panel1, Graphics g)
        {
            int i;
            cbh = 50;
            Mx = panel1.Width / 4;
            My = panel1.Height / 2;
            cub = new poi[16];
            cbinit();
            for (i = 0; i < 16; i++)
            {
                cub[i] = new poi();
                cub[i].x = cb[i].x * cbh;
                cub[i].y = cb[i].y * cbh;
                cub[i].z = cb[i].z * cbh;
                cub[i].x = cub[i].x + Mx;
                cub[i].y = cub[i].y + My;
            }
            ProCub();
            DrawCube(g, new Pen(Color.Red, 1));
        }

        //проекция отмасштабированного куба
        public void MshtCub(float s)
        {
            poi zf = new poi(Mx, My, 0);
            for (int i = 0; i < 16; i++)
            {
                cub[i].x = (float)cub[i].x * s + (float)zf.x * (1 - s);
                cub[i].y = (float)cub[i].y * s + (float)zf.y * (1 - s);
                cub[i].z = (float)cub[i].z * s + (float)zf.z * (1 - s);
            }
        }

        public void Draw_MCube(Panel panel1, Graphics g)
        {
            int pause = 10;
            s = 1;
            while (true)
            {
                if (s > 1.3)
                {
                    //timer2.Enabled = false;
                    break;
                }
                else
                {
                    //if ((i%2)==0)
                    Color clr = panel1.BackColor;
                    ProCub();
                    MshtCub((float)s);
                    DrawCube(g, new Pen(clr, 1));
                    MshtCub((float)s);
                    s += 0.1;
                    clr = Color.Red;
                    ProCub();
                    MshtCub((float)s);
                    ProCub();
                    DrawCube(g, new Pen(clr, 1));
                    Thread.Sleep(pause);
                }
            }
        }
        //смещение по оси Z
        public void TranCub(float dz, float dy)
        {
            for (int i = 0; i < 16; i++)
            {
                cub[i].y = cub[i].y + dy;
                cub[i].z = cub[i].z + dz;
            }
        }

        public void DrawMoveCube(Graphics g)
        {
            int k = 0;
            int pause = 10;
            while (true)
            {
                if (k > 20)
                {
                    //timer1.Enabled = false;
                    break;
                }
                else
                {
                    //button2_Click(sender, e);//hide
                    g.Clear(Color.White);
                    ProCub();
                    DrawCube(g, new Pen(Color.Red, 1));
                    Thread.Sleep(pause);
                    TranCub(5, 2);
                    k += 1;
                }
            }
        }

        public void CirCub(double sn, double cs)
        {
            for (int i = 0; i < 16; i++)
            {
                cubs[i].x = (float)(z.x + (cub[i].x - z.x) * cs - (cub[i].y - z.y) * sn);
                cubs[i].y = (float)(z.y + (cub[i].y - z.y) * cs - (cub[i].x - z.x) * sn);
                cubs[i].z = cub[i].z;
            }
        }

        public void DrawRotationCube(Graphics g, Panel panel1)
        {
            snh = Math.Sin(h);
            csh = Math.Cos(h);
            a = 0;
            cs = Math.Cos(a);
            sn = Math.Sin(a);
            z = new poi(cub[0].x, cub[0].y, 0);
            int i = 0;
            cs = Math.Cos(a);
            sn = Math.Sin(a);
            cubs = new poi[16];
            for (i = 0; i < 16; i++) cubs[i] = new poi(0, 0, 0);
            Color clr;
            int pause = 10;
            //if ((ii % 2) == 0)
            int k = 0;
            while (true)
            {
                if (a > 2 * PI) break;
                else
                {
                    if ((k % 2) == 0) clr = panel1.BackColor;
                    else clr = Color.Red;
                    k++;
                    CirCub(sn, cs);
                    for (i = 0; i < 16; i++)
                    {
                        cubpro[i].X = (int)Math.Round(cubs[i].y - rd * (float)cubs[i].z);
                        cubpro[i].Y = (int)Math.Round(cubs[i].x - rd * (float)cubs[i].z);
                    }
                    DrawCube(g, new Pen(clr, 1));
                    Thread.Sleep(pause);
                    cs = cs * csh - sn * snh; sn = cs * snh + sn * csh; a = a + h;
                    g.Clear(Color.White);
                }
            }
        }
    }
}
