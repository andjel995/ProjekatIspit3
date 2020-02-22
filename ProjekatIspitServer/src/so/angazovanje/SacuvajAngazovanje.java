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
public class SacuvajAngazovanje extends OpstaSistemskaOperacija{

    private boolean sacuvano;
     @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        //nema preduslove
    }

    @Override
    protected void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
          skladiste.sacuvaj((Angazovanje) odo);
          sacuvano = true;
    }
     public boolean getUspenost() {
        return sacuvano;
    }
    
}
