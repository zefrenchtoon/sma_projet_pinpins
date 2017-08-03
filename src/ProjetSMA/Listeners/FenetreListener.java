/*
 * FenetreListener.java
 *
 * Created on 15 avril 2007, 11:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Listeners;

import ProjetSMA.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author amercier
 */
public class FenetreListener implements WindowListener {
    private static APIMonde api = null;
    
    /** Creates a new instance of FenetreListener */
    public FenetreListener(APIMonde api) {
	this.api = api;
    }

    public void windowClosed(WindowEvent e) {
	this.api.arret();
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
	this.api.arret();
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
