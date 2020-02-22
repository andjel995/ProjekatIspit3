/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import baza.konekcija.BazaKonekcija;
import domen.OpstiDomenskiObjekat;
import skladiste.genericko.GenerickoSkladiste;

/**
 *
 * @author User
 */
public abstract class OpstaSistemskaOperacija {
    protected GenerickoSkladiste skladiste;
    
        public OpstaSistemskaOperacija(){
        skladiste = new GenerickoSkladiste();
    }
        
       public final void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception
       {
           this.izvrsiSO(odo);
       }
       
       protected abstract void proveriPreduslov(OpstiDomenskiObjekat odo)throws Exception;
       
       private void pokreniTransakciju() throws Exception
       {
           BazaKonekcija.getInstance().pokreniTransakciju();
       }
        private void potvrdiTransakciju() throws Exception
       {
           BazaKonekcija.getInstance().potvrdiTransakciju();
       }
       private void ponistiTransakciju() throws Exception
       {
           BazaKonekcija.getInstance().ponistiTransakciju();
       }
       
       protected abstract void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception;
               }
