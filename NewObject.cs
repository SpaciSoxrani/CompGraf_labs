using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompGraf1
{
    public class NewObject
    {
        Point point1;
        Point point2;

        int size;
        public string OBJECT_NAME;

        Point[] points;

        public NewObject() { }
        public NewObject(TextBox textBox_X1Line, TextBox textBox_Y1Line,
            TextBox textBox_X2Line, TextBox textBox_Y2Line, TextBox textBox_WLine)
        {
            //line
            OBJECT_NAME = "LINE";

            point1 = new Point(int.Parse(textBox_X1Line.Text), int.Parse(textBox_Y1Line.Text));
            point2 = new Point(int.Parse(textBox_X2Line.Text), int.Parse(textBox_Y2Line.Text));
            size = int.Parse(textBox_WLine.Text);
        }

        public NewObject(TextBox textBox_XCenterCircle, TextBox textBox_YCenterCircle,
            TextBox textBox_radiusCircle)
        {
            //circle
            OBJECT_NAME = "CIRCLE";

            size = int.Parse(textBox_radiusCircle.Text);
            point1 = new Point(int.Parse(textBox_XCenterCircle.Text), int.Parse(textBox_YCenterCircle.Text));

        }

        public NewObject(Point[] points)
        {
            //polygon
            OBJECT_NAME = "POLYGON";
            this.points = points;
        }

        public NewObject(TextBox textBox_XRPolygon, TextBox textBox_YRPolygon,
            TextBox textbox_radiusPolygon, TextBox textBox_polygonAngles)
        {
            //polygon
            OBJECT_NAME = "R_POLYGON";

            int angles = int.Parse(textBox_polygonAngles.Text);
            double alpha = 2 * Math.PI / angles;
            size = int.Parse(textbox_radiusPolygon.Text);

            point1 = new Point(int.Parse(textBox_XRPolygon.Text), int.Parse(textBox_YRPolygon.Text));

            points = new Point[angles];

            for (int i = 0; i < angles; i++)
            {
                int delX = (int)(size * Math.Sin(alpha * i));
                int delY = (int)(size * Math.Cos(alpha * i));
                points[i] = new Point(point1.X + delX, point1.Y + delY);
            }
        }

        public void DrawNewLine(Graphics g)
        {
            Pen p = new Pen(Color.Black, size);
            g.DrawLine(p, point1, point2);
        }

        public void FillNewCircle(Graphics g)
        {
            SolidBrush b = new SolidBrush(Color.Red);
            g.FillEllipse(b, point1.X - size, point1.Y - size, size * 2, size * 2);
        }

        public void FillNewPolygon(Graphics g)
        {
            SolidBrush b = new SolidBrush(Color.Blue);
            g.FillPolygon(b, points);
        }

        public void FillNewRPolygon(Graphics g)
        {
            SolidBrush b = new SolidBrush(Color.DarkGreen);
            g.FillPolygon(b, points);
        }
    }
}
