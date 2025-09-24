/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao;

import agenda.dao.utils.GenericDao;
import agenda.entities.TypeEvenement;
import agenda.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author code
 */
@Stateless
public class TypeEvenementDao extends GenericDao<TypeEvenement,Integer>{
    
    public TypeEvenementDao(){
        super(TypeEvenement.class);
    }
    
    public List<TypeEvenement> lister(Utilisateur utilisateur) {
        Query query = this.em.createQuery("SELECT t FROM TypeEvenement t WHERE t.utilisateur = :utilisateur");
        query.setParameter("utilisateur", utilisateur);
        List<TypeEvenement> list = query.getResultList();
        return list;
    }
}
