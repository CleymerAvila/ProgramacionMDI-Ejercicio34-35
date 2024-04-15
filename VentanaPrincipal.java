package ProgramacionMDI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

    JDesktopPane panelInterno;

    JMenuBar barraMenu;
    JMenu menuArchivo;
    JMenuItem menuArchivoAbrir;
    JMenuItem menuArchivoCerrar;
    JMenuItem menuArchivoCerrarTodo;
    JMenuItem menuArchivoInfo;

    JMenu menuVentana;
    JMenuItem menuCascada;
    JSeparator separador;
    JMenuItem menuArchivoSalir;

    public static void main(String[] args) {
        VentanaPrincipal miventana = new VentanaPrincipal();
        miventana.setTitle("Visor de imagenes");
    }
    VentanaPrincipal(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true); 
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        barraMenu = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        menuArchivoAbrir = new JMenuItem("Abrir");
        menuArchivoInfo = new JMenuItem("Info");
        menuArchivoCerrar = new JMenuItem("Cerrar");
        menuArchivoCerrarTodo = new JMenuItem("Cerrar Todo");
        menuArchivoSalir = new JMenuItem("Salir");

        menuArchivo.add(menuArchivoAbrir);
        menuArchivo.add(menuArchivoCerrar);
        menuArchivo.add(menuArchivoCerrarTodo);
        menuArchivo.add(menuArchivoInfo);
        menuArchivo.addSeparator();
        menuArchivo.add(menuArchivoSalir);

        menuVentana = new JMenu("Ventana");
        menuCascada = new JMenuItem("Cascada");
        menuVentana.add(menuCascada);

        barraMenu.add(menuArchivo);
        barraMenu.add(menuVentana);

        add(barraMenu, BorderLayout.NORTH);

        panelInterno = new JDesktopPane();
        panelInterno.setBackground(new Color(11, 252, 249 ));
        add(panelInterno, BorderLayout.CENTER);


        menuArchivoAbrir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuArchivoAbrirActionPerformed(e);
            }
            
        });

        menuArchivoCerrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuArchivoCerrarActionPerformed(e);
            }
            
        });

        menuArchivoInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuArchivoInfoActionPerformed(e);
            }
            
        });

        menuArchivoCerrarTodo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuArchivoCerrarTodoActionPerformed(e);
            }
            
        });

        menuArchivoSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });

        menuCascada.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuCascadaActionPerformed(e);
            }
            
        });



    }

    public void menuArchivoAbrirActionPerformed(ActionEvent e){
        JFileChooser abrir  = new JFileChooser();
        int boton = abrir.showOpenDialog(null);
        if (boton==JFileChooser.APPROVE_OPTION) {
            // Creacion de la ventana interna para la imagen
            VentanaInterna vi = new VentanaInterna();
            vi.setClosable(true);
            vi.setResizable(true);
            vi.setMaximizable(true);
            vi.setIconifiable(true);
            

              
            panelInterno.add(vi);

            // Se asigna la imagen y se muestra la ventana interna
            String camino = abrir.getSelectedFile().getPath();
            VentanaInterna.setImage(camino);;
            vi.setTitle(camino);
            vi.setVisible(true);
          
        }

       
    }

    public void menuArchivoCerrarActionPerformed(ActionEvent e){
        VentanaInterna vActiva = (VentanaInterna) panelInterno.getSelectedFrame();

        if (vActiva != null) {
            vActiva.dispose();
        }
    }

    public void menuArchivoInfoActionPerformed(ActionEvent e){
        VentanaInterna vActiva = (VentanaInterna) panelInterno.getSelectedFrame();

        if (vActiva!=null) {
            String titulo;

            titulo = vActiva.getTitle();
            JOptionPane.showMessageDialog(null, "Ruta de la imagen :\n"+titulo);
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna ventana Seleccionada");
        }
    }

    public void menuArchivoCerrarTodoActionPerformed(ActionEvent e){
        JInternalFrame v[] = panelInterno.getAllFrames();

        for (int i = 0; i < v.length; i++) {
            v[i].dispose();
        }
    }

    public void menuCascadaActionPerformed(ActionEvent e){
        int x,y;

        JInternalFrame v[] = panelInterno.getAllFrames();

        x=0;
        y=0;
        for (int i = 0; i < v.length; i++) {
            v[i].setSize(600, 400);
            v[i].setLocation(x+=30, y+=30);
        }
    }
}
