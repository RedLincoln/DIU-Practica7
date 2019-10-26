
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aaron
 */

class InternalFrame extends JInternalFrame {

    //Contador estático que aumenta cada vez que instanciamos una ventana.
    static int openFrameCount = 1;
    //Posición de la ventana interna.
    static final int xOffset = 50, yOffset = 50;
    static int posX = 0, posY = 0;

    private BufferedImage img;


    public InternalFrame(String titulo, BufferedImage imagen){
        super(titulo,
                true, //Resizable
                true, //Closable
                true, //Maximizable
                true);//Iconifiable
        openFrameCount++;   
        img = imagen;
        setVisible(true);
        //Ponemos la localición de la ventana.
        setLocation(posX, posY);
        posX = xOffset * openFrameCount;
        posY = yOffset * openFrameCount;
        
        
        ImgHolder panel = new ImgHolder();
        panel.setImage(img);
        setMinimumSize(new Dimension(200,200));
        add(panel);
        pack();        
        
    }
    
    public static void resetOffsetPosition(){
        openFrameCount=1;
        posX=0;
        posY=0;
    }
    
    
    public BufferedImage getImg() {
        return img;
    }
}
