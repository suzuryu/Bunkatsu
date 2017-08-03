/**
 * Created by ryu on 2017/08/03.
 */
public class PointClass {
    public double x;
    public double y;
    public double henkaku;

    PointClass(double x, double y){
        this.x = x;
        this.y = y;
    }

    public PointClass getNearPoint(PointClass points[]){
        double min, distance;
        int num=0;
        min = 100000000;
        for(int i=0;i<points.length;i++){
            distance = Math.sqrt((this.x - points[i].x)*(this.x - points[i].x)+(this.y - points[i].y)*(this.y - points[i].y));

            if(! (this.x == points[i].x && this.y == points[i].y)) {

                if (distance < min) {
                    num = i;
                    min = distance;
                }
            }

        }
        return points[num];
    }

    public double getHenkaku(){return this.henkaku;}
    public void setHenkaku(double henkaku){this.henkaku = henkaku;}

    public boolean checkEquals(PointClass other){
        return (this.x == other.x && this.y == other.y);
    }

}
