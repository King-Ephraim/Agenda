/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao;

import agenda.dao.utils.GenericDao;
import agenda.entities.Evenement;
import agenda.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author code
 */
@Stateless
public class EvenementDao extends GenericDao<Evenement,Integer>{
    
    public EvenementDao(){
        super(Evenement.class);
    }

    public List<Evenement> lister(Utilisateur utilisateur) {
        Query query = this.em.createQuery("SELECT e FROM Evenement e WHERE e.utilisateur = :utilisateur");
        query.setParameter("utilisateur", utilisateur);
        List<Evenement> list = query.getResultList();
        return list;
    }
    
}
