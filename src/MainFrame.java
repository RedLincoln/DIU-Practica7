import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.beans.PropertyVetoException;
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
    private InternalFrame original;
    private Map<InternalFrame, Boolean> umbralizadas = new HashMap<>();
    private String ext = "jpg";
    
    public MainFrame() {
        initComponents();
        this.setMinimumSize(new Dimension(500,500));
        this.setLocation(0, 0);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        this.addWindowListener(onWindowClosing());
        this.setTitle("Practica 7");
        this.addComponentListener(onRezisable());
    }
    
    private WindowAdapter onWindowClosing(){
        return new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e){
                inCloseActionPerformed();
            }
            
        };
    }
    
    private ComponentAdapter onRezisable(){
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                Dimension dimension = desktop.getSize();  
                System.out.println(dimension);
                
                for(JInternalFrame frame : desktop.getAllFrames()){   
                    Point coordenadas = frame.getLocation();
                    Dimension frameSize = frame.getSize();
                    
                    Point nuevoCentro = compruebaCoordenadas(dimension,coordenadas,frameSize);
                    if(nuevoCentro!= null)frame.setLocation(nuevoCentro);
                    
                    }
                    
                }
            };       
        }

            private Point compruebaCoordenadas(Dimension dimension, Point coordenadas, Dimension frameSize) {
                if(coordenadas.getX()+frameSize.width>dimension.width){
                    
                    return new Point((int)coordenadas.getX()-5,(int)coordenadas.getY());
                    
                }else if(coordenadas.getY()+frameSize.height>dimension.height){
                    
                    return new Point((int)coordenadas.getX(),(int)coordenadas.getY()-5);
                    
                }else if(coordenadas.getX()<0){
                    
                    return new Point((int)coordenadas.getX()+5,(int)coordenadas.getY());
                    
                }else if(coordenadas.getY()<0){
                    
                    return new Point((int)coordenadas.getX(),(int)coordenadas.getY()+5);
                    
                }
                return null;
            }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abrirMenuItem = new javax.swing.JMenuItem();
        GuardarMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        umbralizarMenuItem = new javax.swing.JMenuItem();
        cerrarMenuItem = new javax.swing.JMenu();
        SalirMenuItem = new javax.swing.JMenuItem();

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

        jMenu1.setText("Archivo");

        abrirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirMenuItem);

        GuardarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        GuardarMenuItem.setText("Guardar");
        GuardarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(GuardarMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

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

        SalirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        SalirMenuItem.setText("Salir");
        SalirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirMenuItemActionPerformed(evt);
            }
        });
        cerrarMenuItem.add(SalirMenuItem);

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
        String[] ObjButtons = {"Si","No"};
        int result=JOptionPane.YES_OPTION;
        if(umbralizadas.containsValue(false))result = JOptionPane.showOptionDialog(desktop,"Quedan imagenes umbralizadas por guardar\n Desea cerrarlas sin guardar?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(result == JOptionPane.NO_OPTION)return;
        JFileChooser fileChoser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png");
        fileChoser.setFileFilter(filter);
        fileChoser.setAcceptAllFileFilterUsed(false);
        int resultado = fileChoser.showOpenDialog(this);
        File abre = fileChoser.getSelectedFile();
        if (abre!= null && !abre.exists()){
            JOptionPane.showMessageDialog(this, "El fichero " + abre.getAbsolutePath() + " no existe." );
        }else if(resultado == JFileChooser.APPROVE_OPTION){
            try {
                checkImg(abre);
                InternalFrame.resetOffsetPosition();
                original = new InternalFrame("Original", img);
                original.addInternalFrameListener(onOriginalInternalFrameClosing());
                desktop.add(original);
                setImgFocus(original);
            }catch(Exception e){System.out.println(e);}
                
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
                }else{
                    original.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        for (InternalFrame e: umbralizadas.keySet()){
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
        if ((input == null) && (input.length() <= 0)) {
            return;
        }
        
        try{
            int value = Integer.parseInt(input);
            if (value < 0 ||value > 255) {
                throw new Exception("El umbral debe estar entre 0 y 255");
            }
            BufferedImage imgUmbralizada = ImgUmbralizer.umbralizar(ImageIO.read(file), value);
            InternalFrame demo = new InternalFrame("* Umbral: " + value, imgUmbralizada);
            umbralizadas.put(demo, false);
            desktop.add(demo);
            demo.addInternalFrameListener(onUmbralizedInternalFrameClosing());
            setImgFocus(demo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Introduzca solo valores enteros entre 0 y 255");
        }
    }//GEN-LAST:event_umbralizarMenuItemActionPerformed

    
    private InternalFrameAdapter onUmbralizedInternalFrameClosing(){
        return new InternalFrameAdapter() {
            
            @Override
            public void internalFrameClosing(InternalFrameEvent evt){
                InternalFrame active = (InternalFrame)evt.getInternalFrame();
                if(isSaved(active)){
                    removeFromDesktop(active);
                    return;
                }
                String ObjButtons[] = {"Si","No"};
                int PromptResult = JOptionPane.showOptionDialog(desktop,"Quiere guardar esta imagen?","Cerrar",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                
                if(PromptResult==JOptionPane.YES_OPTION) saveUmbralizedImg(active);
                if(isSaved(active)|| PromptResult==JOptionPane.NO_OPTION) removeFromDesktop(active);
                
                active.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        };
    }
    
    
    private void saveUmbralizedImg(InternalFrame iframe){
        BufferedImage img= iframe.getImg();
        JFileChooser fileChoser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png");
        fileChoser.setFileFilter(filter);
        fileChoser.setAcceptAllFileFilterUsed(false);
        int result = fileChoser.showSaveDialog(this);
        if(result == JFileChooser.CANCEL_OPTION)return;
        if(result == JFileChooser.APPROVE_OPTION)saveImage(fileChoser.getSelectedFile(),img);
    }
    
    
    private void saveImage(File file, BufferedImage img){
        String absolutePath = file.getAbsolutePath();
        String filePath = null;
        if (file.isDirectory()){
            warningItIsDirectory();
        }else if (fileExist(file) && askForFileReplacement() ){   
            filePath = absolutePath;
        }else {
            filePath = getFileNameWithoutExtension(file) + "." + ext;
        }
        if (filePath == null) return;
        
        File saveFile =  new File(filePath);
        try {
            ImageIO.write(img, ext, saveFile);
            JOptionPane.showMessageDialog(this, "Imagen Guardada correctamente");
            umbralizadas.put((InternalFrame)desktop.getSelectedFrame(), true);
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error al guardar la imagen",
                                          "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void warningItIsDirectory(){
        JOptionPane.showMessageDialog(this, "Especifique un nombre de fichero", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private boolean askForFileReplacement(){
        int respinse = JOptionPane.showConfirmDialog(this, "El fichero exite, desea remplarlo?",
                                          "Existe firecho con mismo nombre",
                                          JOptionPane.OK_CANCEL_OPTION);
        return (respinse == JOptionPane.OK_OPTION);
    }
    
    private void removeFromDesktop(InternalFrame frame){
        umbralizadas.remove(frame);
        frame.dispose();
    }
    
    private boolean fileExist(File file){
        String path = file.getAbsolutePath();
        String pathWithExtension = path + "." + ext;
        return new File(path).exists() || new File(pathWithExtension).exists();
    }
    
    private String getFileNameWithoutExtension(File file){
        String fileName = file.getAbsolutePath();
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex != -1){
            fileName = fileName.substring(0, extensionIndex);
        }
        return fileName;
    }
    
    
    private boolean isSaved(InternalFrame iframe){
        return umbralizadas.get(iframe);
    }
    
    private void SalirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirMenuItemActionPerformed
        inCloseActionPerformed();
    }//GEN-LAST:event_SalirMenuItemActionPerformed

    private void GuardarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarMenuItemActionPerformed
        if(umbralizadas.isEmpty()){
            JOptionPane.showMessageDialog(this, "No hay images para guardar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(original.isSelected())return;
        InternalFrame active = (InternalFrame)desktop.getSelectedFrame();
        saveUmbralizedImg(active);
        if(isSaved(active)) active.setTitle(active.getTitle().substring(2));
        
    }//GEN-LAST:event_GuardarMenuItemActionPerformed
    
    
    private void inCloseActionPerformed(){
        String msn="Seguro que quieres salir";
        String ObjButtons[] = {"Si","No"};
        if(umbralizadas.containsValue(false)){
            msn="Quedan imagenes umbralizadas por guardar, ¿desea salir?";
            ObjButtons = new String[]{"Salir de todas formas","No"};
        }
        
        int PromptResult = JOptionPane.showOptionDialog(this,msn,"Salir",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION)
        {
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
    private javax.swing.JMenuItem GuardarMenuItem;
    private javax.swing.JMenuItem SalirMenuItem;
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenu cerrarMenuItem;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem umbralizarMenuItem;
    // End of variables declaration//GEN-END:variables

    private void setImgFocus(InternalFrame iFrame) {
        try{
            iFrame.setSelected(true);
        }catch(PropertyVetoException e){
            System.out.println(e);
        }    
    }
}
