/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Predmet extends OpstiDomenskiObjekat implements Serializable{
  private Long predmetId;
  private String naziv;

    public Predmet() {
    }

    public Predmet(Long predmetId, String naziv) {
        this.predmetId = predmetId;
        this.naziv = naziv;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv; //To change body of generated methods, choose Tools | Templates.
    }

    public String vratiImeTabele() {
        return "predmet";
    }
    @Override
    public boolean equals(Object o) {
       Predmet p = (Predmet) o;
       if(p.getPredmetId() == null || this.predmetId == null)
           return p.getNaziv() == this.naziv;
        return p.getPredmetId().equals(this.predmetId);
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong("predmetId");
            String naziv = rs.getString(2);
            Predmet p = new Predmet(id,naziv);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public String vratiUslovJoin() {
        return " predmet p join angazovanje a on a.predmet = p.predmetId ";
     //To change body of generated methods, choose Tools | Templates.
    }
    public String vratiUslovPojedinacniJoin() {
         return " where predmetId=" + this.predmetId+" ";
    }
    @Override
    public String vratiUslov() {
         return " where predmetId=" + this.predmetId+" ";
    }

     @Override
    public ArrayList<OpstiDomenskiObjekat> napuniJoin(ResultSet resultSet) throws SQLException {
         ArrayList<OpstiDomenskiObjekat> lista =  new ArrayList<>();
    
        while(resultSet.next()){
              Long id = resultSet.getLong("angazovanjeId");
                TipNastave tipNastave = TipNastave.valueOf(resultSet.getString("tipNastave"));
                Long predmetId = resultSet.getLong("predmetId");
                String predmetNaziv = resultSet.getString("predmetNaziv");
                
                Long profesorId = resultSet.getLong("profesorId");
                String profesorIme = resultSet.getString("profesorIme");
                String profesorPrezime = resultSet.getString("profesorPrezime");

                Predmet predmet = new Predmet(predmetId, predmetNaziv);
                Profesor profesor = new Profesor(profesorId,profesorIme,profesorPrezime);
                
                Angazovanje a = new Angazovanje(id , predmet, profesor, tipNastave);
                lista.add(a);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat napuniPojedinacniJoin(ResultSet rs) throws SQLException {
        Predmet p = new Predmet();
        while (rs.next()) {
                Long id = rs.getLong("predmetId");
                String naziv = rs.getString(2);
                p = new Predmet(id, naziv);
            }
        return p;
    }
    
    
  
}
