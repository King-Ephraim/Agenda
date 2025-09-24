/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.beans.forms;

import agenda.entities.Utilisateur;
import agenda.services.UtilisateurService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author code
 */
@Named
@SessionScoped
public class FormBean implements Serializable {

    @Inject
    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;

    @PostConstruct
    public void init() {
        this.utilisateur = new Utilisateur();
    }

    public String enregistrer() {
        this.utilisateurService.ajouter(this.utilisateur);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement effectué", "votre inscription a été effectué avec succes");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "loginForm?faces-redirect=true";
    }

    public String login() {
        String email = this.utilisateur.getEmail();
        String password = this.utilisateur.getPassword();
        
        Utilisateur user = this.utilisateurService.trouverEmail(email);
        
        if (email == null || email.isEmpty() || password == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "champs manquant", "les champs email et password doivent etre remplis");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (user == null || !password.equals(user.getPassword())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ou mot de passe incorrectes", "Email ou mot de passe incorrectes");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion reussie", "connexion reussie");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.utilisateur = user ;
            return "/acceuil?faces-redirect=true";
        }
        return null;
    }
    
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        return "/loginForm?faces-redirect=true";
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
