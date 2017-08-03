package ProjetSMA;
/** Un panel avec une zone d'attraction circulaire */

import Gdima.kernel.communicatingAgent.BasicCommunicatingAgent;
import ProjetSMA.Pinpins.Pinpin;
import ProjetSMA.Pinpins.PinpinPerception;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Vector;
import java.util.Random;
import Gdima.kernel.ProactiveComponents.ProactiveComponentsManager;
import Gdima.kernel.ProactiveComponents.ProactiveComponent;
import java.util.Iterator;

public class World extends Panel {
    private int laTailleDuMonde;
    private int leNombreDePinpin;
    private int leNombreDOpinion;
    private int laNaivete;
    private boolean slowPinpins;
    public Pinpin [][] lesPixels = new Pinpin[300][300];
    private Color [] lesCouleurs = {Color.BLUE,Color.RED,Color.CYAN,Color.MAGENTA,Color.ORANGE,
    Color.PINK,Color.YELLOW,Color.DARK_GRAY,Color.LIGHT_GRAY,Color.GRAY};
    private Random alea;
    ProactiveComponentsManager pam;
    
    public World(){
	
    }
    
    public void start(Vector lesPinpins){
	pam = new ProactiveComponentsManager(lesPinpins);
	pam.startAll();
    }
    
    public void stop(){
	if (pam != null) {
	    pam.killAll();
	    ProactiveComponent temp;
	    for (Iterator it = pam.getProactiveObjects().iterator(); it.hasNext();){
		if ((temp = (ProactiveComponent)it.next()) instanceof BasicCommunicatingAgent){
		    temp.setAlive(false);
		}
	    }
	    System.gc();
	}
    }
    
    
    public void setParam(int uneTaille, int NombreDePinpin, int NombreDOpinion, int uneNaivete, boolean slowPinpins) {
	alea = new Random();
	this.laTailleDuMonde=uneTaille;
	this.leNombreDOpinion=NombreDOpinion;
	this.leNombreDePinpin=NombreDePinpin;
	this.laNaivete=uneNaivete;
	this.slowPinpins = slowPinpins;
    }
    
    // --- Retourne un vecteur compose de tous les elements presents dans l'environnement proche de l'animal
    public synchronized Vector getEnvironnement(Pinpin lePinpin) {
	Vector voisin = new Vector();
	for(int i = lePinpin.getPosX() - lePinpin.getDistancePerception()/2;
                i < lePinpin.getPosX() + lePinpin.getDistancePerception()/2;
                i++){
	    if(i >= 0 && i < 300) {
		for(int j = lePinpin.getPosY() - lePinpin.getDistancePerception()/2;
                        j < lePinpin.getPosY() + lePinpin.getDistancePerception()/2;
                        j++){
		    if(j >= 0 && j < 300 //si on est dans le tableau
			&& this.lesPixels[i][j] != null // et qu'il y a quelqu'un
			&& i != lePinpin.getPosX() && j != lePinpin.getPosY() ){ // et que c'est pas moi!!!
			voisin.add(new PinpinPerception(this.lesPixels[i][j], lePinpin));
		    }
		}
	    }
	}
	return voisin;
    }
    
    public Color getLaCouleur(int i) {
	return lesCouleurs[i];
    }
    public int getNbCouleurs() {
	return lesCouleurs.length;
    }

	public boolean isSlowPinpins() {
		return slowPinpins;
	}
    
}
