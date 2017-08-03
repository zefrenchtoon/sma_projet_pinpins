/*
 * ConfigPanel.java
 *
 * Created on 15 avril 2007, 11:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Panels;

import ProjetSMA.*;
import ProjetSMA.Listeners.ConfigListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author amercier
 */
public class ConfigPanel extends JPanel {
    private static String separator = System.getProperties().getProperty("line.separator");
    private static JButton ivjBGo = new JButton("Go!");
    private static JButton ivjBExit = new JButton("Exit");
    private static JCheckBox ivjCBoxSlow = new JCheckBox("Slow Pinpin ?");
    private static JCheckBox ivjCBoxDistrib = new JCheckBox("Distrib. Al\u00E9atoire");
    private static JLabel ivjLabelPinpin = new JLabel("Nombre de Pinpins");
    private static JLabel ivjLabelOpinion = new JLabel("Nombre d'opinions");
    private static JLabel ivjLabelNaivete = new JLabel("Degr\u00E9 de na\u00EFvet\u00E9");
    private static JLabel ivjLabelDuree = new JLabel("Fr\u00E9quence "+separator+"du Log");
    private static JTextField ivjTNumberPinpin = new JTextField("100");
    private static JTextField ivjTNumberOpinion = new JTextField("10");
    private static JTextField ivjTNumberNaivete = new JTextField("5");
    private static JTextField ivjTNumberDuree = new JTextField("20");
    private static APIMonde api = null;
    
    /**
	 * Creates a new instance of ConfigPanel
	 */
    public ConfigPanel(APIMonde api) {
	super(new GridLayout(4, 3, 5, 5));
	this.setBackground(Color.BLACK);
	
	ivjCBoxSlow.setBackground(Color.BLACK);
	ivjCBoxSlow.setForeground(Color.WHITE);
	ivjCBoxDistrib.setBackground(Color.BLACK);
	ivjCBoxDistrib.setForeground(Color.WHITE);
	if (System.getProperty("os.name").equals("Mac OS X")) {
	    ivjBGo.setForeground(Color.BLACK);
	    ivjBExit.setForeground(Color.BLACK);
	}
	ivjLabelPinpin.setForeground(Color.WHITE);
	ivjLabelOpinion.setForeground(Color.WHITE);
	ivjLabelNaivete.setForeground(Color.WHITE);
	ivjLabelDuree.setForeground(Color.WHITE);
	
	ConfigListener cl = new ConfigListener(this);
        ivjBGo.addActionListener(cl);
        ivjBExit.addActionListener(cl);
	
	this.add(ivjLabelPinpin);
	this.add(ivjTNumberPinpin);
	this.add(ivjBGo);
	this.add(ivjLabelOpinion);
	this.add(ivjTNumberOpinion);
	this.add(ivjBExit);
	this.add(ivjLabelNaivete);
	this.add(ivjTNumberNaivete);
	this.add(ivjCBoxDistrib);
	this.add(ivjLabelDuree);
	this.add(ivjTNumberDuree);
	this.add(ivjCBoxSlow);
	this.api = api;
    }
    
    public boolean isDistribSelected() {
	return this.ivjCBoxDistrib.isSelected();
    }
    
    public boolean isSlowSelected() {
	return this.ivjCBoxSlow.isSelected();
    }
    
    public int getNbPinpin() {
	return Integer.parseInt(this.ivjTNumberPinpin.getText());
    }
    
    public int getNbOpinion() {
	return Integer.parseInt(this.ivjTNumberOpinion.getText());
    }
    
    public int getDegreNaivete() {
	return Integer.parseInt(this.ivjTNumberNaivete.getText());
    }
    
    public int getDuree() {
	return Integer.parseInt(this.ivjTNumberDuree.getText());
    }
    
    public JButton getBGo() {
	return ivjBGo;
    }
    
    public JButton getBExit() {
	return ivjBExit;
    }
    
    public void deactivateComponents() {
	ivjBGo.setEnabled(false);
	ivjTNumberPinpin.setEnabled(false);
	ivjTNumberOpinion.setEnabled(false);
	ivjTNumberNaivete.setEnabled(false);
	ivjTNumberDuree.setEnabled(false);
	ivjCBoxDistrib.setEnabled(false);
	ivjCBoxSlow.setEnabled(false);
    }

    public void bGo_ActionPerformed(ActionEvent e) {
	    api.demarrage();
	    this.deactivateComponents();
    }
    public void bExit_ActionPerformed(ActionEvent e) {
	    api.arret();
    }
}
