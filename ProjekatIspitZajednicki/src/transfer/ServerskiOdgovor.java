/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import konstante.StatusOdgovora;

/**
 *
 * @author User
 */
public class ServerskiOdgovor implements Serializable {
    private StatusOdgovora status;
    private Exception greska;
    private Object podaci;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(StatusOdgovora status, Exception greska, Object podaci) {
        this.status = status;
        this.greska = greska;
        this.podaci = podaci;
    }

    public Object getPodaci() {
        return podaci;
    }

    public void setPodaci(Object podaci) {
        this.podaci = podaci;
    }

    public StatusOdgovora getStatus() {
        return status;
    }

    public void setStatus(StatusOdgovora status) {
        this.status = status;
    }

    public Exception getGreska() {
        return greska;
    }

    public void setGreska(Exception graska) {
        this.greska = greska;
    }
    
    
    
}
