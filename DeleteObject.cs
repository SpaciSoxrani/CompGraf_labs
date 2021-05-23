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
    public class DeleteObject
    {
        public NewObject ReturnShouldBeRemove(LinkedList<NewObject> stackObjects, NewObject shouldBeRemove, Graphics g, Label label_message, string CURRENT_OBJECT)
        {
                if (stackObjects.Count() != 0 && stackObjects.Find(shouldBeRemove) != null) { stackObjects.Remove(shouldBeRemove); }
                g.Clear(Color.White);
                label_message.Text = "";
                if (stackObjects.Count != 0)
                {
                    foreach (NewObject l in stackObjects.Reverse())
                    {
                        switch (l.OBJECT_NAME)
                        {
                            case "LINE":
                                l.DrawNewLine(g);
                                if (CURRENT_OBJECT == "LINE")
                                    shouldBeRemove = l;
                                break;
                            case "CIRCLE":
                                if (CURRENT_OBJECT == "CIRCLE")
                                    shouldBeRemove = l;
                                l.FillNewCircle(g);
                                break;
                            case "POLYGON":
                                if (CURRENT_OBJECT == "POLYGON")
                                    shouldBeRemove = l;
                                l.FillNewPolygon(g);
                                break;
                            case "R_POLYGON":
                                if (CURRENT_OBJECT == "R_POLYGON")
                                    shouldBeRemove = l;
                                l.FillNewRPolygon(g);
                                break;
                        }
                    }
                }
                return shouldBeRemove;
            }
        
    }
}
