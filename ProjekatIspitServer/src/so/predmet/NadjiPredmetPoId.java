/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predmet;

import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class NadjiPredmetPoId extends OpstaSistemskaOperacija{

   Predmet p;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        //
    }

    @Override
    protected void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception 
    {
     p=(Predmet) skladiste.vratiPoKriterijumu(odo);
    }
    
    public Predmet vratiPredmet(){
        return p;
    }
    
}
