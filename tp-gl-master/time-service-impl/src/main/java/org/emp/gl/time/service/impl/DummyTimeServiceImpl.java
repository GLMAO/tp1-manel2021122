/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class DummyTimeServiceImpl
        implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    //List<TimerChangeListener> listeners = new LinkedList<>();
    private final PropertyChangeSupport pcs;

    /**
     * Constructeur du DummyTimeServiceImpl: ici, 
     * nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tics à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {

        this.pcs = new PropertyChangeSupport(this);

        setTimeValues();
        // initialize schedular
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
             @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

   


    @Override
    public void addTimeChangeListener(PropertyChangeListener listener) {
        // TODO
        pcs.addPropertyChangeListener(listener) ;
    }

    @Override
    public void removeTimeChangeListener(PropertyChangeListener listener) {
        // TODO
        pcs.removePropertyChangeListener(listener) ;  
        //listeners.remove(pl) ;
    }

    private void timeChanged() {
        int oldDixiemeDeSeconde = dixiemeDeSeconde;
        dixiemeDeSeconde = (dixiemeDeSeconde + 1) % 10;
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                oldDixiemeDeSeconde, dixiemeDeSeconde);
        if(dixiemeDeSeconde == 0) {
            int oldSecondes = secondes;
            secondes = (secondes + 1) % 60;
            pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP,
                    oldSecondes, secondes);
            if(secondes == 0) {
                int oldMinutes = minutes;
                minutes = (minutes + 1) % 60;
                pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP,
                        oldMinutes, minutes);
                if(minutes == 0) {
                    int oldHeures = heures;
                    heures = (heures + 1) % 24;
                    pcs.firePropertyChange(TimerChangeListener.HEURE_PROP,
                            oldHeures, heures);
                }
            }
        }
       // setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
       // if (dixiemeDeSeconde == newDixiemeDeSeconde)
         //   return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // informer les listeners !
        //dixiemeDeSecondesChanged(oldValue, dixiemeDeSeconde);
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                oldValue, dixiemeDeSeconde);
    }

   /* private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                   oldValue, dixiemeDeSeconde);
       }
    }*/


    public void setSecondes(int newSecondes) {
       // if (secondes == newSecondes)
         //   return;

        int oldValue = secondes;
        secondes = newSecondes;

        //secondesChanged(oldValue, secondes);
        pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP,
                oldValue, secondes);
    }

    /*private void secondesChanged(int oldValue, int secondes) {

       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.SECONDE_PROP,
                   oldValue, secondes);
       }
    }*/


    public void setMinutes(int newMinutes) {
        //if (minutes == newMinutes)
          //  return;

        int oldValue = minutes;
        minutes = newMinutes;

        //minutesChanged (oldValue, minutes) ;
        pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP,
                oldValue, secondes);
    }

    /*private void minutesChanged(int oldValue, int minutes) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.MINUTE_PROP,
                   oldValue, secondes);
       }
    }*/

    public void setHeures(int newHeures) {
       // if (heures == newHeures)
         //   return;

        int oldValue = heures;
        heures = newHeures;

        //heuresChanged (oldValue, heures) ;
        pcs.firePropertyChange(TimerChangeListener.HEURE_PROP,
                oldValue, secondes);
    }

    /*private void heuresChanged(int oldValue, int heures) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.HEURE_PROP,
                   oldValue, secondes);
       }
    }*/


    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}