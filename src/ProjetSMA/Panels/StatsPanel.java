/*
 * StatsPanel.java
 *
 * Created on 15 avril 2007, 11:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Panels;

import ProjetSMA.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author amercier
 */
public class StatsPanel extends JPanel{
    private APIMonde monde = null;
    
    public StatsPanel(APIMonde monde) {
	super();
	this.monde = monde;
	World w = monde.getMonde();
	this.setLayout(new GridLayout(1,w.getNbCouleurs(), 5, 5));
	JLabel monLabel = null;
	for(int i = 0; i < w.getNbCouleurs(); i++) {
	    monLabel = new JLabel("0",JLabel.CENTER);
	    monLabel.setBackground(w.getLaCouleur(i));
	    monLabel.setOpaque(true);
	    this.add(monLabel);
	}
	this.setBackground(Color.BLACK);
    }
    
    public int getNbAt(int i) {
	return Integer.parseInt(((JLabel)this.getComponent(i)).getText());
    }
    public void setNbAt(int i, int n) {
	((JLabel)this.getComponent(i)).setText(""+n);
    }
}
