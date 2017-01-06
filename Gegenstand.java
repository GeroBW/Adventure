import java.awt.*;
import java.applet.Applet;

public class Gegenstand
{
   String bezeichnung;
   double angriffswert, verteidigungswert, goldwert, zustand, heilwert;
   int typ, gewicht;
   private Applet applet;
   Image bild;
   public static final int WAFFE = 1;
   public static final int SCHILD = 2;
   public static final int RUESTUNG = 3;

   public Gegenstand(String bez, double ang, double ver, double gol, int gew, int typ, String dateiname, Applet applet)
   {
      bezeichnung = bez;
      angriffswert = ang;
      verteidigungswert = ver;
      goldwert = gol;
      gewicht = gew;
      zustand = 100;
      this.typ=typ;
      this.applet=applet;
      bild = applet.getImage(applet.getCodeBase(), dateiname);
   }

   public Gegenstand(String bez, double heilen, double gol, int gew, int typ, String dateiname, Applet applet)
   {
      bezeichnung = bez;
      heilwert = heilen;
      goldwert = gol;
      gewicht = gew;
      zustand = 100;
      this.typ=typ;
      this.applet=applet;
      bild = applet.getImage(applet.getCodeBase(), dateiname);
   }
   
   public void anzeigen(Graphics g, int x, int y)
   {
      g.draw3DRect(x-10, y-15, 160, 190, true);
      g.setColor(Color.red);
      g.fillRect(x-10, y+20, 40, 20);
      g.fillRect(x+(int)((zustand/100)*140), y+70, 140-(int)((zustand/100)*140), 10);
      g.setColor(Color.green);
      g.fillRect(x+30, y+20, 40, 20);
      g.fillRect(x, y+70, (int)((zustand/100)*140), 10);
      g.setColor(Color.yellow);
      g.fillRect(x+70, y+20, 40, 20);
      g.setColor(Color.blue);
      g.fillRect(x+110, y+20, 40, 20);
      g.setColor(Color.black);
      g.drawLine(x-10, y+60, x+150, y+60);
      g.drawString(bezeichnung + " (" + gibTypname() + ")",x-5,y+10);
      g.drawString(""+Math.round(angriffswert), x-5, y+35);
      g.drawString(""+Math.round(verteidigungswert), x+35, y+35);
      g.drawString(""+Math.round(goldwert), x+75, y+35);
      g.drawString(""+Math.round(zustand), x+115, y+35);
      g.setFont(g.getFont().deriveFont(10.0f));
      g.drawString("Angr.", x-5, y+55);
      g.drawString("Vert.", x+35, y+55);
      g.drawString("Gold.", x+75, y+55);
      g.drawString("Zust.", x+115, y+55);
      g.drawRect(x-10, y+20, 160, 40);
      g.drawRect(x+30, y+20, 80, 40);
      g.drawLine(x+70, y+20, x+70, y+60);
      g.drawLine(x-10, y+40, x+150, y+40);
      g.setFont(g.getFont().deriveFont(12.0f));
      g.drawImage(bild,x+29, y+90, 80, 80, applet);
   }
   
   public void kleinanzeigen(Graphics g, int x, int y) {
      g.draw3DRect(x-10, y-15, 80, 100, true);
      g.drawString(bezeichnung, x-5, y+10);
      g.drawImage(bild,x+20, y+30, 40, 40, applet);
      g.setColor(Color.green);
      g.fillRect(x, y+20, 10, 60);
      g.setColor(Color.red);
      g.fillRect(x, y+20, 10, 60-(int)((zustand/100)*60));
      g.setColor(Color.black);
   }
    
   public void minianzeigen(Graphics g, int x, int y) {
       g.drawImage(bild,x,y,Feld.FELD,Feld.FELD,applet);
   }
    
   public void beschaedigen(int prozent) {
       double x = 1-(prozent*0.01);
       angriffswert = angriffswert*x;
       verteidigungswert = verteidigungswert*x;
       goldwert = goldwert*x;
       zustand = zustand*x;
   }
    
   public int reparaturKosten() {
       double x = 100/zustand;
       double ursprungswert = goldwert*x;
       double kosten = ursprungswert - goldwert;
       return((int)Math.round(kosten));
   }
    
   public void reparieren() {
       double x = 100/zustand;
       angriffswert = angriffswert*x;
       verteidigungswert = verteidigungswert*x;
       goldwert = goldwert*x;
       zustand = zustand*x;
   }
    
   public int gibTyp() {
       return(typ);
   }
   
   public String gibTypname() {
       String typname = null;
       if(istWaffe()) {
           typname = "Waffe";
        }
       if(istSchild()) {
           typname = "Schild";
        }
       if(istRuestung()) {
           typname = "Ruestung";
        }
       return(typname);
    }
    
   public boolean istWaffe() {
       return(gibTyp()==WAFFE);
    }
    
   public boolean istSchild() {
       return(gibTyp()==SCHILD);
    }
    
   public boolean istRuestung() {
       return(gibTyp()==RUESTUNG);
    }
    
   public String gibName() {
       return(this.bezeichnung);
    }
    
   public double gibHeilwert() {
       return(this.heilwert);
    }
   
}