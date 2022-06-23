/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konekkamera;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.osgi.OpenCVInterface;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author WINDOWS 10
 */
public class Konekkamera {

    /**
     * @param args the command line arguments
     * @throws com.googlecode.javacv.FrameGrabber.Exception
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws Exception {

        OpenCVFrameGrabber frameGrabber = new OpenCVFrameGrabber("http://192.168.87.188:8080/video?dummy=param.mjpg");
        frameGrabber.setFormat("mjpeg");
        frameGrabber.start();
        IplImage iPimg = frameGrabber.grab();
        CanvasFrame canvasFrame = new CanvasFrame("camera");
        canvasFrame.setCanvasSize(iPimg.width(), iPimg.height());

        while (canvasFrame.isVisible() && (iPimg = frameGrabber.grab()) != null) {
            canvasFrame.showImage(iPimg);
        }
        frameGrabber.stop();
        canvasFrame.dispose();
        System.exit(0);

//CanvasFrame CamWindow = new CanvasFrame("Camera");
//
//        String Cam1Jpeg_url = "https://192.168.87.188:8080/snapshot.mjpeg?user=admin&pwd=1234";
//        URL url = new URL(Cam1Jpeg_url);
//
//        while(true){
//        InputStream is = url.openStream();
//        BufferedImage image = ImageIO.read(is);
//        CamWindow.showImage(image);
//        is.close();
//        }
    }
}
