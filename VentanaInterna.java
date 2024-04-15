package ProgramacionMDI;



import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class VentanaInterna extends JInternalFrame{
    public static JLabel etiImagen;

    VentanaInterna() {
        
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocation(100, 100);
        this.setVisible(true);
        this.setClosable(true);


        etiImagen = new JLabel();
        
        add(etiImagen);
    
    }

    public static void setImage(String camino){
        ImageIcon imagen = new ImageIcon(camino);
        etiImagen.setIcon(imagen);
    }
}
