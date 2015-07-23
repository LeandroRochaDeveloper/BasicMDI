/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmdi.example;

import basicmdi.util.AppConstants;
import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Leandro Rocha
 */
public class Desktop extends javax.swing.JFrame {    
    // JInternal Frames
    private InternalFrame tela;
    // System tray
    private final String appName = AppConstants.APP_NAME;
    private Image appLogoIcon;
    private SystemTray sysTray;
    private PopupMenu trayMenu;
    private MenuItem trayItemExit;
    private TrayIcon trayIcon;
    private MenuItem trayItemShowApp;
    // Background do JDesktopPane
    private BufferedImage background_image;
    /**
     * Creates new form ArduinoDesktop
     * 
     * Quando pressionado o botão de fechar, ele irá minimizar para a tray (hide on close).
     */
    public Desktop() {
        initComponents();
        /* Configura a tray */
        tray();
        /* Configura imagens da aplicacao */
         try {
             // Icone da aplicacao
            setIconImage(Toolkit.getDefaultToolkit().getImage(AppConstants.LOGO_PATH));     
            // Background do JDesktopPane
            background_image = ImageIO.read(new File(AppConstants.BACKGROUND_IMAGE));
            /*
            Para configurar a imagem no bg, devesse ir em "Propriedades" > 
                "Codigo" > "Codigo de Criacao Personalizado" do JDesktopPane
            new JDesktopPane() {
                @Override protected void paintComponent(Graphics grphcs) { 
                    super.paintComponent(grphcs); 
                    grphcs.drawImage(background_image, 0, 0, null);
                } 
                @Override public Dimension getPreferredSize() { 
                    return new Dimension(background_image.getWidth(), background_image.getHeight());
                }
            };
            */
         } catch (java.lang.ExceptionInInitializerError | NullPointerException e) {
            System.err.println(e.getCause());
         } catch (IOException ex) {
            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        /* Não permitir que as jinternalframes 'fujam' da tela */
        desktopPane.setDesktopManager(new BoundedDesktopManager());
        /* Confgura a tela para ser exibida em fullscreen */
        setExtendedState(this.MAXIMIZED_BOTH);// Tela inicia fullscreeam.          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new JDesktopPane() {@Override protected void paintComponent(Graphics grphcs) { super.paintComponent(grphcs); grphcs.drawImage(background_image, 0, 0, null);} @Override public Dimension getPreferredSize() { return new Dimension(background_image.getWidth(), background_image.getHeight());}};
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        desktopPane.setBackground(new java.awt.Color(51, 153, 255));

        fileMenu.setMnemonic('f');
        fileMenu.setText("Arquivo");

        openMenuItem.setText("Abrir");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Salvar");
        fileMenu.add(saveMenuItem);
        fileMenu.add(jSeparator1);

        exitMenuItem.setText("Sair");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        aboutMenuItem.setText("Sobre");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        byte resp = (byte) JOptionPane.showConfirmDialog(this, "Deseja sair?", "Aviso", JOptionPane.YES_NO_OPTION);
        switch (resp) {
            case 0:
                System.exit(0);
                break;
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "v1.0");
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:      
        tela = new InternalFrame(desktopPane);   
        tela.setVisible(true);
    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Look and feel */
        try {
            // Windows <3
            String sun_desktop = System.getProperty("sun.desktop");
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (sun_desktop.equalsIgnoreCase(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Desktop().setVisible(true);
            }
        });
    }
    
    /**
     * Metodo que configura a system tray
     */
    public final void tray() {
        //check to see if system tray is supported on OS.
        if (SystemTray.isSupported()) {
            sysTray = SystemTray.getSystemTray();
            //create logo icon image
            appLogoIcon = Toolkit.getDefaultToolkit().getImage(AppConstants.LOGO_PATH);// size: (16x16)
            //create popupmenu
            trayMenu = new PopupMenu();
            //create item
            trayItemExit = new MenuItem(AppConstants.TRAY_OPTIONS[1]);

            // Item to show the app
            trayItemShowApp = new MenuItem(AppConstants.TRAY_OPTIONS[0]);
            //add actionListener to second trayMenu item
            trayItemShowApp.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }
            });
            //add second item to popup trayMenu
            trayMenu.add(trayItemShowApp);
            //add item to trayMenu
            trayMenu.add(trayItemExit);
            //add action listener to the item in the popup trayMenu
            trayItemExit.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Verifico se o usuÃ¡rio realmente quer fechar o aplicativo.
                    byte resp = (byte) JOptionPane.showConfirmDialog(null, "Deseja sair?", "Aviso", JOptionPane.YES_NO_OPTION);
                    switch (resp) {
                        case 0:// Caso ele realmente queira.
                            System.exit(0);
                            break;
                    }
                }
            });
            //create system tray icon.
            trayIcon = new TrayIcon(appLogoIcon, appName, trayMenu);
            //add the tray icon to the system tray.
            try {
                sysTray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
    
    public class BoundedDesktopManager extends DefaultDesktopManager {
    // Fonte: http://stackoverflow.com/questions/8136944/preventing-jinternalframe-from-being-moved-out-of-a-jdesktoppane
        @Override
        public void beginDraggingFrame(JComponent f) {
            // Don't do anything. Needed to prevent the DefaultDesktopManager setting the dragMode
        }

        @Override
        public void beginResizingFrame(JComponent f, int direction) {
            // Don't do anything. Needed to prevent the DefaultDesktopManager setting the dragMode
        }

        @Override
        public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
            boolean didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
            if (!inBounds((JInternalFrame) f, newX, newY, newWidth, newHeight)) {
                Container parent = f.getParent();
                Dimension parentSize = parent.getSize();
                int boundedX = (int) Math.min(Math.max(0, newX), parentSize.getWidth() - newWidth);
                int boundedY = (int) Math.min(Math.max(0, newY), parentSize.getHeight() - newHeight);
                f.setBounds(boundedX, boundedY, newWidth, newHeight);
            } else {
                f.setBounds(newX, newY, newWidth, newHeight);
            }
            if (didResize) {
                f.validate();
            }
        }

        protected boolean inBounds(JInternalFrame f, int newX, int newY, int newWidth, int newHeight) {
            if (newX < 0 || newY < 0) {
                return false;
            }
            if (newX + newWidth > f.getDesktopPane().getWidth()) {
                return false;
            }
            if (newY + newHeight > f.getDesktopPane().getHeight()) {
                return false;
            }
            return true;
        }
    }
}
