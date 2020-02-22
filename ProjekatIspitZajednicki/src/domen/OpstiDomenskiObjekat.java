/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class OpstiDomenskiObjekat {
    public String vratiImeTabele() {
        return null;
    }
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception{
        return null;
    }    

    public String vratiKoloneZaUnos() {
        return null;
    }

    public String vratiVrednostiZaUnos() {
        return null;
    }

    public String vratiVrednostPrimarnogKljuca() {
        return null;
    }

    public String vratiUpitZaSledecuSifru() {
        return "";
    }

    public long vratiSledecuSifru(ResultSet rs) {
        return -1;
    }

    public String vratiUslov() {
        return "";
    }

    public String vratiVrednostiZaIzmenu() {
        return "";
    }
    public ArrayList<OpstiDomenskiObjekat> napuniJoin(ResultSet rs) throws SQLException{
        return null;
    }

    public String vratiUslovJoin(){
        return "";
    }

    public String vratiKriterijum() {
        return "";
    }

    public OpstiDomenskiObjekat vratiDomenskiObjekat(OpstiDomenskiObjekat odo) {
        return null;
    }
     public OpstiDomenskiObjekat napuniPojedinacniJoin(ResultSet rs) throws SQLException {
         return null;
     }
 
     public String vratiUslovBrisanja()
     {
         return "";
     }

    public String dajUslovSelect() {
        return "";
    }

}
