/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao;

import agenda.dao.utils.GenericDao;
import agenda.entities.Categorie;
import agenda.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author code
 */
@Stateless
public class CategorieDao extends GenericDao<Categorie,Integer>{
    
    public CategorieDao(){
        super(Categorie.class);
    }
    
     public List<Categorie> lister(Utilisateur utilisateur) {
        Query query = this.em.createQuery("SELECT c FROM Categorie c WHERE c.utilisateur = :utilisateur");
        query.setParameter("utilisateur", utilisateur);
        List<Categorie> list = query.getResultList();
        return list;
    }
}
