/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.beans;

import agenda.beans.forms.FormBean;
import agenda.entities.Categorie;
import agenda.services.CategorieService;
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
public class CategorieBean implements Serializable {

    @Inject
    private CategorieService categorieService;
    
    @Inject
    private FormBean formBean ;
    
    private Integer idCategorie;
    private Categorie categorie;
    private List<Categorie> liste;

    @PostConstruct
    public void init() {
        this.liste = this.categorieService.lister(this.formBean.getUtilisateur());
    }

    public void initEdition() {
        if (this.idCategorie == null) {
            this.categorie = new Categorie();
        } else {
            this.categorie = this.categorieService.trouver(this.idCategorie);
        }
        System.out.println("-> " + this.categorie);
    }

    public String enregistrer() {
        
        this.categorie.setUtilisateur(this.formBean.getUtilisateur());
        if (this.idCategorie == null) {
            this.categorieService.ajouter(this.categorie);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout effectué", "Ajout d'une categorie effectué avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            this.categorieService.modifier(this.categorie);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification effectuée", "Une categorie mofifiée avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/categorie.xhtml?faces-redirect=true";
    }

    public String supprimer(Categorie categorie) {
        this.categorieService.supprimer(categorie.getId());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression effectuée", "Une categorie supprimée avec succès");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/categorie.xhtml?faces-redirect=true";
    }

    public List<Categorie> getListe() {
        return this.liste;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setFormBean(FormBean formBean) {
        this.formBean = formBean;
    }
    
    
}
