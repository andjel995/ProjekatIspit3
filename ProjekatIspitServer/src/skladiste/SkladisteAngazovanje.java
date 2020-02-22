/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladiste;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface SkladisteAngazovanje {
   public List<Angazovanje> vratiSve(Angazovanje odo) throws SQLException ;
   public void dodajAngazovanje(Angazovanje a) throws Exception;
}
