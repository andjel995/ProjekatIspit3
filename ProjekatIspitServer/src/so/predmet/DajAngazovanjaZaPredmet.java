/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predmet;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class DajAngazovanjaZaPredmet extends OpstaSistemskaOperacija{

   List<OpstiDomenskiObjekat> a;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        //
    }

    @Override
    protected void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
     a=(List<OpstiDomenskiObjekat>) skladiste.vratiListuPoKriterijumu(odo);
    }
    public List<OpstiDomenskiObjekat> vratiListu(){
        return a;
    }
    
}
