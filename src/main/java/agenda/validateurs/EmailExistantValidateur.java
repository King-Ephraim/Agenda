/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.validateurs;

import agenda.services.UtilisateurService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author code
 */
@Named
@RequestScoped
public class EmailExistantValidateur implements Validator{
    
    private static final String EMAIL_EXISTE_DEJA = "Adresse Email deja utilisée" ;
    
    @Inject
    private UtilisateurService utilisateurService ;

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        String email = (String) value ;
        if (this.utilisateurService.trouverEmail(email) != null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_EXISTE_DEJA, null)
            );
        }
    }

    
    
}
