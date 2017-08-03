package ProjetSMA.Pinpins;
import Gdima.basicagentcomponents.AgentIdentifier;
import Gdima.basiccommunicationcomponents.Message;
import Gdima.kernel.communicatingAgent.BasicCommunicatingAgent;
import ProjetSMA.*;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.ClassCastException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Pinpin extends BasicCommunicatingAgent {
    protected int posX;
    protected int posY;
    protected int maDirectionX = 0;
    protected int maDirectionY = 0;
    private static final int maDistanceDePerception = 10;
    private static int maNaivete = 0;
    protected static World leMonde= ProjetSMA.APIMonde.getMonde();
    protected static Random alea = new Random();
    private static PinpinQuiSaitTout lePinpinQuiSaitTout = ProjetSMA.APIMonde.getLePinpinQuiSaitTout();
    private int monOpinion = 0;
    protected Color maCouleur;
    protected Vector monEnvironnement = null;
    protected boolean slowPinpin = false;
    protected boolean modeSlowPinpin = ProjetSMA.APIMonde.getMonde().isSlowPinpins();
    
    public Pinpin(int x, int y, int uneOpinion, int uneNaivete) throws ClassCastException {
	//super(x,y);
	posX = x;
	posY = y;
	this.addAquaintance(lePinpinQuiSaitTout);
	this.setMaNaivete(uneNaivete);
	this.monOpinion = uneOpinion;
	this.maCouleur = this.leMonde.getLaCouleur(this.monOpinion);
	Message m = new Message("declarer",monOpinion);
        this.sendMessage(lePinpinQuiSaitTout.getId(), m);
    }
    
    //-------------- Acesseurs----------
    public void setPosX(int newValue) {
	this.posX = newValue;
    }
    public void setPosY(int newValue) {
	this.posY = newValue;
    }
    
    public int getPosX(){
	return this.posX;
    }
    
    public int getPosY(){
	return this.posY;
    }
    
    public int getDistancePerception(){
	return this.maDistanceDePerception;
    }
    
    public int getMonOpinion() {
	return this.monOpinion;
    }
    
    public void setMonOpinion(Integer monOpinion) {
	int monAncienneOpinion = this.monOpinion;
	this.monOpinion = monOpinion;
	this.maCouleur = this.leMonde.getLaCouleur(monOpinion);
	Message m = new Message("cafter", monAncienneOpinion, monOpinion);
	this.sendMessage(lePinpinQuiSaitTout.getId(), m);
        if (this.modeSlowPinpin) {
            this.slowPinpin = true;
        }
    }
    
    public static int getMaNaivete() {
	return maNaivete;
    }
    
    public static void setMaNaivete(int aMaNaivete) {
	maNaivete = aMaNaivete;
    }
    
    //-------------- Fin Accesseurs ----------
    
    protected int getRandomDirectionX() {
        if(this.slowPinpin){
            return (int)(-1.+(alea.nextInt(3)));
        }else{
            return 2*(int)(-1.+(alea.nextInt(3)));
        }
    }
    
    protected int getRandomDirectionY() {
	if(this.slowPinpin){
            return (int)(-1.+(alea.nextInt(3)));
        }else{
            return 2*(int)(-1.+(alea.nextInt(3)));
        }
    }
    
    protected void erase() {
	Graphics g = leMonde.getGraphics();
	g.setColor(Color.WHITE);
	g.drawRect(this.getPosX()-1,this.getPosY()-1,3,3);
		/*g.drawRect(this.getPosX() - this.getDistancePerception()/2,
			   this.getPosY() - this.getDistancePerception()/2,
			   this.getDistancePerception(),
			   this.getDistancePerception());*/
	
    }
    protected void paint() {
	Graphics g = this.leMonde.getGraphics();
	g.setColor(maCouleur);
	g.drawRect(this.getPosX()-1,this.getPosY()-1,3,3);
		/*g.setColor(Color.GREEN);
		g.drawRect(this.getPosX() - this.getDistancePerception()/2,
			   this.getPosY() - this.getDistancePerception()/2,
			   this.getDistancePerception(),
			   this.getDistancePerception());*/
    }
    
    protected void gambader() {
	int dx = this.getRandomDirectionX();
	int dy = this.getRandomDirectionY();
	
	if ((this.getPosX()+dx >= 300) || (this.getPosX()+dx < 0))
	    dx = 0;
	if ((this.getPosY()+dy >= 300) || (this.getPosY()+dy < 0))
	    dy = 0;
	
	if (this.leMonde.lesPixels[this.getPosX()+dx][this.getPosY()+dy] == null) {
	    this.leMonde.lesPixels[this.getPosX()][this.getPosY()] = null;
	    this.setPosX(this.getPosX()+dx);
	    this.setPosY(this.getPosY()+dy);
	    this.leMonde.lesPixels[this.getPosX()][this.getPosY()] = this;
	}
    }
    
    public void step() {
	this.erase();
	this.readAllMessages();
	this.monEnvironnement = this.leMonde.getEnvironnement(this);
	if(this.monEnvironnement.isEmpty())
	    this.gambader();
	else
	    this.aviser();
	this.paint();
	this.wwait(30);
    }
    
    private void aviser() {
	PinpinPerception perception = new PinpinPerception((Pinpin)null,Double.POSITIVE_INFINITY,0,0);
	for ( Iterator it = this.monEnvironnement.iterator();it.hasNext();){
	    PinpinPerception pinpin = (PinpinPerception)it.next();
	    if( perception.getLaDistance() > pinpin.getLaDistance()) {
		if ((pinpin.getLePinpin().getMonOpinion() <= this.getMonOpinion()+this.maNaivete)
		 && (pinpin.getLePinpin().getMonOpinion() >= this.getMonOpinion()-this.maNaivete)
                 && (pinpin.getLePinpin().getMonOpinion() != this.getMonOpinion()))
		    perception = pinpin;
	    }
	}
	if(perception.getLePinpin() != null) {
	    Message m = new Message("setMonOpinion", this.getMonOpinion());
            this.sendMessage(perception.getLePinpin().getId(), m);
	}
	else {
	    this.gambader();
	}
    }
}
