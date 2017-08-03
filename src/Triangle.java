import java.util.Objects;

/**
 * Created by ryu on 2017/07/29.
 */
public class Triangle {
    private PointClass point1;
    private PointClass point2;
    private PointClass point3;
    private Edge edge1;
    private  Edge edge2;
    private  Edge edge3;

    Triangle(PointClass point1,PointClass point2, PointClass point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        this.edge1 = new Edge(point1,point2);
        this.edge2 = new Edge(point2, point3);
        this.edge3 = new Edge(point3, point1);
    }
/*
    public boolean checkInside(PointClass p){

        PointClass center = new PointClass((this.point1.x+this.point2.x+this.point3.x)/3,(this.point1.y+this.point2.y+this.point3.y)/3);
        Edge checkEdge = new Edge(center,p);


        if(checkEdge.checkLineCross(this.edge1) || checkEdge.checkLineCross(this.edge2) || checkEdge.checkLineCross(this.edge3)){
            return false;
        }else{
            return true;
        }
    }
*/
    public boolean hasEdge(Edge edge){
        return edge.equalEdge(this.edge1) || edge.equalEdge(this.edge2) || edge.equalEdge(this.edge3);
    }

    public PointClass getPoint1(){
        return this.point1;
    }
    public PointClass getPoint2(){
        return this.point2;
    }
    public PointClass getPoint3(){
        return this.point3;
    }

    public Edge getEdge1() {
        return this.edge1;
    }
    public Edge getEdge2() {
        return this.edge2;
    }
    public Edge getEdge3() {
        return this.edge3;
    }

    public PointClass noCommonPointByEdge(Edge edge){
        if(this.getEdge1().equalEdge(edge)){
            return this.getPoint3();
        }
        if(this.getEdge2().equalEdge(edge)){
            return this.getPoint1();
        }
        if(this.getEdge3().equalEdge(edge)){
            return this.getPoint2();
        }
        return null;
    }

    public boolean checkHasPoint(PointClass p){
        return this.point1.checkEquals(p) || this.point2.checkEquals(p) || this.point3.checkEquals(p);
    }

    public boolean hasPointInExternalCircle(PointClass p){
        MyCircle externalCircle = getCircumscribedCircle(this);
        return  externalCircle.checkPointInside(p);
    }

    public MyCircle getCircumscribedCircle(Triangle t){
        double x1_2 = t.getPoint1().x * t.getPoint1().x;
        double x2_2 = t.getPoint2().x * t.getPoint2().x;
        double x3_2 = t.getPoint3().x * t.getPoint3().x;
        double y1_2 = t.getPoint1().y * t.getPoint1().y;
        double y2_2 = t.getPoint2().y * t.getPoint2().y;
        double y3_2 = t.getPoint3().y * t.getPoint3().y;

        double c = 2 * ((t.getPoint2().x - t.getPoint1().x)*(t.getPoint3().y - t.getPoint1().y) - (t.getPoint2().y - t.getPoint1().y)*(t.getPoint3().x- t.getPoint1().x));

        double  x = ((t.getPoint3().y - t.getPoint1().y) * (x2_2 - x1_2 + y2_2 - y1_2) + (t.getPoint1().y - t.getPoint2().y) * (x3_2 - x1_2 + y3_2 - y1_2)) / c;
        double  y = ((t.getPoint1().x - t.getPoint3().x) * (x2_2 - x1_2 + y2_2 - y1_2) + (t.getPoint2().x - t.getPoint1().x) * (x3_2 - x1_2 + y3_2 - y1_2)) / c;

        double _x = t.getPoint1().x - x;
        double _y = t.getPoint1().y - y;

        double r = Math.sqrt((_x*_x) + (_y*_y));

        return new MyCircle(new PointClass(x,y),r);
    }

    public boolean equalTriangle(Triangle t){
        if(this.getPoint1().x == t.getPoint1().x && this.getPoint1().y == t.getPoint1().y ){
            if(this.getPoint2().x == t.getPoint2().x && this.getPoint2().y == t.getPoint2().y && this.getPoint3().x == t.getPoint3().x && this.getPoint3().y == t.getPoint3().y){
                return true;
            }
            if(this.getPoint2().x == t.getPoint3().x && this.getPoint2().y == t.getPoint3().y && this.getPoint3().x == t.getPoint2().x && this.getPoint3().y == t.getPoint2().y){
                return true;
            }
        }
        if(this.getPoint2().x == t.getPoint2().x && this.getPoint2().y == t.getPoint2().y ){
            if(this.getPoint1().x == t.getPoint1().x && this.getPoint1().y == t.getPoint1().y && this.getPoint3().x == t.getPoint3().x && this.getPoint3().y == t.getPoint3().y){
                return true;
            }
            if(this.getPoint1().x == t.getPoint3().x && this.getPoint1().y == t.getPoint3().y && this.getPoint3().x == t.getPoint1().x && this.getPoint3().y == t.getPoint1().y){
                return true;
            }
        }
        if(this.getPoint3().x == t.getPoint3().x && this.getPoint3().y == t.getPoint3().y ){
            if(this.getPoint2().x == t.getPoint2().x && this.getPoint2().y == t.getPoint2().y && this.getPoint1().x == t.getPoint1().x && this.getPoint1().y == t.getPoint1().y){
                return true;
            }
            if(this.getPoint2().x == t.getPoint1().x && this.getPoint2().y == t.getPoint1().y  && this.getPoint1().x == t.getPoint2().x && this.getPoint1().y == t.getPoint2().y){
                return true;
            }
        }
        return false;
    }
}
