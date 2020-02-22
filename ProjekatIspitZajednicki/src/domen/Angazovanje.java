/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Angazovanje extends OpstiDomenskiObjekat implements Serializable
{
    Long angazovanjeId;
    Predmet predmet;
    Profesor profesor;
    TipNastave tipNastave;

    public Angazovanje(Long angazovanjeId, Predmet predmet, Profesor profesor, TipNastave tipNastave) {
        this.angazovanjeId = angazovanjeId;
        this.predmet = predmet;
        this.profesor = profesor;
        this.tipNastave = tipNastave;
    }

    public Angazovanje() {
    }

        public Angazovanje( Predmet predmet, Profesor profesor, TipNastave tipNastave) {
        this.predmet = predmet;
        this.profesor = profesor;
        this.tipNastave = tipNastave;
    }
    public Long getAngazovanjeId() {
        return angazovanjeId;
    }

    public void setAngazovanjeId(Long angazovanjeId) {
        this.angazovanjeId = angazovanjeId;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    @Override
    public boolean equals(Object an) 
    {
        Angazovanje a = (Angazovanje) an;
        return a.getPredmet().equals(predmet) && a.getProfesor().equals(profesor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) throws Exception {
         List<OpstiDomenskiObjekat> lista = new ArrayList<>();
           while (resultSet.next()) {
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
    public String vratiUslovJoin() {
        return "angazovanje a  inner join predmet p on p.predmetId = a.predmet inner join profesor pr on a.profesor = pr.profesorId"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslov() {
        return ""; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> napuniJoin(ResultSet resultSet) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
           while (resultSet.next()) {
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
    public String vratiImeTabele() {
        return "angazovanje"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiKoloneZaUnos() {
        return " tipNastave, predmet, profesor "; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovBrisanja() {
        return " WHERE angazovanjeId = "+ this.angazovanjeId + " "; //To change body of generated methods, choose Tools | Templates.
    }

    @Override//vazno je da je u bazi tip nastave u istom redosledu kao u enumu
    public String vratiVrednostiZaUnos() {
       StringBuilder sb = new StringBuilder();
        sb.append(this.getTipNastave().toString()).append(",")
        .append(getPredmet().getPredmetId()).append(",")
        .append(getProfesor().getProfesorId())
      ;

        return sb.toString();        
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String dajUslovSelect() {
        return "a.angazovanjeId, a.tipNastave, p.predmetId as predmetId, p.naziv as predmetNaziv, pr.profesorId as profesorId, pr.ime as profesorIme, pr.prezime as profesorPrezime"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
