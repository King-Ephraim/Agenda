/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.convertisseurs;

import agenda.entities.Categorie;
import agenda.services.CategorieService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 *
 * @author code
 */
@FacesConverter(value = "categorieConverter", managed = true)
public class categorieConverter implements Converter<Categorie> {

    @Inject
    private CategorieService categorieService ;
    
    @Override
    public Categorie getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value == null || value.isEmpty()) return null;
            return categorieService.trouver(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Categorie t) {
        if(t == null) return null ;
            return String.valueOf(t.getId());
    }
    
}
