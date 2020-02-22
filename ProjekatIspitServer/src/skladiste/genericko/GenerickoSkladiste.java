/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladiste.genericko;

import baza.konekcija.BazaKonekcija;
import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class GenerickoSkladiste {
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws Exception
    {
      List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        //try {
            String upit = "SELECT * FROM "+ odo.vratiImeTabele();
            System.out.println(upit);
            Connection konekcija = BazaKonekcija.getInstance().getConnection();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            lista = odo.vratiListu(rs);
            return lista;

    }
    
    public void obrisi(OpstiDomenskiObjekat odo) throws Exception
    {
        String upitObrisi = "DELETE FROM " + odo.vratiImeTabele()+ odo.vratiUslovBrisanja();
        System.out.println(upitObrisi);
        Statement sqlNaredba = BazaKonekcija.getInstance().getConnection().createStatement();
        sqlNaredba.executeUpdate(upitObrisi);
        sqlNaredba.close();
    }
        public OpstiDomenskiObjekat vratiPoKriterijumu(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiImeTabele()+ odo.vratiUslov();
        System.out.println(upit);
        Statement st = BazaKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        return odo.napuniPojedinacniJoin(rs);
    }
        
    public void sacuvaj(OpstiDomenskiObjekat odo) throws SQLException{
        String upit="INSERT INTO "+odo.vratiImeTabele()+"("+odo.vratiKoloneZaUnos()+") VALUES ("+odo.vratiVrednostiZaUnos()+")"; 
        System.out.println(upit);
        Statement sqlNaredba = BazaKonekcija.getInstance().getConnection().createStatement();
        sqlNaredba.execute(upit);
        sqlNaredba.close();
    }
   public List<OpstiDomenskiObjekat> vratiListuPoKriterijumu(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiUslovJoin() + odo.vratiUslov();
        System.out.println(upit);
        Statement st = BazaKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);

        return odo.napuniJoin(rs);
    }
     /* public List<OpstiDomenskiObjekat> vratiListuPoKriterijumuIzDveTabele(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT "+ odo.vratiUslovJoin() +odo.dajUslovSelect()+ " FROM " + odo.vratiUslov();
        System.out.println(upit);
        Statement st = BazaKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);

        return odo.napuniJoin(rs);
    }*/
}
