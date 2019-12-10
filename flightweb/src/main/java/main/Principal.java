/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Henrique
 */
public class Principal {
    
    public static void main (String [] args) {
    
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("br.hk.flightweb_flightweb_war_1.0PU");
        fabrica.close();
        
    }
    
}
