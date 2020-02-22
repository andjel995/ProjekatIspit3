/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.angazovanje;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class ObrisiAngazovanje extends OpstaSistemskaOperacija{

  private boolean sacuvano = false; 

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        //proveri
    }
    @Override
    protected void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        skladiste.obrisi((Angazovanje)odo);
        sacuvano=true;
    }

   
    
    public boolean getUspenost() {
        return sacuvano;
    }
    
}
