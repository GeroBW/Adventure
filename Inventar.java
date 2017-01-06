import java.awt.*;
import java.applet.Applet;
import java.util.ArrayList;

public class Inventar
{
    int maximalgewicht, gewicht;
    ArrayList<Gegenstand> liste;
    
    public Inventar(int max)
    {
        liste = new ArrayList<Gegenstand>();
        maximalgewicht = max;
        gewicht = 0;
    }

    public void einpacken(Gegenstand neu) {
        if((liste.size()<30)&&((gewicht+neu.gewicht)<=maximalgewicht)) {
            liste.add(neu);
            gewicht = gewicht+neu.gewicht;
        }
    }
    
    public Gegenstand auspacken(String bezeichnung) {
        Gegenstand objekt = null;
        for(int i=0; i<liste.size(); i++) {
            if(liste.get(i).bezeichnung==bezeichnung) {
                objekt = liste.get(i);
            }
        }
        liste.remove(objekt);
        gewicht=gewicht-objekt.gewicht;
        return(objekt);
    }
    
    public void anzeigen(Graphics g, int x, int y) {
        for(int i=0; i<liste.size(); i++) {
            g.drawString("Inventar:", x, y);
            g.drawString("Maximalgewicht: " + maximalgewicht, x+70, y);
            g.drawString("Aktuelles Gewicht:" + gewicht, x+200, y);
            if(liste.get(i)!=null) {
                if(i<=5) {
                    liste.get(i).kleinanzeigen(g, x+10+100*i, y+30);
                }
                if((5<i)&&(i<=11)) {
                    liste.get(i).kleinanzeigen(g, x+10+100*(i-6), y+150);
                }
                if((11<i)&&(i<=17)) {
                    liste.get(i).kleinanzeigen(g, x+10+100*(i-12), y+270);
                }
                if((17<i)&&(i<=23)) {
                    liste.get(i).kleinanzeigen(g, x+10+100*(i-16), y+390);
                }
                if((23<i)&&(i<=29)) {
                    liste.get(i).kleinanzeigen(g, x+10+100*(i-21), y+510);
                }
            }
        }
    }
    
    public void beschaedigen(int prozent) {
        for(int i=0; i<liste.size(); i++) {
            if(liste.get(i)!=null) {
                liste.get(i).beschaedigen(prozent);
            }
        }
    }
    
    public void reparieren() {
        for(int i=0; i<liste.size(); i++) {
            if(liste.get(i)!=null) {
                liste.get(i).reparieren();
            }
        }
    }
}
