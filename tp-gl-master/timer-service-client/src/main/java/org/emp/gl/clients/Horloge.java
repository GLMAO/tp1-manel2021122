package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class Horloge implements TimerChangeListener{

    String name; 
    TimerService timerService ; 


    public Horloge (String name, TimerService timerService) {
        this.name = name ;
        this.timerService = timerService ; 
        this.timerService.addTimeChangeListener(this);

        System.out.println ("Horloge "+name+" initialized!") ;
//        afficherHeure() ;
    }

    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                timerService.getHeures() +":"+
                                timerService.getMinutes()+":"+
                                timerService.getSecondes()) ;
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt/*String prop, Object oldValue, Object newValue*/) {
        if(TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())){    
            afficherHeure();
        }
    }

    public void arreter() {
        this.timerService.removeTimeChangeListener(this);
        System.out.println("Horloge " + name + " arrêtée");
    }
}
