/*
 * ConfigListener.java
 *
 * Created on 15 avril 2007, 11:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Listeners;

import ProjetSMA.*;
import ProjetSMA.Panels.ConfigPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author amercier
 */
public class ConfigListener implements ActionListener {
    private ConfigPanel panel = null;
    
    /** Creates a new instance of ConfigListener */
    public ConfigListener(ConfigPanel panel) {
	this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.panel.getBGo()) {
            this.panel.bGo_ActionPerformed(e);
        }
        if(e.getSource() == this.panel.getBExit()) {
            this.panel.bExit_ActionPerformed(e);
        }
    }
    
}
