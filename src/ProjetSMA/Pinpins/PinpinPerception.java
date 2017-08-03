package ProjetSMA.Pinpins;

public class PinpinPerception {
    
    private Pinpin lePinpin;
    double laDistance;
    private int laDirectionX;
    private int laDirectionY;
    
    public PinpinPerception(Pinpin unPinpinVu, Pinpin voyant) {
	lePinpin = unPinpinVu;
	laDirectionX = voyant.getPosX() - unPinpinVu.getPosX();
	laDirectionY = voyant.getPosY() - unPinpinVu.getPosY();
	laDistance = Math.hypot(this.laDirectionX, this.laDirectionY);
    }
    
    public PinpinPerception(Pinpin unAnimal, double uneDistance, int uneDirectionX,int uneDirectionY) {
	lePinpin = unAnimal;
	laDistance = uneDistance;
	laDirectionX = uneDirectionX;
	laDirectionY = uneDirectionY;
    }
    
    public int getLaDirectionX() {
	return laDirectionX;
    }
    
    public int getLaDirectionY() {
	return laDirectionY;
    }
    
    public double getLaDistance() {
	return laDistance;
    }
    
    public Pinpin getLePinpin() {
	return lePinpin;
    }
    
}

