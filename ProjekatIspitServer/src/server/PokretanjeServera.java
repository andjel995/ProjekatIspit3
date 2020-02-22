/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Katarina
 */
public class PokretanjeServera extends Thread{
    ServerskaForma sf;

    public PokretanjeServera(ServerskaForma sf) {
        this.sf = sf;
    }
    
      

     public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            sf.pokrenutServer();
            System.out.println("Server se pokrenuo");
            ZaustavljanjeServera sz = new ZaustavljanjeServera(ss, this);
            sz.start();
            while (true) {                
                Socket s = ss.accept();
                System.out.println("Klijent se povezao");
                ObradaKlijentskihZahtevaNit o = new ObradaKlijentskihZahtevaNit(s);
                o.start();
            }
            
        } catch (IOException ex) {
            sf.prekinutServer();
            System.out.println("Server je prekinut");
        }
    
}
}
