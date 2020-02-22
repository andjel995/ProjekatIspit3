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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Korisnik extends OpstiDomenskiObjekat implements Serializable
{
    Long korisnikId;
    String korisnickoIme;
    String lozinka;
    String ime;
    String prezime;
    
    public Korisnik(Long korisnikId, String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnikId = korisnikId;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Korisnik() {
    }
    
     @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> korisnici = new ArrayList<>();
        try {
            while(rs.next()){
                long id = rs.getLong("id");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");                
                Korisnik korisnik = new Korisnik(id, korisnickoIme, lozinka, ime, prezime);
                korisnici.add(korisnik);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return korisnici;
    }
        
    

    @Override
    public String vratiImeTabele() {
        return "korisnik";
    }

    @Override
    public String vratiKoloneZaUnos() {
        return "id, ime, prezime, korisnickoIme, lozinka";
    }

        @Override
    public String toString() {
        return ime+" "+prezime; 
    }
    
    @Override
    public String vratiVrednostiZaUnos() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(getIme()).append("',")
                .append("'").append(getPrezime()).append("',")
                .append("'").append(getKorisnickoIme()).append("',")
                .append("'").append(getLozinka()).append("',");
        
        return sb.toString();
    }
    
    
    
}
