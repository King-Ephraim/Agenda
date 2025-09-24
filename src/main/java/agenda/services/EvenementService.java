/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.services;

import agenda.dao.EvenementDao;
import agenda.entities.Evenement;
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
public class EvenementService extends GenericService<Evenement,Integer>{
    
    @Inject
    private EvenementDao evenementDao ;

    public List<Evenement> lister(Utilisateur utilisateur) {
        return this.evenementDao.lister(utilisateur);
    }
}
