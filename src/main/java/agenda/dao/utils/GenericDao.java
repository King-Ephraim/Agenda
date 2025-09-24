/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.dao.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author code
 * @param <Entity>
 * @param <Key>
 */
public abstract class GenericDao<Entity, Key> {

    @PersistenceContext(unitName = "agendaPU")
    protected EntityManager em;

    protected Class<Entity> classEntity;
    
    protected GenericDao(Class<Entity> classEntity){
        this.classEntity = classEntity;
    }

    public void ajouter(Entity entity) {
        this.em.persist(entity);
    }

    public void modifier(Entity entity) {
        this.em.merge(entity);
    }

    public void supprimer(Key id) {
        String className = this.classEntity.getSimpleName();
        Query query = this.em.createQuery("DELETE FROM " + className + " e WHERE e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Entity trouver(Key id) {
        return this.em.find(classEntity, id);
    }

    public List<Entity> lister() {
        String className = this.classEntity.getSimpleName();
        Query query = this.em.createQuery("SELECT e FROM " + className + " e");
        List<Entity> list = query.getResultList();
        return list;
    }
}
