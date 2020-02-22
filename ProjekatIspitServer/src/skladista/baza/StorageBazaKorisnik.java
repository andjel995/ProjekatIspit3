/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladista.baza;

import baza.konekcija.BazaKonekcija;
import domen.Korisnik;
import domen.Predmet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import skladiste.ApstraktnoSkladisteKorisnik;

/**
 *
 * @author User
 */
public class StorageBazaKorisnik extends ApstraktnoSkladisteKorisnik{
        /* public List<Korisnik> vratiSve() throws Exception 
         {
        List<Korisnik> lista = new LinkedList<>();
        try {

            Connection connection = BazaKonekcija.getInstance().getConnection();
            String query = "SELECT * FROM korisnik";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("korisnikId");
                String korisnickoIme = resultSet.getString(2);
                String lozinka = resultSet.getString(3);
                String ime = resultSet.getString(4);
                String prezime = resultSet.getString(5);
                Korisnik korisnik = new Korisnik(id,korisnickoIme,lozinka,ime,prezime);
                lista.add(korisnik);
            }
            resultSet.close();
            statement.close();
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Neuspesna konekcija! " + ex.getMessage(), ex);
        }
      }*/

         @Override
    public Korisnik pronadjiPoKorisnickomImenuILozinci(String korisnickoIme, String lozinka) throws Exception {

        try{
        Connection konekcija= BazaKonekcija.getInstance().getConnection();
      
        String upit="SELECT * FROM korisnik where korisnickoIme='" + korisnickoIme+"' and lozinka='"+lozinka+"';";
    
    Statement statement=konekcija.createStatement();
    ResultSet rs=statement.executeQuery(upit);
    
    while(rs.next()){
        Long idKorisnik= rs.getLong("korisnikId");
        String ime= rs.getString("ime");
        String prezime=rs.getString("prezime");
        String dbkorisnickoIme=rs.getString("korisnickoIme");
        String dblozinka=rs.getString("lozinka");
        
        return new Korisnik(idKorisnik, dbkorisnickoIme, dblozinka, ime, prezime);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    //rs.close();
    //statement.close();
    
    throw new Exception("Korisnik ne postoji u bazi");


    }  
    
}
