/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Angazovanje;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import konstante.StatusOdgovora;
import kontroler.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author User
 */
public class ObradaKlijentskihZahtevaNit extends Thread{
    Socket soket;

    public ObradaKlijentskihZahtevaNit(Socket s) {
    soket = s;
    }

    @Override
    public void run() {
        while(true)
        {
        try
        {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija())
            {
                case Operacije.PRIJAVA:
                    Korisnik korisnik = (Korisnik) kz.getPodaci();
                        try {
                            Korisnik k = Kontroler.dajInstancu().prijava(korisnik.getKorisnickoIme(), korisnik.getLozinka());
                            so.setStatus(StatusOdgovora.SUCCESS);
                            so.setPodaci(k);
                        } catch (Exception ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            so.setStatus(StatusOdgovora.ERROR);
                            so.setGreska(ex);
                        }
                        break;
                case Operacije.VRATI_SVA_ANGAZOVANJA:
                      List<Angazovanje> lista = Kontroler.dajInstancu().dajAngazovanja();
                        so.setPodaci(lista);
                        so.setStatus(StatusOdgovora.SUCCESS);
                        System.out.println("Vracena lista angazovanja");
                        break;
                case Operacije.VRATI_SVE_PREDMETE:
                      List<OpstiDomenskiObjekat> listaPredmeta = Kontroler.dajInstancu().dajPredmete();
                        so.setPodaci(listaPredmeta);
                        so.setStatus(StatusOdgovora.SUCCESS);
                        System.out.println("Vracena lista predmeta");
                        break;
                case Operacije.VRATI_SVE_PROFESORE:
                       List<OpstiDomenskiObjekat> listaProfesora = Kontroler.dajInstancu().dajListuProfesora();
                        so.setPodaci(listaProfesora);
                        so.setStatus(StatusOdgovora.SUCCESS);
                        System.out.println("Vracena lista profesora");
                case Operacije.SACUVAJ_ANGAZOVANJA:
                         List<Angazovanje> lista2 = (List<Angazovanje>) kz.getPodaci();
                        try {
                            for(Angazovanje angazovanje : lista2)
                            Kontroler.dajInstancu().sacuvajAngazovanje(angazovanje);
                            so.setStatus(StatusOdgovora.SUCCESS);
                        } catch (Exception ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            so.setStatus(StatusOdgovora.ERROR);
                            so.setGreska(ex);
                        }
                        break;
                case Operacije.VRATI_PREDMET_PO_ID:
                        Long idPredmet = (Long) kz.getPodaci();
                        try {
                            Predmet p = Kontroler.dajInstancu().nadjiPredmetPoId(idPredmet);
                            so.setPodaci(p);
                            so.setStatus(StatusOdgovora.SUCCESS);
                        } catch (Exception ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            so.setStatus(StatusOdgovora.ERROR);
                            so.setGreska(ex);
                        }
                        break;
                case Operacije.OBRISI_ANGAZOVANJA:
                    List<Angazovanje> listaAng = (List<Angazovanje>) kz.getPodaci();
                    for(Angazovanje a : listaAng)
                        Kontroler.dajInstancu().obrisiAngazovanje(a);
                    so.setStatus(StatusOdgovora.SUCCESS);
                    break;
            }
           posaljiOdgovor(so);

        }
        catch(Exception ex){}
        }
    }
   
       private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kz;
    }
       
     private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
