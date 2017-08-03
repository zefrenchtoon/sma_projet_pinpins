/*
 * PinpinQuiSaitTout.java
 *
 * Created on 6 avril 2007, 18:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ProjetSMA.Pinpins;

import Gdima.basicagentcomponents.AgentName;
import Gdima.kernel.communicatingAgent.BasicCommunicatingAgent;
import ProjetSMA.*;
import ProjetSMA.Panels.StatsPanel;
import java.awt.Panel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import org.omg.SendingContext.RunTime;

/**
 *
 * @author Administrateur
 */
public class PinpinQuiSaitTout extends BasicCommunicatingAgent {
    protected static World leMonde = ProjetSMA.APIMonde.getMonde();
    private static StatsPanel stats = ProjetSMA.APIMonde.getIvjStatsPane();
    private static String date = new SimpleDateFormat("[yyMMdd_HHmm]").format(new Date());
    private int nombreDePinpin;
    private int nombreDOpinion;
    private int laNaivete;
    private int laDuree;
    
    /** Creates a new instance of PinpinQuiSaitTout */
    public PinpinQuiSaitTout(int unNombreDePinpin, int unNombreDOpinion, int uneNaivete, int uneDuree) {
        super();
        this.nombreDePinpin = unNombreDePinpin;
        this.nombreDOpinion = unNombreDOpinion;
        this.laNaivete = uneNaivete;
	this.laDuree = uneDuree;
    }
    
    public void step() {
	this.readAllMessages();
    }
    
    public void cafter(Integer ancien, Integer nouveau) {
	if( ! ((ancien instanceof Integer) && (nouveau instanceof Integer)) )
	    throw new ClassCastException();
	int temp = stats.getNbAt(ancien);
	stats.setNbAt(ancien,--temp);
	temp = stats.getNbAt(nouveau);
	stats.setNbAt(nouveau,++temp);
    }
    
    public void publier(Integer temps) {
        try {
            String fileName = "Pinpin["+this.nombreDePinpin+"_Op"+this.nombreDOpinion+"_Na"+this.laNaivete+"_T"+laDuree+"]_"+date+".log";
            FileWriter fw = new FileWriter(fileName,true);
            BufferedWriter log = new BufferedWriter(fw);
            log.append(temps+"\t");
            for(int i = 0;i<this.nombreDOpinion;i++)
                log.append((((JLabel)stats.getComponent(i)).getText())+"\t");
            log.newLine();
            log.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void declarer(Integer opinion) {
	int temp = stats.getNbAt(opinion);
	stats.setNbAt(opinion,++temp);
    }
    
}
