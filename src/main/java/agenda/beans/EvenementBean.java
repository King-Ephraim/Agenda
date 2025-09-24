/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.beans;

import agenda.beans.forms.FormBean;
import agenda.entities.Evenement;
import agenda.services.EvenementService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author code
 */
@Named
@ViewScoped
public class EvenementBean implements Serializable {

    @Inject
    private EvenementService evenementService;
    
    @Inject                                                         
    private FormBean formBean;
    
    @Inject
    private MoisBean moisBean ;
    
    private Integer idEvenement;
    private Evenement evenement;
    private List<Evenement> liste;
    
    private String requete ;
    
    private List<Evenement> filtreListe ;

    @PostConstruct
    public void init() {
        this.liste = this.evenementService.lister(this.formBean.getUtilisateur());
    }

    public void initEdition() {
        if (this.idEvenement == null) {
            this.evenement = new Evenement();
        } else {
            this.evenement = this.evenementService.trouver(this.idEvenement);
        }
        System.out.println("-> " + this.evenement);
    }

    public String enregistrer() {
        
        if(!this.validerDates() || !this.validerHeures()){
            return null;
        }
        
        this.evenement.setUtilisateur(this.formBean.getUtilisateur());
        if (this.idEvenement == null) {
            this.evenementService.ajouter(this.evenement);
            this.moisBean.ajouterDansCalendrier(evenement);
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout effectué", "Ajout d'une evenement effectué avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            this.evenementService.modifier(this.evenement);
            this.moisBean.recharger();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification effectuée", "Une evenement mofifiée avec succès");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/acceuil.xhtml?faces-redirect=true";
    }

    public String supprimer(Evenement evenement) {
        this.evenementService.supprimer(evenement.getId());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression effectuée", "Un évenement supprimée avec succès");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/acceuil.xhtml?faces-redirect=true";
    }

    private boolean validerDates() {
        if (this.evenement.getDateDebut() == null || this.evenement.getDateFin() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dates invalides", "les dates choisies sont invalides");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        if (this.evenement.getDateDebut().isAfter(this.evenement.getDateFin())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dates invalides", "La date de début doit être avant ou égale à la date de fin");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        
        return true;
    }
    
    private boolean validerHeures() {
        if (this.evenement.getHeureDebut()== null || this.evenement.getHeureFin() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Heures invalides", "les heures choisies sont invalides");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        if (this.evenement.getHeureDebut().isAfter(this.evenement.getHeureFin())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Heures invalides", "L'heure de début doit être avant l'heure de fin");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true ;
    }
    
    public void rechercher(){
        for(Evenement e : liste){
            if(requete == null || requete.isEmpty()){
                System.out.println("-> pas d'element correspondant" );
                filtreListe = null ;
            }
            if(requete.equals(e.getIntitule())){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "element trouvé", "l'element recherché a été trouvé");
                FacesContext.getCurrentInstance().addMessage(null, message);
                System.out.println("-> " + e);
                filtreListe = new ArrayList<>();
                filtreListe.add(e);
            }else{
                System.out.println("-> pas d'element correspondant" );
                filtreListe = null ;
            }
        }
        
    }

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    
    public List<Evenement> getListe() {
        if (filtreListe == null) {
            return this.liste;
        }else{
            return this.getFiltreListe();
        }
    }

    public List<Evenement> getFiltreListe() {
        return filtreListe;
    }

    public void setFiltreListe(List<Evenement> filtreListe) {
        this.filtreListe = filtreListe;
    }

    
    public Integer getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void setFormBean(FormBean formBean) {
        this.formBean = formBean;
    }
}
