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
public class VratiListuPredmeta  extends OpstaSistemskaOperacija{
        private List<OpstiDomenskiObjekat> lista;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
         if(!(odo instanceof Predmet)){
            throw new Exception("Pogresan tip parametra");
        }
    }

    @Override
    protected void izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        lista=skladiste.vratiSve((Predmet)odo);
    }
    public List<OpstiDomenskiObjekat> vratiListu(){
        return lista;
    }
}
