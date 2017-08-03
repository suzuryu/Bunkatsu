import java.util.*;

/**
 * Created by ryu on 2017/07/30.
 */
public class Graham {
    public List<PointClass> grahmScan(PointClass points[]){
        PointClass p0;
        Deque<PointClass> pointStack = new ArrayDeque<PointClass>();

        List<PointClass> pointList = new ArrayList<>();

        //Min Y
        double minY = points[0].y;
        int minInd=0;
        for(int i=0;i<points.length;i++){
            if(points[i].y < minY){
                minY = points[i].y;
                minInd = i;
            }
        }
        p0 = points[minInd];
        p0.setHenkaku(0);
        points[minInd] = points[0];
        points[0] = p0;
        pointList.add(p0);

        //henkau
        for(int i=1;i<points.length;i++){
            points[i].setHenkaku(Math.atan2(points[i].y-p0.y, points[i].x - p0.x));
        }

        double minHenkaku;
        PointClass minPoint;

        for(int t=1;t<points.length;t++) {
            minHenkaku = points[t].getHenkaku();
            minInd = t;
            for (int i = t; i < points.length; i++) {
                if(points[i].getHenkaku() < minHenkaku){
                    minHenkaku = points[i].getHenkaku();
                    minInd = i;
                }
            }
            minPoint = points[minInd];
            points[minInd] = points[t];
            points[t] = minPoint;
        }

        pointStack.push(p0);
        pointStack.push(points[1]);

        PointClass checkPoint1 = p0;
        PointClass checkPoint2 = points[1];

        for(int i=2;i<points.length;i++){
            while(signTriangle(checkPoint1, checkPoint2, points[i])){
                pointStack.pop();
                checkPoint2 = pointStack.pop();
                checkPoint1 = pointStack.pop();
                pointStack.push(checkPoint1);
                pointStack.push(checkPoint2);
            }
            pointStack.push(points[i]);
            checkPoint2 = pointStack.pop();
            checkPoint1 = pointStack.pop();
            pointStack.push(checkPoint1);
            pointStack.push(checkPoint2);

        }
        List<PointClass> returnList = new ArrayList<>();
        int stackSize = pointStack.size();
        for(int i=0;i<stackSize;i++){
            returnList.add(pointStack.pop());
        }

        return returnList;
    }

    public boolean signTriangle(PointClass point1,PointClass point2, PointClass point3){
        double sign = ((point1.x - point3.x) * (point2.y - point3.y) + (point2.x - point3.x) * (point3.y - point1.y)) / 2;
        if(sign  < 0){
            return true;
        }else{
            return false;
        }
    }
}
