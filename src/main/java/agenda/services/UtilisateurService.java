/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.services;

import agenda.dao.UtilisateurDao;
import agenda.entities.Utilisateur;
import agenda.services.utils.GenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 *
 * @author code
 */
@Stateless
public class UtilisateurService extends GenericService<Utilisateur, Integer>{
    
    @Inject
    private UtilisateurDao utilisateurDao;
    
    public Utilisateur trouverEmail(String email){
        return utilisateurDao.trouverEmail(email);
    }
    
}
