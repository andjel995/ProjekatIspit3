/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladista.baza;

import baza.konekcija.BazaKonekcija;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.Profesor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skladiste.ApstraktnoSkladistePredmet;
import skladiste.SkladistePredmet;
import skladiste.genericko.GenerickoSkladiste;

/**
 *
 * @author User
 */
public class StorageBazaPredmet implements SkladistePredmet{
    
    GenerickoSkladiste skladiste;

    public StorageBazaPredmet() {
        
        skladiste = new GenerickoSkladiste();
    }
    
    
    @Override
       public List<Predmet> vratiSve() throws Exception {
        List<Predmet> predmeti = new ArrayList<>();
       try{
           List<OpstiDomenskiObjekat> lista = skladiste.vratiSve(new Predmet());
            for(OpstiDomenskiObjekat o : lista){
                predmeti.add((Predmet) o);
            }
            return predmeti;
        } catch (SQLException ex) {
            Logger.getLogger(StorageBazaPredmet.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public Predmet nadjiPoId(Long predmetId) throws Exception {
         Predmet predmet = new Predmet();
        try {

            Connection connection = BazaKonekcija.getInstance().getConnection();
            String query = "SELECT * FROM predmet where predmetId = " + predmetId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("predmetId");
                String naziv = resultSet.getString(2);
                predmet = new Predmet(id, naziv);
            }
            resultSet.close();
            statement.close();
            return predmet;
        } catch (SQLException ex) {
            throw new Exception("Neuspesna konekcija! " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Angazovanje> vratiListuPoKriterijumu(Predmet odo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Predmet vratiPoKriterijumu(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
