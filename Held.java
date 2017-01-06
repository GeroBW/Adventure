import java.awt.*;
import java.applet.Applet;

public class Held
{
    String name;
    double leben;
    Gegenstand waffe, schild, ruestung;
    Inventar rucksack;
    int xpos, ypos;
    Image bild;
    private Applet applet;

    public Held(String name, Applet applet)
    {
        this.name = name;
        leben = 100;
        waffe = null;
        schild = null;
        ruestung = null;
        rucksack = new Inventar(100);
        xpos=0;
        ypos=0;
        bild = applet.getImage(applet.getCodeBase(),"images/held.png");
    }

    public void einpacken(Gegenstand neu)
    {
        rucksack.einpacken(neu);
    }
    
    public void anlegen(Gegenstand neu) {
        if(neu.istWaffe()) waffe = neu;
        if(neu.istSchild()) schild = neu;
        if(neu.istRuestung()) ruestung = neu;
    }
    
    public void anzeigen(Graphics g, int x, int y) {
        g.draw3DRect(x, y-15, 175, 120, true);
        g.drawLine(x, y+15, x+175, y+15);
        g.drawString(name, x+5, y+5);
        g.setColor(Color.green);
        g.fillRect(x+75, y-3, (int)((leben/100)*80), 10);
        g.setColor(Color.red);
        g.fillRect(x+75+(int)((leben/100)*80), y-3, 80-(int)((leben/100)*80), 10);
        g.setColor(Color.black);
        g.drawString("Leben: "+Math.round(leben), x+5, y+30);
        if(waffe!=null) {
            g.drawString("Waffe: "+waffe.bezeichnung, x+5, y+50);
        }
        else {
            g.drawString("Waffe: keine", x+5, y+50);
        }
        if(schild!=null) {
            g.drawString("Schild: "+schild.bezeichnung, x+5, y+70);
        }
        else {
            g.drawString("Schild: keins", x+5, y+70);
        }
        if(ruestung!=null) {
            g.drawString("Ruestung: "+ruestung.bezeichnung, x+5, y+90);
        }
        else {
            g.drawString("Ruestung: keine", x+5, y+90);
        }
    }
    
    public Gegenstand ablegen(int typ) {
        Gegenstand abgelegt = null;
        if(typ==1) {
            abgelegt= waffe;
            waffe = null;
        }
        if(typ==2) {
            abgelegt= schild;
            schild = null;
        }
        if(typ==3) {
            abgelegt= ruestung;
            ruestung = null;
        }
        return(abgelegt);
    }
    
    public void ausRucksackAnlegen(String bezeichnung) {
        anlegen(rucksack.auspacken(bezeichnung));
    }
    
    public void inRucksackAblegen(int typ) {
       rucksack.einpacken(ablegen(typ));
    }
    
    public void verletzen(int prozent) {
        double x = 1-(prozent*0.01);
        leben=leben*x;
    }
    
    public void heilen() {
        leben = 100;
        if(waffe!=null) {
            waffe.reparieren();
        }
        if(schild!=null) {
            schild.reparieren();
        }
        if(ruestung!=null) {
            ruestung.reparieren();
        }
        rucksack.reparieren();
    }
    
    public void heilen(double he) {
        leben += he;
        if(leben>100)  leben = 100;
    }
    
    public void anzeigen(Graphics g) {
        //g.drawImage(bild,Feld.FELD*(xpos+1),Feld.FELD*(ypos+1),(Feld.FELD),(Feld.FELD),applet);
        g.drawImage(bild,Feld.FELD*xpos,Feld.FELD*ypos-32,Feld.FELD,Feld.FELD+32,applet);
    }
    
    public void platziere(int x, int y) {
        xpos=x;
        ypos=y;
    }
    
    public void bewegen(int dx, int dy) {
        xpos += dx;
        if(xpos<0) xpos=0;
        if(xpos>31) xpos=31;
        ypos += dy;
        if(ypos<0) ypos=0;
        if(ypos>23) ypos=23;
    }
    
    public int gibXpos() {
        return(xpos);
    }
    
    public int gibYpos() {
        return(ypos);
    }
}
