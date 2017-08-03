/*
 * PinpinPasseTemps.java
 *
 * Created on 6 avril 2007, 18:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Pinpins;

import Gdima.basiccommunicationcomponents.Message;
import Gdima.kernel.communicatingAgent.BasicCommunicatingAgent;
import ProjetSMA.*;
import ProjetSMA.Panels.StatsPanel;
import java.awt.Panel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Administrateur
 */

public class PinpinPasseTemps extends BasicCommunicatingAgent {
    
    private MyTimer monTaf;
    private PinpinQuiSaitTout lePinpinQuiSaitTout;
    private int nombreEcriture = 0;
    private int duree;
    
    public PinpinPasseTemps(PinpinQuiSaitTout lePinpinQuiSaitTout, int duree){
	super();
	this.setMonTaf(new MyTimer(this, duree));
	this.lePinpinQuiSaitTout = lePinpinQuiSaitTout;
	this.addAquaintance(lePinpinQuiSaitTout);
	this.lePinpinQuiSaitTout.addAquaintance(this);
        this.duree = duree;
	Message m = new Message("publier",duree*nombreEcriture);
        this.sendMessage(this.lePinpinQuiSaitTout.getId(), m);
    }
    
    public void step() {
    }
    
    class MyTimer extends Timer {
	private int duree;
	public MyTimer(PinpinPasseTemps lePinpinPasseTemps, int duree) {
	    super();
	    this.duree = duree;
	    this.schedule(new maTache(lePinpinPasseTemps), duree*1000);
	}
	private class maTache extends TimerTask {
	    private PinpinPasseTemps lePinpinPasseTemps;
	    private World leMonde = ProjetSMA.APIMonde.getMonde();
	    private StatsPanel stats = ProjetSMA.APIMonde.getIvjStatsPane();
	    
	    maTache(PinpinPasseTemps lePinpinPasseTemps) {
		this.lePinpinPasseTemps = lePinpinPasseTemps;
	    }
	    public void run() {
		this.lePinpinPasseTemps.sendMessage(
		    lePinpinPasseTemps.lePinpinQuiSaitTout.getId(),
		    new Message("publier",(Integer)(duree*++nombreEcriture)));
		this.lePinpinPasseTemps.setMonTaf(new MyTimer(lePinpinPasseTemps, duree));
	    }
	}
    }
    
    public void setMonTaf(MyTimer monTaf) {
	this.monTaf = monTaf;
    }
}
