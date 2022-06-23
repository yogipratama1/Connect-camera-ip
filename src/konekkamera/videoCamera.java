/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konekkamera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author WINDOWS 10
 */
public class videoCamera extends JPanel{
    VideoCapture camera; 

    public videoCamera(VideoCapture cam) 
    {

        camera  = cam; 

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        // TODO Auto-generated method stub

    }
    public BufferedImage Mat2BufferedImage(Mat m)
    {

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage img = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return img;


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Mat mat = new Mat();

        if( camera.read(mat))
        {
            System.out.print("IMAGE");


        }

        BufferedImage image = Mat2BufferedImage(mat);
        //Mat gray = turnGray(mat);
        //MatOfRect objects = new MatOfRect();
        //CascadeClassifier cas = new CascadeClassifier();
        //cas.detectMultiScale(gray,objects);
        //Mat thresh  = threash( gray);

        //BufferedImage image = Mat2BufferedImage(thresh);
        g.drawImage(image,10,10,image.getWidth(),image.getHeight(), null);

    }
    public Mat turnGray( Mat img)

    {
        Mat mat1 = new Mat();
        Imgproc.cvtColor(img, mat1, Imgproc.COLOR_RGB2GRAY);
        return mat1;
    }
    public Mat threash(Mat img)
    {
        Mat threshed = new Mat();
        int SENSITIVITY_VALUE = 100;
        Imgproc.threshold(img, threshed, SENSITIVITY_VALUE,255,Imgproc.THRESH_BINARY);
        return threshed;
    }


}
    

