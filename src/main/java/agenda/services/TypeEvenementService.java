/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.services;

import agenda.dao.TypeEvenementDao;
import agenda.entities.TypeEvenement;
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
public class TypeEvenementService extends GenericService<TypeEvenement, Integer> {

    @Inject
    private TypeEvenementDao typeEvenementDao;

    public List<TypeEvenement> lister(Utilisateur utilisateur) {
        return this.typeEvenementDao.lister(utilisateur);
    }

}
