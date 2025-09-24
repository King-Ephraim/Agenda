/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.services.utils;

import agenda.dao.utils.GenericDao;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author code
 * @param <Entity>
 * @param <Key>
 */
public abstract class GenericService<Entity,Key> {

    @Inject
    protected GenericDao<Entity,Key> genericDao;

    public void ajouter(Entity entity) {
        this.genericDao.ajouter(entity);
    }

    public void modifier(Entity entity) {
        this.genericDao.modifier(entity);
    }

    public void supprimer(Key id) {
        this.genericDao.supprimer(id);
    }

    public Entity trouver(Key id) {
        return (Entity) this.genericDao.trouver(id);
    }

    public List<Entity> lister() {
        return (List<Entity>) this.genericDao.lister();
    }

}
