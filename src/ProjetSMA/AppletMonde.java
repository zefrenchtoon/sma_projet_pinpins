package ProjetSMA;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;

public class AppletMonde extends Applet {
    Font font;
    String str;
    int xPos;
    private APIMonde vuesProiePred;
    
    public AppletMonde() {
	font = new Font("Dialog", 1, 24);
	str = "Les Pinpins parlent";
	xPos = 10;
    }
    public String getAppletInfo() {
	return "Les Pinpins parlent...\n";
    }
    private void handleException(Throwable exception) {
    }
    
    
    // Creation et affiche de APIMonde
    
    public void init() {
	super.init();
	try {
	    setName("PinpinsBavards");
	    setLayout(null);
	    setBackground(SystemColor.textHighlightText);
	    setSize(580, 460);
	    vuesProiePred = new APIMonde();
	    vuesProiePred.setVisible(true);
	} catch (Throwable ivjExc) {
	    handleException(ivjExc);
	}
    }
    public void paint(Graphics g) {
	g.setFont(font);
	g.setColor(Color.black);
	g.drawString(str, xPos, 50);
    }
}
