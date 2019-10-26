
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;



public class ImgUmbralizer {
    
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    private static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    
    
    public static BufferedImage umbralizar(BufferedImage imagen,Integer umbral){
        
        Mat imagen_original = bufferedImageToMat(imagen);
        
        Mat imagenGris=new Mat(imagen_original.rows(),imagen_original.cols(),CvType.CV_8U);
        Mat imagenUmbralizada=new Mat(imagen_original.rows(),imagen_original.cols(),CvType.CV_8U);
        
        Imgproc.cvtColor(imagen_original,imagenGris,Imgproc.COLOR_BGR2GRAY);
        
        Imgproc.threshold(imagenGris,imagenUmbralizada,umbral,255,Imgproc.THRESH_BINARY);
        
        return (BufferedImage)HighGui.toBufferedImage(imagenUmbralizada);
    }
    
}
