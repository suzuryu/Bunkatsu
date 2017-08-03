import java.nio.channels.Pipe;

/**
 * Created by ryu on 2017/07/29.
 */
public class Edge {
    private PointClass startPoint;
    private PointClass endPoint;

    Edge(PointClass start, PointClass end){
        this.startPoint = start;
        this.endPoint = end;
    }

    public boolean checkLineCross(Edge edge){
        double sign1 = ((this.startPoint.x - edge.startPoint.x) * (this.endPoint.y - edge.startPoint.y) + (this.endPoint.x - edge.startPoint.x) * (edge.startPoint.y - this.startPoint.y)) / 2;
        double sign2 = ((this.startPoint.x - edge.endPoint.x) * (this.endPoint.y - edge.endPoint.y) + (this.endPoint.x - edge.endPoint.x) * (edge.endPoint.y - this.startPoint.y)) / 2;

        if(sign1 * sign2 < 0){
            return true;
        }else {
            return false;
        }
    }

    public PointClass getEndPoint() {
        return endPoint;
    }

    public PointClass getStartPoint() {
        return startPoint;
    }

    public boolean equalEdge(Edge edge){
        if(this.startPoint.x == edge.startPoint.x&& this.startPoint.y == edge.startPoint.y && this.endPoint.x == edge.endPoint.x && this.endPoint.y == edge.endPoint.y){
            return true;
        }
        if(this.startPoint.x == edge.endPoint.x && this.startPoint.y == edge.endPoint.y && this.endPoint.x == edge.startPoint.x && this.endPoint.y == edge.startPoint.y){
            return  true;
        }
        return false;
    }

}