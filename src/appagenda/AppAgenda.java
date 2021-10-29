/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

/**
 *
 * @author chris
 */
import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class AppAgenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppAgendaPU");
        EntityManager em = emf.createEntityManager();
        
        //INSERTAR
        Provincia provinciaCadiz=new Provincia(1,"Cádiz");
        
        Provincia provinciaSevilla=new Provincia();
        provinciaSevilla.setNombre("Sevilla");
        
        em.getTransaction().begin();
        em.persist(provinciaCadiz);
        em.persist(provinciaSevilla);
        em.getTransaction().commit();
        
        //MODIFICAR
        Query queryProvinciaCadiz = em.createNamedQuery("Provincia.findByNombre");
        queryProvinciaCadiz.setParameter("nombre", "Cádiz");
        List<Provincia> listProvinciasCadiz =queryProvinciaCadiz.getResultList();
        em.getTransaction().begin();
        
        for(Provincia provinciaCadiz2 : listProvinciasCadiz){
            provinciaCadiz.setCodigo("CA");
            em.merge(provinciaCadiz);
        }
        em.getTransaction().commit();

        //ELIMINAR
        Provincia provinciaId15 = em.find(Provincia.class, 15);
        em.getTransaction().begin();
        if (provinciaId15 != null){
        em.remove(provinciaId15);
        }else{
        System.out.println("No hay ninguna provincia con ID=15");
        }
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        try{
        DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch (SQLException ex){
        }
        
        
    }
    
}
