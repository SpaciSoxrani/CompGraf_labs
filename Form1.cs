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
    public partial class Form1 : Form
    {
        Graphics g;
        private LinkedList<NewObject> stackObjects;

        NewObject lineShouldBeRemove;
        NewObject circleShouldBeRemove;
        NewObject polygonShouldBeRemove;
        NewObject R_polygonShouldBeRemove;

        public Form1()
        {
            InitializeComponent();
            this.stackObjects = new LinkedList<NewObject>();
            this.g = panelPicture.CreateGraphics();
        }


        public string CheckFields(Label label_message, params TextBox[] textBoxes)
        {
            foreach (TextBox tb in textBoxes)
            {
                if (tb.TextLength == 0)
                {
                    label_message.Text = "Не все данные для отрисовки объекта получены!";
                    return "EMPTY_FIELDS";
                }
                try
                {
                    int b = int.Parse(tb.Text);
                }
                catch (Exception ex)
                {
                    label_message.Text = "Необходимо вводить только числа! " + ex;
                    return "NOT_TEXT";
                }
            }
            label_message.Text = "Все необходимые значения получены:)";
            return "OK";
        }

        private void drawLine_Click(object sender, EventArgs e)
        {
            if (CheckFields(label_message, textBox_X1Line, textBox_Y1Line, textBox_X2Line,
                textBox_Y2Line, textBox_WLine) == "OK")
            {
                NewObject newLine = new NewObject(textBox_X1Line, textBox_Y1Line, textBox_X2Line,
                textBox_Y2Line, textBox_WLine);
                newLine.DrawNewLine(g);
                lineShouldBeRemove = newLine;
                stackObjects.AddFirst(newLine);
            }
        }

        private void clearLine_Click(object sender, EventArgs e)
        {
            DeleteObject del = new DeleteObject();
            lineShouldBeRemove =  del.ReturnShouldBeRemove(stackObjects, lineShouldBeRemove, g, label_message, "LINE");
        }

        private void buttonDrawCircle_Click(object sender, EventArgs e)
        {
            if (CheckFields(label_message, textBox_XCenterCircle, textBox_YCenterCircle,
            textBox_radiusCircle) == "OK")
            {
                NewObject newCircle = new NewObject(textBox_XCenterCircle, textBox_YCenterCircle,
            textBox_radiusCircle);
                newCircle.FillNewCircle(g);
                circleShouldBeRemove = newCircle;
                stackObjects.AddFirst(newCircle);
            }
        }

        private void buttonСlearCircle_Click(object sender, EventArgs e)
        {
            DeleteObject del = new DeleteObject();
            circleShouldBeRemove = del.ReturnShouldBeRemove(stackObjects, circleShouldBeRemove, g, label_message, "CIRCLE");
        }

        private void button_addPoint_Click(object sender, EventArgs e)
        {
            if (CheckFields(label_message, textBox_XPolygon, textBox_YPolygon) == "OK")
            {
                Point pointPolygon = new Point(int.Parse(textBox_XPolygon.Text), int.Parse(textBox_YPolygon.Text));
                listBox_polygon.Items.Add(pointPolygon);
            }
        }

        private void buttonDrawPolygon_Click(object sender, EventArgs e)
        {
            if (CheckFields(label_message, textBox_XPolygon, textBox_YPolygon) == "OK")
            {
                if (listBox_polygon.Items.Count > 2)
                {
                    Point[] points = listBox_polygon.Items.OfType<Point>().ToArray();
                    NewObject newPolygon = new NewObject(points);
                    newPolygon.FillNewPolygon(g);
                    polygonShouldBeRemove = newPolygon;
                    stackObjects.AddFirst(newPolygon);
                    listBox_polygon.Items.Clear();
                }
                else
                {
                    label_message.Text = "Недостаточно точек для отрисовки многоугольника";
                }
            }
        }

        private void buttonClearPolygon_Click(object sender, EventArgs e)
        {
            DeleteObject del = new DeleteObject();
            polygonShouldBeRemove = del.ReturnShouldBeRemove(stackObjects, polygonShouldBeRemove, g, label_message, "POLYGON");
        }

        private void button_drawRPolygon_Click(object sender, EventArgs e)
        {
            if (CheckFields(label_message, textBox_XRPolygon, textBox_YRPolygon,
                textBox_radiusPolygon, textBox_polygonAngles) == "OK")
            {
                if (int.Parse(textBox_polygonAngles.Text) > 2)
                {
                    NewObject newRPolygon = new NewObject(textBox_XRPolygon, textBox_YRPolygon,
                        textBox_radiusPolygon, textBox_polygonAngles);
                    newRPolygon.FillNewRPolygon(g);
                    R_polygonShouldBeRemove = newRPolygon;
                    stackObjects.AddFirst(newRPolygon);
                }
                else
                {
                    label_message.Text = "Недостаточно точек для отрисовки правильного многоугольника";
                }
            }
        }

        private void button_ClearRPolygon_Click(object sender, EventArgs e)
        {
            DeleteObject del = new DeleteObject();
            R_polygonShouldBeRemove = del.ReturnShouldBeRemove(stackObjects, R_polygonShouldBeRemove, g, label_message, "R_POLYGON");
        }
    }

}