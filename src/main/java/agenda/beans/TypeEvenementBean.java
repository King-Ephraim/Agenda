/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.beans;

import agenda.beans.forms.FormBean;
import agenda.entities.TypeEvenement;
import agenda.services.TypeEvenementService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author code
 */
@Named
@ViewScoped
public class TypeEvenementBean implements Serializable{
    
    @Inject
    private TypeEvenementService typeEvenementService;
    
    @Inject
    private FormBean formBean ;
    
    private Integer idTypeEvenement;
    private TypeEvenement typeEvenement;
    private List<TypeEvenement> liste;
   

    @PostConstruct
    public void init() {
        this.liste = this.typeEvenementService.lister(this.formBean.getUtilisateur());
    }

    public void initEdition() {
        if (this.idTypeEvenement == null) {
            this.typeEvenement = new TypeEvenement();
        } else {
            this.typeEvenement = this.typeEvenementService.trouver(this.idTypeEvenement);
        }
        System.out.println("-> " + this.typeEvenement);
    }

    public String enregistrer() {
        
        this.typeEvenement.setUtilisateur(this.formBean.getUtilisateur());
        if (this.idTypeEvenement == null) {
            this.typeEvenementService.ajouter(this.typeEvenement);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout effectué", "Ajout d'une typeEvenement effectué avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            this.typeEvenementService.modifier(this.typeEvenement);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification effectuée", "Une typeEvenement mofifiée avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/typeEvenement.xhtml?faces-redirect=true";
    }

    public String supprimer(TypeEvenement typeEvenement) {
        this.typeEvenementService.supprimer(typeEvenement.getId());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression effectuée", "Un type d'evenement supprimé avec succès");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/typeEvenement.xhtml?faces-redirect=true";
    }

    public List<TypeEvenement> getListe() {
        return this.liste;
    }

    public Integer getIdTypeEvenement() {
        return idTypeEvenement;
    }

    public void setIdTypeEvenement(Integer idTypeEvenement) {
        this.idTypeEvenement = idTypeEvenement;
    }

    public TypeEvenement getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public void setFormBean(FormBean formBean) {
        this.formBean = formBean;
    }

    
}
