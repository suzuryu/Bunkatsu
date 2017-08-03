import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.*;

/**
 * Created by ryu on 2017/07/26.
 */
public class Main extends JFrame {

    public static void main(String args[]){
        new Main();
    }
    int pointNum = 20;
    PointClass points[] = new PointClass[pointNum];
    int maxPos = 500;

    Main(){
        int appWidth = 1000;
        int appHeight = 1000;
        Container contentPane = getContentPane();

        MyCanvas canvas = new MyCanvas(points);
        setSize(appWidth, appHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        makePoints();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel,BorderLayout.EAST);
        contentPane.add(canvas, BorderLayout.WEST);

        TextArea textArea = new TextArea();
        textArea.setMaximumSize(new Dimension(400,300));

        JButton addButton = new JButton("Change points");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makePoints();
                canvas.setPoints(points);
                canvas.repaint();

                textArea.append(getTimeString() + " @ Change points. Point count : "+pointNum+"\n");
            }
        });
/*
        JButton eraseButton = new JButton("Erase");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.append(getTimeString() + " @ Erase\n");
            }
        });
*/
        panel.add(addButton);
//        panel.add(eraseButton);
        panel.add(textArea,BorderLayout.SOUTH);
    }

    public void makePoints() {
        Random rnd = new Random();
        for (int i = 0; i < pointNum; i++) {
            points[i] = new PointClass(rnd.nextInt(this.maxPos)+40, rnd.nextInt(this.maxPos)+300);
        }

    }

    public String getTimeString(){
        int time[] = new int[3];
        Calendar calendar = Calendar.getInstance();
        time[0] = calendar.get(calendar.HOUR_OF_DAY);
        time[1] = calendar.get(calendar.MINUTE);
        time[2] = calendar.get(calendar.SECOND);

        String returnS = String.format("%02d",time[0]) + ":" + String.format("%02d", time[1]) + ":" +  String.format("%02d", time[2]);

        return  returnS;
    }

}

