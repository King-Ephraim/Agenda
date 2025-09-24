/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.services;

import agenda.dao.CategorieDao;
import agenda.entities.Categorie;
import agenda.entities.Utilisateur;
import agenda.services.utils.GenericService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author code
 */
@Stateless
public class CategorieService extends GenericService<Categorie,Integer>{
    @Inject
    private CategorieDao categorieDao ;
    
    
    public List<Categorie> lister(Utilisateur utilisateur) {
        return this.categorieDao.lister(utilisateur);
    }
}
