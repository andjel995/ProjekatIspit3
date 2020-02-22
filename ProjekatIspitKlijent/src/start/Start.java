/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import forme.LoginForma;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import komunikacija.KomunikacijaSaServerom;

/**
 *
 * @author User
 */
public class Start {
    
    public static void main(String[] args) 
    {
        try {
                    Socket soket=new Socket("127.0.0.1", 9000);
                    KomunikacijaSaServerom.getInstanca().setSoket(soket);
                    
                    LoginForma lf = new LoginForma();
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    lf.setDefaultLookAndFeelDecorated(true);
                    
                    lf.setVisible(true);
                } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
