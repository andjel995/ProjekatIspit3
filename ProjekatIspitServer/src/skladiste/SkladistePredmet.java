/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladiste;

import domen.Angazovanje;
import domen.Predmet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface SkladistePredmet {
        List<Predmet> vratiSve() throws Exception;
        List<Angazovanje> vratiListuPoKriterijumu(Predmet odo) throws SQLException;
        Predmet vratiPoKriterijumu(Long id) throws SQLException;
}
