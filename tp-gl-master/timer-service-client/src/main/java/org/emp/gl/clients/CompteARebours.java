package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class CompteARebours implements TimerChangeListener{

    String name;
    int cpt; 
    TimerService timerService ; 


    public CompteARebours (String name, int cpt, TimerService timerService) {
        this.cpt = cpt ;
        this.name = name ;
        this.timerService = timerService ; 
        this.timerService.addTimeChangeListener(this);

        System.out.println ("Decompteur "+name+" initialisé à "+cpt+" secondes" ) ;

    }

    public void afficherCompte () {
        if (timerService != null && cpt >= 0)
            System.out.println (name + " affiche " + 
                                cpt + " secondes restantes") ;
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if(cpt >= 0) {
            afficherCompte();
            if(cpt == 0) {
                this.arreter();
            }
            else {
                cpt--;  
            }
        }
    }

    public void arreter() {
        this.timerService.removeTimeChangeListener(this);
        System.out.println("Décompteur " + name + " arrêté");
    }
}
