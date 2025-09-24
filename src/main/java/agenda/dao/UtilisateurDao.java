/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao;

import agenda.dao.utils.GenericDao;
import agenda.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author code
 */
@Stateless
public class UtilisateurDao extends GenericDao<Utilisateur, Integer> {

    public UtilisateurDao() {
        super(Utilisateur.class);
    }

    public Utilisateur trouverEmail(String email) {
        try {
            TypedQuery<Utilisateur> query = this.em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email",Utilisateur.class);
            query.setParameter("email", email);

            return  query.getSingleResult();
        } catch (NoResultException e) {
            return null ;
        }
    }
}
