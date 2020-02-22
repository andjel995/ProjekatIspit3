package logika;

import domen.Angazovanje;
import domen.Korisnik;
import domen.Predmet;
import domen.Profesor;
import domen.RezultatCuvanja;
import domen.TipNastave;
import java.util.ArrayList;
import java.util.List;
import komunikacija.KomunikacijaSaServerom;
import konstante.Operacije;
import konstante.StatusOdgovora;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Kontroler {
    private static Kontroler instanca;
   
    private Kontroler() {
    }
    
   public static Kontroler dajInstancu()
   {
     if(instanca == null)
         instanca = new Kontroler();
     return instanca;
   }
    
   public Korisnik prijaviSe(String korisnickoIme, String lozinka) throws Exception
   {
       KlijentskiZahtev kz = new KlijentskiZahtev();
       kz.setOperacija(Operacije.PRIJAVA);
       kz.setPodaci(new Korisnik(null, korisnickoIme, lozinka, null, null));

       KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
       ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
       if(so.getStatus() == StatusOdgovora.SUCCESS)
       {
          return (Korisnik)so.getPodaci();
       }
       else 
           throw so.getGreska();
      
   }

    public List<Predmet> dajPredmete() throws Exception {
       KlijentskiZahtev kz = new KlijentskiZahtev();
       kz.setOperacija(Operacije.VRATI_SVE_PREDMETE);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        return (List<Predmet>) so.getPodaci();
    }

    public List<Profesor> dajListuProfesora() throws Exception {
       KlijentskiZahtev kz = new KlijentskiZahtev();
       kz.setOperacija(Operacije.VRATI_SVE_PROFESORE);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        return (List<Profesor>) so.getPodaci();
    }

    public List<Angazovanje> dajAngazovanja() throws Exception {
      KlijentskiZahtev kz = new KlijentskiZahtev();
       kz.setOperacija(Operacije.VRATI_SVA_ANGAZOVANJA);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        return (List<Angazovanje>) so.getPodaci();
    }

    public boolean proveriDuplikate(Profesor profesor, Predmet predmet, TipNastave tn, List<Angazovanje> lista) {
        for(Angazovanje angazovanje : lista)
        {
            if(angazovanje.getPredmet().equals(predmet) && (angazovanje.getProfesor()).equals(profesor))
                return true;
        }
        return false;
    }

    public RezultatCuvanja sacuvajSve(List<Angazovanje> lista) throws Exception {
        RezultatCuvanja rc = RezultatCuvanja.Uspesan;
        List<Angazovanje> listaZaCuvanje = new ArrayList<>();
        List<Angazovanje> angazovanja = dajAngazovanja();
        for(Angazovanje a: lista)
        {
            if(angazovanja.contains(a))
            {
                rc = RezultatCuvanja.Duplikati;
                continue;
            }
           a.setAngazovanjeId(Long.valueOf(angazovanja.size()));
           listaZaCuvanje.add(a);
        }
        //sacuvaj sve
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.SACUVAJ_ANGAZOVANJA);
        kz.setPodaci(listaZaCuvanje);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        if (so.getStatus() == StatusOdgovora.SUCCESS) {
            System.out.println("Angazovanja su sacuvana");;
        } else {
            throw so.getGreska();
        }
        return rc;
    }

    public List<Angazovanje> dajAngazovanjaZaOdredjeniPredmet(Long predmetId) throws Exception {       
       List<Angazovanje> lista =  dajAngazovanja();
       List<Angazovanje> listaOdabranihAngazovanja = new ArrayList<>();
       for(Angazovanje a : lista)
       {
           if(a.getPredmet().getPredmetId().equals(predmetId))
               listaOdabranihAngazovanja.add(a);
       }
       return listaOdabranihAngazovanja;
    }

    public boolean izmeniAngazovanja(Angazovanje a, List<Angazovanje> novaLista) throws Exception {
        int brojac = 0;
        boolean bezDuplikata = true;
        List<Angazovanje> postojecaAngazovanja = dajAngazovanjaZaOdredjeniPredmet(a.getPredmet().getPredmetId());
        List<Angazovanje> listaZaDodavanje = new ArrayList<>();
        for(Angazovanje angazovanje : novaLista)
        {
            if(postojecaAngazovanja.contains(angazovanje)){
                brojac++;
                continue;
            }
            else
            {
               listaZaDodavanje.add(angazovanje);
            }
        }
        //postojecaAngazovanja = dajAngazovanjaZaOdredjeniPredmet(a.getPredmet().getPredmetId());
        if(brojac == postojecaAngazovanja.size())
            bezDuplikata = false;
        
        List<Angazovanje> listaZaBrisanje = new ArrayList<>();
        for(Angazovanje angazovanje : postojecaAngazovanja)
        {
            if(!novaLista.contains(angazovanje))
            listaZaBrisanje.add(angazovanje);
        }
        
        sacuvajSve(listaZaDodavanje);
        obrisiAngazovanja(listaZaBrisanje);
       
        return bezDuplikata;
    }

    public Predmet nadjiPredmetPoId(Long predmetId) throws Exception 
    {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_PREDMET_PO_ID);
        kz.setPodaci(predmetId);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        if (so.getStatus() == StatusOdgovora.SUCCESS) {
            return (Predmet) so.getPodaci();
        }
        else
        {
            Exception ex = so.getGreska();
            throw ex;
        }
    }
    
    public void obrisiAngazovanja(List<Angazovanje> lista) throws Exception
    {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.OBRISI_ANGAZOVANJA);
        kz.setPodaci(lista);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        if (so.getStatus() == StatusOdgovora.SUCCESS) {
            System.out.println("uspesno brisanje");
        } else {
            Exception ex = so.getGreska();
            throw ex;
        }
    }
}
