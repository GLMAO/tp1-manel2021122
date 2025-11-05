/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;
/**
 *
 * @author tina
 */
public interface TimeChangeProvider {

    public void addTimeChangeListener(PropertyChangeListener pl);

    public void removeTimeChangeListener(PropertyChangeListener pl);
}
