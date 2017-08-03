import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Created by ryu on 2017/07/30.
 */
public class MyCanvas extends Canvas {
    private PointClass[] points;


    MyCanvas(PointClass[] points){
        this.setBackground(Color.WHITE);
        this.setBounds(0,0,700,100);
        this.points = points;
    }

    public void setPoints(PointClass[] points){
        this.points = points;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke wideStroke = new BasicStroke(1.5f);
        g2.setStroke(wideStroke);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintDots(g2);
        //paintTotsuhou(g2);
        wideStroke = new BasicStroke(1.0f);
        g2.setStroke(wideStroke);
        paintDelaunay(g2);
    }

    public void paintDots(Graphics g){
        for(int i=0;i<points.length;i++){
            g.drawLine((int)points[i].x, (int)points[i].y, (int)points[i].x, (int)points[i].y);
        }
    }

    public void paintLine(Graphics g){
        PointClass nearPoint = points[0].getNearPoint(points);
        g.drawLine((int)points[0].x,(int)points[0].y, (int)nearPoint.x, (int)nearPoint.y);
    }

    public void paintTotsuhou(Graphics g){
        Graham graham = new Graham();
        List<PointClass> list;
        int xArray[],yArray[];

        list = graham.grahmScan(points);
        xArray = new int[list.size()];
        yArray = new int[list.size()];

        for(int i=0;i<list.size();i++){
            xArray[i] =(int) list.get(i).x;
            yArray[i] = (int)list.get(i).y;
        }
        g.drawPolygon(xArray, yArray,list.size());
    }

    public void paintTriangle(Graphics g){
        int x[] = {0,0,1000};
        int y[] = {0,2000,500};

        g.drawPolygon(x,y,x.length);
    }

    public void paintDelaunay(Graphics g){
        DelaunayDiagram delaunay = new DelaunayDiagram(this.points);
        List<Triangle> triangles = delaunay.DelaunayTrianguration();

        g.setColor(Color.RED);

        for(Triangle t:triangles){
            int x[] = {(int)t.getPoint1().x, (int)t.getPoint2().x, (int)t.getPoint3().x};
            int y[] = {(int)t.getPoint1().y, (int)t.getPoint2().y, (int)t.getPoint3().y};

            g.drawPolygon(x,y,x.length);
        }
    }

    public void paintCircle(){
        Graphics g = getGraphics();
        super.paint(g);

        g.drawOval(100,100,50,50);
    }


}
