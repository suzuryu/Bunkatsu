/**
 * Created by ryu on 2017/07/29.
 */
public class MyCircle {
    private PointClass center;
    double radius;

    MyCircle(PointClass point,double r){
        this.radius = r;
        this.center = point;
    }

    public boolean checkPointInside(PointClass p){
        double x = p.x - this.center.x;
        double y = p.y - this.center.y;

        return Math.sqrt((x*x)+(y*y)) < this.radius;
    }

}
