/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
public class ConsultaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppAgendaPU");
        EntityManager em = emf.createEntityManager();
        
        //MOSTRAR
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        List<Provincia> listProvincias = queryProvincias.getResultList();
        
        for(Provincia provincia : listProvincias){
            System.out.println(provincia.getNombre());
        }
        
        Query queryProvinciaCadiz = em.createNamedQuery("Provincia.findByNombre");
        queryProvinciaCadiz.setParameter("nombre", "Cádiz");
        List<Provincia> listProvinciasCadiz =queryProvinciaCadiz.getResultList();
        
        for(Provincia provinciaCadiz:listProvinciasCadiz){
            System.out.println(provinciaCadiz.getId()+":");
            System.out.println(provinciaCadiz.getNombre());
        }
        
        Provincia provinciaId2=em.find(Provincia.class,2);
        if (provinciaId2 != null){
            System.out.print(provinciaId2.getId() + ":");
            System.out.println(provinciaId2.getNombre());
        } 
        else {
            System.out.println("No hay ninguna provincia con ID=2");
        }
        
        


        
        em.close();
        emf.close();
        try{
        DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch (SQLException ex){
        }
    }
    
}
