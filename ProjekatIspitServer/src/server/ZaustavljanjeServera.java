/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katarina
 */
public class ZaustavljanjeServera extends Thread{
    ServerSocket ss;
    boolean kraj=false;
    PokretanjeServera ps;

    public ZaustavljanjeServera(ServerSocket ss, PokretanjeServera ps) {
        this.ss=ss;
        this.ps=ps;
    }
    
    @Override
    public void run() {
        while(!kraj){
            if(ps.isInterrupted()){
                try {
                    ss.close();
                    kraj = true;
                } catch (IOException ex) {
                    Logger.getLogger(ZaustavljanjeServera.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
}
