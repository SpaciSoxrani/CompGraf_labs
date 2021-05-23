using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompGraf2
{
    public partial class Form1 : Form
    {
        Graphics g;
        Timer timer1;

        double a = 0;
        double alpha = 2*Math.PI / 360;

        NewObject rec = new NewObject(20, 20);
        static int i = 5;
        public int ticks;
        public int interval = 10;

        public Form1()
        {
            InitializeComponent();
            g = panel1.CreateGraphics();
        }


        private void drawFigure_Click(object sender, EventArgs e)
        {
            SolidBrush b = new SolidBrush(Color.DarkCyan);
            g.FillRectangle(b, (float)(panel1.Width/2 - rec.width/2), (float)(panel1.Height/2 - rec.height/2),
                (float)rec.width, (float)rec.height);
            drawFigure.Enabled = false;
            rec.StartPoints(panel1, rec);
        }


        public void EnableButtons(bool en)
        {
            buttonIncrease.Enabled = en;
            buttonReduce.Enabled = en;
            rotation.Enabled = en;
        }

        public void UpdatePoints(int i)
        {
            rec.points[0] = new Point(rec.points[0].X + i, rec.points[0].Y + i);
            rec.points[1] = new Point(rec.points[1].X - i, rec.points[1].Y + i);
            rec.points[2] = new Point(rec.points[2].X - i, rec.points[2].Y - i);
            rec.points[3] = new Point(rec.points[3].X + i, rec.points[3].Y - i);
        }

        private void buttonIncrease_Click(object sender, EventArgs e)
        {
            timer1 = new Timer();

            EnableButtons(false);

            timer1.Interval = interval;
            timer1.Tick += Timer1_Tick;
            timer1.Start();
        }

        private void buttonReduce_Click(object sender, EventArgs e)
        {
            timer1 = new Timer();
            EnableButtons(false);

            timer1.Interval = interval;
            timer1.Tick += Timer2_Tick;
            timer1.Start();
        }

        private void rotation_Click(object sender, EventArgs e)
        {
            timer1 = new Timer();
            EnableButtons(false);

            timer1.Interval = interval;
            timer1.Tick += Timer3_Tick;
            timer1.Start();
        }

        private void Timer3_Tick(object sender, EventArgs e)
        {
            if(ticks < 225)
            {
                g.Clear(Color.White);
                SolidBrush b = new SolidBrush(Color.DarkCyan);
                double sin = Math.Sin(a);
                double cos = Math.Cos(a);
                double size = Math.Sqrt(rec.width * rec.width + rec.height * rec.height) / 2;

                rec.points[0] = new Point((int)Math.Round(panel1.Width/2 + size * Math.Cos(a)),
                    (int)Math.Round(panel1.Height/2 + size * Math.Sin(a)));
                rec.points[1] = new Point((int)Math.Round(panel1.Width / 2 + size * Math.Cos(a + Math.PI/2)),
                    (int)Math.Round(panel1.Height / 2 + size * Math.Sin(a + Math.PI / 2)));
                rec.points[2] = new Point((int)Math.Round(panel1.Width / 2 + size * Math.Cos(a + Math.PI)),
                    (int)Math.Round(panel1.Height / 2 + size * Math.Sin(a + Math.PI)));
                rec.points[3] = new Point((int)Math.Round(panel1.Width / 2 + size * Math.Cos(a + 3*Math.PI / 2)),
                    (int)Math.Round(panel1.Height / 2 + size * Math.Sin(a + 3*Math.PI / 2)));

                g.FillPolygon(b, rec.points);
                a += alpha;
                ticks += 1;
            }
            else
            {
                ticks = 0;
                a = 0;
                timer1.Stop();
                EnableButtons(true);
            }

        }

        private void Timer2_Tick(object sender, EventArgs e)
        {
            if (ticks < 10)
            {
                g.Clear(Color.White);
                SolidBrush b = new SolidBrush(Color.DarkCyan);
                rec.width -= i;
                rec.height -= i;

                UpdatePoints(i);

                g.FillPolygon(b, rec.points);
                ticks += 1;
            }
            else
            {
                ticks = 0;
                timer1.Stop();
                EnableButtons(true);

            }
        }

        private void Timer1_Tick(object Sender, EventArgs e)
        {
            if (ticks < 10)
            {
                g.Clear(Color.White);
                SolidBrush b = new SolidBrush(Color.DarkCyan);
                rec.width += i;
                rec.height += i;

                UpdatePoints(-1 * i);

                g.FillPolygon(b, rec.points);
                ticks += 1;
            }
            else
            {
                ticks = 0;
                timer1.Stop();
                EnableButtons(true);

            }
        }

        private void buttonFMove_Click(object sender, EventArgs e)
        {
            timer1 = new Timer();
            EnableButtons(false);

            timer1.Interval = interval;
            timer1.Tick += Timer4_Tick;
            timer1.Start();
        }

        private void Timer4_Tick(object sender, EventArgs e)
        {
            if (ticks < 100)
            {
                //y = sin(x)
                g.Clear(Color.White);
                SolidBrush b = new SolidBrush(Color.DarkCyan);

                rec.points[0] = new Point((int)(rec.points[0].X+1), (int)(10*Math.Sin((rec.points[0].X)/4)+100));

                g.FillRectangle(b, rec.points[0].X, rec.points[0].Y, 20, 20);
                ticks += 1;
            }
            else
            {
                ticks = 0;
                timer1.Stop();
                EnableButtons(true);

            }
        }
    }

}
