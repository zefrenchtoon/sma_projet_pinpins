package ProjetSMA;

import Gdima.basicagentcomponents.AgentIdentifier;
import Gdima.basicagentcomponents.AgentName;
import Gdima.kernel.communicatingAgent.BasicCommunicatingAgent;
import ProjetSMA.Listeners.FenetreListener;
import ProjetSMA.Panels.ConfigPanel;
import ProjetSMA.Panels.StatsPanel;
import ProjetSMA.Pinpins.Pinpin;
import ProjetSMA.Pinpins.PinpinPasseTemps;
import ProjetSMA.Pinpins.PinpinQuiSaitTout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class APIMonde extends JFrame {
    
    private static PinpinQuiSaitTout ivjPinpinQuiSaitTout;
    private static PinpinPasseTemps ivjPinpinPasseTemps;
    private static World ivjMonde = null;
    private static JPanel ivjContentsPane = null;
    private static ConfigPanel ivjConfigPane = null;
    private static StatsPanel ivjStatsPane = null;
    private int nombreDePinpin;
    private int nombreDOpinion;
    private int laNaivete;
    private int laDuree;
    private boolean slowPinpins;
    private boolean randomDistrib;
    private static Random alea = new Random();
    Vector lesPinpins = new Vector(getNombreDePinpin());
    /** Screen Size */
    private Dimension screenSize;
    
    private int tailleFenetre=300;

    public APIMonde() {
        initialize();
    }
    public APIMonde(String title) {
        super(title);
        initialize();
    }
    public void initialize() {
        ivjMonde = this.getMonde1();
	ivjConfigPane = new ConfigPanel(this);
	ivjStatsPane = new StatsPanel(this);
        this.setName("Les Pinpins se d\u00E9chainent !!!");
	int w = 375;
	int h = 540;
	this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (this.screenSize.width - this.getWidth ()) / 2;
	int y = (this.screenSize.height - this.getHeight ()) / 2;
	this.setLocation(x,y);
        this.setLayout(new BorderLayout());
        this.setSize(w, h);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
	this.setForeground(Color.WHITE);
        this.add(getContentsPane(), BorderLayout.CENTER);
        this.addWindowListener(new FenetreListener(this));
    }
    public void demarrage() throws ClassCastException {
        this.nombreDePinpin = this.ivjConfigPane.getNbPinpin();
        this.nombreDOpinion = this.ivjConfigPane.getNbOpinion();
        this.laNaivete = this.ivjConfigPane.getDegreNaivete();
        this.laDuree = this.ivjConfigPane.getDuree();
	this.slowPinpins = this.ivjConfigPane.isSlowSelected();
        this.randomDistrib = this.ivjConfigPane.isDistribSelected();
        
        if (this.getNombreDOpinion() > 10 || this.getNombreDOpinion() < 0)
            JOptionPane.showMessageDialog(null, "Le nombre d'opinions doit \u00EAtre compris entre 1 et 10 inclus.");
        
        else if (this.getLaNaivete() > 5 || this.getLaNaivete() < 0)
            JOptionPane.showMessageDialog(null, "Le degr\u00E9 de na\u00EFvet\u00E9 doit \u00EAtre compris entre 0 et 5 inclus.");
        
        else if (this.getLaDuree() < 0)
            JOptionPane.showMessageDialog(null, "Le temps doit (logiquement) \u00EAtre sup\u00E9rieur \u00E0 0.");
        
        else {
            tailleFenetre=300;
            ivjMonde.setParam(tailleFenetre, this.getNombreDePinpin(), this.getNombreDOpinion(), this.getLaNaivete(), this.getSlowPinpins());
            
            System.out.println("cr\u00E9ation des pinpins ");
            
            //----------------------- Creation des pinpins
            ivjPinpinQuiSaitTout = new PinpinQuiSaitTout(this.getNombreDePinpin(), this.getNombreDOpinion(), this.getLaNaivete(), this.getLaDuree());
            this.ivjPinpinQuiSaitTout.setId(new AgentName("PinpinQuiSaitTout"));
            
            for(int i = 0;i<this.getNombreDePinpin();i++) {
                int nbOpinion;
                if (this.randomDistrib) {
                    nbOpinion = alea.nextInt(this.getNombreDOpinion());
                }
                else {
                    nbOpinion = i%this.getNombreDOpinion();
                }
                Pinpin p = new Pinpin((int)(Math.random()*300),(int)(Math.random()*300), nbOpinion, getLaNaivete());
                p.setId(new AgentName("Pinpin"+nbOpinion+"_"+i));
                ivjPinpinQuiSaitTout.addAquaintance(p);
                lesPinpins.add(i,p);
            }
            
            for (int i=0; i < lesPinpins.size(); i++) {
                BasicCommunicatingAgent monAnimal = (BasicCommunicatingAgent)lesPinpins.get(i);
                for (int j =0; j < lesPinpins.size(); j++) {
                    if (i != j) {
                        BasicCommunicatingAgent monAutrePinpin = (BasicCommunicatingAgent)lesPinpins.get(j);
                        monAnimal.addAquaintance(monAutrePinpin);
                    }
                }
            }
	    ivjPinpinPasseTemps = new PinpinPasseTemps(ivjPinpinQuiSaitTout, this.ivjConfigPane.getDuree());
            lesPinpins.add(ivjPinpinPasseTemps);
            lesPinpins.add(ivjPinpinQuiSaitTout);
            //-------------------------------------------------------------
            ivjMonde.start(lesPinpins);
            System.out.println("Monde cr\u00E9e");
        }
    }
    public void arret() {
	ivjMonde.stop();
	ivjPinpinPasseTemps = null;
	ivjPinpinQuiSaitTout = null;
	dispose();
	System.exit(0);
    }
    private JPanel getContentsPane() {
        if(ivjContentsPane == null) {
            try {
                ivjContentsPane = new JPanel(new BorderLayout(5,5));
                ivjContentsPane.setBackground(Color.BLACK);
                ivjContentsPane.add(ivjConfigPane, BorderLayout.NORTH);
                ivjContentsPane.add(ivjStatsPane, BorderLayout.SOUTH);
		ivjContentsPane.add(getMonde1(), BorderLayout.CENTER);
                
            } catch(Throwable ivjExc) {
                handleException(ivjExc);
            }
	}
        return ivjContentsPane;
    }
    
    private World getMonde1() {
        if(ivjMonde == null)
            try {
                ivjMonde = new World();
                ivjMonde.setName("Monde1");
                ivjMonde.setLayout(new FlowLayout(FlowLayout.CENTER));
                ivjMonde.setBackground(Color.WHITE);
                System.out.println("TAILLE FENETRE "+tailleFenetre);
                //ivjMonde.setBounds(17, 110, tailleFenetre, tailleFenetre);
		ivjMonde.setSize(tailleFenetre, tailleFenetre);
            } catch(Throwable ivjExc) {
                handleException(ivjExc);
            }
        return ivjMonde;
    }
    
    public static World getMonde() {
        return ivjMonde;
    }
    private void handleException(Throwable throwable) {
        throwable.printStackTrace();
    }
    public static void main(String args[]) {
        try {
            APIMonde unMonde = new APIMonde("Les Pinpins se d\u00E9chainent !!!");
	    
            try {
                Class aCloserClass = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                Class parmTypes[] = {
                    java.awt.Window.class
                };
                Object parms[] = {
                    unMonde
                };
                Constructor aCtor = aCloserClass.getConstructor(parmTypes);
                aCtor.newInstance(parms);
            } catch(Throwable _ex) {
		_ex.printStackTrace();
	    }
            unMonde.setVisible(true);
        } catch(Throwable exception) {
            System.err.println("Exception occurred in main() of java.awt.Frame");
            exception.printStackTrace(System.out);
        }
    }
    
    public static StatsPanel getIvjStatsPane() {
        return ivjStatsPane;
    }
    
    public static PinpinQuiSaitTout getLePinpinQuiSaitTout() {
        return ivjPinpinQuiSaitTout;
    }
    
    public int getNombreDePinpin() {
        return nombreDePinpin;
    }
    
    public int getNombreDOpinion() {
        return nombreDOpinion;
    }
    
    public int getLaNaivete() {
        return laNaivete;
    }

    private int getLaDuree() {
	    return this.laDuree;
    }
    private boolean getSlowPinpins() {
	    return this.slowPinpins;
    }

    public boolean isRandomDistrib() {
        return randomDistrib;
    }
}

