import com.sun.javafx.geom.Vec2d;
import javafx.scene.shape.Circle;

import java.util.*;

/**
 * Created by ryu on 2017/07/30.
 */
public class DelaunayDiagram {
    private PointClass points[];

    DelaunayDiagram(PointClass points[]){
         this.points = points;
    }

    public List<Triangle> DelaunayTrianguration(){
        List<Triangle> triangleList = new ArrayList<>();

        Triangle hugeTriangle = makeHugeTriangle();
        triangleList.add(hugeTriangle);

        for(PointClass p: this.points){

            List<Triangle> hitTriangleList = new ArrayList<>();
            for(Triangle t:triangleList){
                if(t.hasPointInExternalCircle(p)){
                    hitTriangleList.add(t);
                }
            }
            Deque<Edge> edgeStack = new ArrayDeque<Edge>();

            for(Triangle t:hitTriangleList){
                edgeStack.push(t.getEdge1());
                edgeStack.push(t.getEdge2());
                edgeStack.push(t.getEdge3());
                triangleList.remove(t);

                triangleList.add(new Triangle(t.getPoint1(),t.getPoint2(),p));
                triangleList.add(new Triangle(t.getPoint2(),t.getPoint3(),p));
                triangleList.add(new Triangle(t.getPoint3(),t.getPoint1(),p));
            }

            while(edgeStack.size() != 0) {
                Edge edge = edgeStack.pop();
                Deque<Triangle> commonEdgeTriangles = new ArrayDeque<Triangle>();
                for (Triangle t : triangleList) {
                    if (t.hasEdge(edge)) {
                        commonEdgeTriangles.push(t);
                    }
                }

                Triangle ABC;
                Triangle ABD;
                try {
                    ABC = commonEdgeTriangles.pop();
                    ABD = commonEdgeTriangles.pop();
                } catch (NoSuchElementException e) {
                    continue;
                }
                commonEdgeTriangles.push(ABD);
                commonEdgeTriangles.push(ABC);

                if (ABC.equalTriangle(ABD)) {
                    triangleList.remove(ABC);
                    triangleList.remove(ABD);
                    continue;
                }
                PointClass A = edge.getStartPoint();
                PointClass B = edge.getEndPoint();

                PointClass C = ABC.noCommonPointByEdge(edge);
                PointClass D = ABD.noCommonPointByEdge(edge);

                MyCircle externalCircle = ABC.getCircumscribedCircle(ABC);

                if (externalCircle.checkPointInside(D)) {

                    Triangle del1 = commonEdgeTriangles.pop();
                    Triangle del2 = commonEdgeTriangles.pop();
                    triangleList.remove(del1);
                    triangleList.remove(del2);
                    commonEdgeTriangles.push(del2);
                    commonEdgeTriangles.push(del1);

                    Triangle ACD = new Triangle(A, C, D);
                    Triangle BCD = new Triangle(B, C, D);
                    triangleList.add(ACD);
                    triangleList.add(BCD);

                    if (ACD.getEdge1().equalEdge(edge)) {
                        edgeStack.push(ACD.getEdge2());
                        edgeStack.push(ACD.getEdge3());
                    } else if (ACD.getEdge2().equalEdge(edge)) {
                        edgeStack.push(ACD.getEdge1());
                        edgeStack.push(ACD.getEdge3());
                    } else if (ACD.getEdge3().equalEdge(edge)) {
                        edgeStack.push(ACD.getEdge1());
                        edgeStack.push(ACD.getEdge2());
                    }

                    if (BCD.getEdge1().equalEdge(edge)) {
                        edgeStack.push(BCD.getEdge2());
                        edgeStack.push(BCD.getEdge3());
                    } else if (BCD.getEdge2().equalEdge(edge)) {
                        edgeStack.push(BCD.getEdge1());
                        edgeStack.push(BCD.getEdge3());
                    } else if (BCD.getEdge3().equalEdge(edge)) {
                        edgeStack.push(BCD.getEdge1());
                        edgeStack.push(BCD.getEdge2());
                    }
                }
            }
        }

        List<Triangle> returnTriangle = new ArrayList<Triangle>();
        for(Triangle t:triangleList){
            if(t.checkHasPoint(hugeTriangle.getPoint1()) || t.checkHasPoint(hugeTriangle.getPoint2()) || t.checkHasPoint(hugeTriangle.getPoint3())){
            }else{
                returnTriangle.add(t);
            }
        }
        System.out.println("returnTriangles : "+returnTriangle);
        return returnTriangle;
    }

    public Triangle makeHugeTriangle(){
        PointClass p1 = new PointClass(0,0);
        PointClass p2 = new PointClass(0,2000);
        PointClass p3 = new PointClass(1000,500);
        Triangle triangle = new Triangle(p1,p2,p3);

        return triangle;
    }


}
