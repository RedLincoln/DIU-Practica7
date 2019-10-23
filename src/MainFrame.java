import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainFrame extends javax.swing.JFrame {

    private File file;
    private BufferedImage img;
    private DemoInternalFrame original;
    private Map<DemoInternalFrame, Boolean> umbralizadas = new HashMap<>();
    
    public MainFrame() {
        initComponents();
        this.setLocation(0, 0);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        this.addWindowListener(onWindowClosing());
    }
    
    private WindowAdapter onWindowClosing(){
        return new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e){
                inCloseActionPerformed();
            }
            
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abrirMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        umbralizarMenuItem = new javax.swing.JMenuItem();
        cerrarMenuItem = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        abrirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        umbralizarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        umbralizarMenuItem.setText("Umbralizar");
        umbralizarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbralizarMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(umbralizarMenuItem);

        jMenuBar1.add(jMenu2);

        cerrarMenuItem.setText("Cerrar");
        cerrarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarMenuItemActionPerformed(evt);
            }
        });

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        cerrarMenuItem.add(jMenuItem3);

        jMenuBar1.add(cerrarMenuItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed

        JFileChooser fileChoser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png");
        fileChoser.setFileFilter(filter);
        fileChoser.setAcceptAllFileFilterUsed(false);
        fileChoser.showOpenDialog(this);
        File abre = fileChoser.getSelectedFile();
        if (abre!= null && !abre.exists()){
            JOptionPane.showMessageDialog(this, "El fichero " + abre.getAbsolutePath() + " no existe." );
        }else{
            try {
                checkImg(abre);
                original = new DemoInternalFrame("Original", img);
                original.addInternalFrameListener(onOriginalInternalFrameClosing());
                    desktop.add(original);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error interno");
            }
                
        }
    }//GEN-LAST:event_abrirMenuItemActionPerformed
    
    
    private void checkImg(File file) throws Exception{
        try {
            this.file = file;
            if (img != null){
                original.dispose();
                closeUmbralizedInternalFrames();
            }
            this.img = ImageIO.read(file);
        }catch(Exception e){
            throw new Exception("Error");
        }
    }
    
    private InternalFrameAdapter onOriginalInternalFrameClosing(){
        return new InternalFrameAdapter() {
            
            @Override
            public void internalFrameClosing(InternalFrameEvent evt){
                String ObjButtons[] = {"Si","No"};
                int PromptResult = JOptionPane.showOptionDialog(desktop,"Quiere Cerrar la Imagen Original?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION){
                   onOrinalClose();
                   original.dispose();
                   img = null;
                }
            }
        };
    }
    
    private void onOrinalClose(){
        if (umbralizadas.isEmpty()) return;
        
        String ObjButtons[] = {"Si","No"};        
        int response =  JOptionPane.showOptionDialog(desktop,"Desea Cerrar las imagenes umbralizadas?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if (response == JOptionPane.YES_OPTION){
            closeUmbralizedInternalFrames();
        }
    }
    
    
    private void closeUmbralizedInternalFrames(){
        for (DemoInternalFrame e: umbralizadas.keySet()){
            e.dispose();
        }
        umbralizadas.clear();
    }
    
    
    private void cerrarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarMenuItemActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cerrarMenuItemActionPerformed

    private void umbralizarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umbralizarMenuItemActionPerformed
        if (file == null){
            JOptionPane.showMessageDialog(this, "Seleccione una imagen primero");
            return;
        }
        
        String input = JOptionPane.showInputDialog(this,"Introduzca un entero",
                                                        "Aplicar Umbral",
                                                         JOptionPane.QUESTION_MESSAGE);
        if ((input != null) && (input.length() > 0)) {
            return;
        }
        
        try{
            int value = Integer.parseInt(input);
            if (value < 0 ||value > 255) {
                throw new Exception("El umbral debe estar entre 0 y 255");
            }
            //BufferedImage imgUmbralizada = ImgUmbralizer.umbralizar(ImageIO.read(file), value);
            BufferedImage imgUmbralizada = deepCopy(img);
            DemoInternalFrame demo = new DemoInternalFrame("Umbral: " + value, imgUmbralizada);
            umbralizadas.put(demo, false);
            desktop.add(demo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Introduzca solo valores enteros entre 0 y 255");
        }
    }//GEN-LAST:event_umbralizarMenuItemActionPerformed
    
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    private InternalFrameAdapter onUmbralizedInternalFrameClosing(){
        return new InternalFrameAdapter() {
            
            @Override
            public void internalFrameClosing(InternalFrameEvent evt){
                String ObjButtons[] = {"Si","No"};
                int PromptResult = JOptionPane.showOptionDialog(desktop,"Quiere Cerrar la Imagen Umbralizada?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION){
                    DemoInternalFrame active = (DemoInternalFrame)evt.getInternalFrame();
                    askForUmbralizedSave(active);
                    active.dispose();
                }
            }
        };
    }
    
    
    private void askForUmbralizedSave(DemoInternalFrame iframe){
        if (isSaved(iframe)) return;
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(desktop,"Desea guardar la imagen antes de cerrar?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION){
            //TODO: guardar imagen
        }
    }
    
    private boolean isSaved(DemoInternalFrame iframe){
        return umbralizadas.get(iframe);
    }
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        inCloseActionPerformed();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    
    private void inCloseActionPerformed(){
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(this,"Seguro que quieres salir","Salir",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(this, "Hasta Pronto !!");
            System.exit(0);
        }else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenu cerrarMenuItem;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem umbralizarMenuItem;
    // End of variables declaration//GEN-END:variables
}

class DemoInternalFrame extends JInternalFrame {

    //Contador estático que aumenta cada vez que instanciamos una ventana.
    static int openFrameCount = 1;
    //Posición de la ventana interna.
    static final int xOffset = 50, yOffset = 50;
    static int posX = 0, posY = 0;

    private BufferedImage img;

    public DemoInternalFrame(String titulo, BufferedImage imagen){
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
        add(panel);
        pack();        
    }
}
