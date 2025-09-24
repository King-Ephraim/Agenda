/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda.beans;

import agenda.beans.forms.FormBean;
import agenda.entities.Evenement;
import agenda.services.EvenementService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author code
 */
@Named
@SessionScoped
public class MoisBean implements Serializable {

    @Inject
    private EvenementService evenementService;

    @Inject
    private FormBean formBean;

    private ScheduleModel eventModel;
    private ScheduleEvent<?> event = new DefaultScheduleEvent<>();

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        List<Evenement> liste = this.evenementService.lister(this.formBean.getUtilisateur());

        for (Evenement e : liste) {
           this.ajouterDansCalendrier(e);
        }
    }

    public void recharger() {
        init(); // recharge tout depuis la BD
    }

    public void ajouterDansCalendrier(Evenement e) {
        DefaultScheduleEvent<Object> se = DefaultScheduleEvent.builder()
                .title(e.getIntitule())
                .startDate(LocalDateTime.of(e.getDateDebut(), e.getHeureDebut()))
                .endDate(LocalDateTime.of(e.getDateFin(), e.getHeureFin()))
                .allDay(e.isEstJourneeEntiere())
                .build();
        eventModel.addEvent(se);
    }

    public void setFormBean(FormBean formBean) {
        this.formBean = formBean;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent<?> getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent<?> event) {
        this.event = event;
    }

}
