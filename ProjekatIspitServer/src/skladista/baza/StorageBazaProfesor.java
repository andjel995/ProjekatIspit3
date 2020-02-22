/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladista.baza;

import baza.konekcija.BazaKonekcija;
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
import skladiste.ApstraktnoSkladisteProfesor;
import skladiste.genericko.GenerickoSkladiste;

/**
 *
 * @author User
 */
public class StorageBazaProfesor extends ApstraktnoSkladisteProfesor{
    GenerickoSkladiste skladiste;

    public StorageBazaProfesor() {
        skladiste = new GenerickoSkladiste();
    }
    
    
     @Override
     public List<Profesor> vratiSve() throws Exception {
      /*  List<Profesor> lista = new ArrayList<>();
        try {

            Connection connection = BazaKonekcija.getInstance().getConnection();
            String query = "SELECT * FROM profesor";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("profesorId");
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                Profesor profesor = new Profesor(id, ime, prezime);
                lista.add(profesor);
            }
            resultSet.close();
            statement.close();
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Neuspesna konekcija! " + ex.getMessage(), ex);
        }*/
              List<Profesor> profesori = new ArrayList<>();
       try{
           List<OpstiDomenskiObjekat> lista = skladiste.vratiSve(new Predmet());
            for(OpstiDomenskiObjekat o : lista){
                profesori.add((Profesor) o);
            }
            return profesori;
        } catch (SQLException ex) {
            Logger.getLogger(StorageBazaPredmet.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
}
