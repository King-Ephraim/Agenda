/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.convertisseurs;


import agenda.entities.TypeEvenement;
import agenda.services.TypeEvenementService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 *
 * @author code
 */
@FacesConverter(value = "typeEvenementConverter", managed = true)
public class TypeEvenementConverter implements Converter<TypeEvenement>{
    
    @Inject
    private TypeEvenementService typeEvenementService ;

    @Override
    public TypeEvenement getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value == null || value.isEmpty()) return null;
        return typeEvenementService.trouver(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, TypeEvenement t) {
        if(t == null) return null ;
        return String.valueOf(t.getId());
    }
}
