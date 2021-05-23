using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompGraf2
{
    class NewObject
    {
        public int width;
        public int height;

        public Point[] points = new Point[4];

        public NewObject(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public void StartPoints(Panel panel1, NewObject rec)
        {
            rec.points[0] = new Point(panel1.Width / 2 - rec.width / 2, panel1.Height / 2 - rec.height / 2);
            rec.points[1] = new Point(panel1.Width / 2 - rec.width / 2 + rec.width, panel1.Height / 2 - rec.height / 2);

            rec.points[2] = new Point(panel1.Width / 2 - rec.width / 2 + rec.width, panel1.Height / 2 - rec.height / 2 + rec.height);
            rec.points[3] = new Point(panel1.Width / 2 - rec.width / 2, panel1.Height / 2 - rec.height / 2 + rec.height);

        }
    }

}
