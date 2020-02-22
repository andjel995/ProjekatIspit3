/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisImplementacija;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skladiste.genericko.GenerickoSkladiste;

/**
 *
 * @author User
 */
public class KorisnickiServis implements servis.KorisnickiServis{

    GenerickoSkladiste skladiste;

    public KorisnickiServis() {
        skladiste = new GenerickoSkladiste();
    }
    @Override
    public List<Korisnik> vratiSve() throws Exception {
        List<Korisnik> korisnici = new ArrayList<>();
       try{
           List<OpstiDomenskiObjekat> lista = skladiste.vratiSve(new Korisnik());
            for(OpstiDomenskiObjekat o : lista){
                korisnici.add((Korisnik) o);
            }
            return korisnici;
        } catch (SQLException ex) {
            Logger.getLogger(KorisnickiServis.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Korisnik pronadjiPoKorImenuiLozinci(String korisnickoIme, String lozinka) throws Exception {
      try {
            List<OpstiDomenskiObjekat> lista=skladiste.vratiSve(new Korisnik());
            for(OpstiDomenskiObjekat o : lista){
                Korisnik trenutni=(Korisnik) o;
                if(trenutni.getKorisnickoIme().equals(korisnickoIme)&& trenutni.getLozinka().equals(lozinka))
                    return trenutni;
            }
            throw new Exception("Korisnik ne postoji");
        } catch (SQLException ex) {
            Logger.getLogger(KorisnickiServis.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
}
