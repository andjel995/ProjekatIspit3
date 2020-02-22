/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import konstante.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import domen.Korisnik;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.StatusOdgovora;
import kontroler.Kontroler;

/**
 *
 * @author Katarina
 */
public class Server {

    public void start() throws Exception {
        ServerSocket serverskiSoket= new ServerSocket(9000);
        Socket soket=serverskiSoket.accept();
        komunikacija(soket);
    }

    private void komunikacija(Socket soket) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
        
        while(true){
            Object objekat= ois.readObject();
            if(objekat instanceof KlijentskiZahtev){
                KlijentskiZahtev kz= (KlijentskiZahtev) objekat;
                ServerskiOdgovor so= obradiZahtev(kz);
                posaljiOdgovor(soket, so);
            }
            
        }
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {
        int operacija= kz.getOperacija();
        ServerskiOdgovor so= new ServerskiOdgovor();
        
        switch(operacija){
            case Operacije.PRIJAVA:
                Korisnik korisnikPrijava=(Korisnik) kz.getPodaci();
                try {
                        Korisnik korisnik = Kontroler.dajInstancu().prijava(korisnikPrijava.getKorisnickoIme(), korisnikPrijava.getLozinka());
                        so.setStatus(StatusOdgovora.SUCCESS);
                        so.setPodaci(korisnik);
                    } catch (Exception ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        so.setStatus(StatusOdgovora.ERROR.ERROR);
                        so.setGreska(ex);
                    }
                break;
                
            //case Operacije.VRATI_SVE_ARTIKLE:
              //  break;
            //case Operacije.VRATI_SVE_DOBAVLJACE:
              //  break;
        }
        return so;
    }

    private void posaljiOdgovor(Socket soket, ServerskiOdgovor so) throws IOException{
         ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
         oos.writeObject(so);
    }
    
}
