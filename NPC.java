import java.awt.*;
import java.applet.Applet;
import javax.swing.JOptionPane;

public class NPC
{
    String name;
    double leben;
    Inventar inventar;
    int xpos, ypos, typ;
    //1=händler, 2=schmied, 3=heiler
    Image bild;
    private Applet applet;
    Graphics g;
    
    public NPC(String name, Applet applet)
    {
        leben = 100;
        this.name = name;
        inventar = new Inventar(5000);
        xpos=0;
        ypos=0;
        bild = applet.getImage(applet.getCodeBase(),"images/held.png");
    }

    public int handel()
    {
        JOptionPane.showMessageDialog(null,
            "Hallo, mein Name ist Gertrude. Ich bin die Händlerin hier im Dorf.",
            "Gertrude",
            JOptionPane.PLAIN_MESSAGE);
        Object[] options = {"Ja, gerne.",
                    "Nein danke."};
        int n = JOptionPane.showOptionDialog(null,
            "Möchtest du handeln?",
            "Gertrude",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        
        return(n);
    }
    
    public void schmieden() {
         JOptionPane.showMessageDialog(null,
            "Moin, moin. Ich bin Harold, der beste - und einzige - Schmied weit und breit.",
            "Harold",
            JOptionPane.PLAIN_MESSAGE);
        Object[] options = {"Ja, gerne.",
                    "Nein danke."};
        int n = JOptionPane.showOptionDialog(null,
            "Möchtest du deine Waffen reparieren?",
            "Harold",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
    }
    
    public void heilen() {
         JOptionPane.showMessageDialog(null,
            "Ich bin Sarah die Weise, man nennt mich auch Sarah. Meine Kunst ist die des Heilens.",
            "Sarah",
            JOptionPane.PLAIN_MESSAGE);
        Object[] options = {"Ja, gerne.",
                    "Nein danke."};
        int n = JOptionPane.showOptionDialog(null,
            "Wünschst du, für eine bescheidene Spende von 100 Gold von mir geheilt zu werden?",
            "Sarah",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
    }
    
    public void platziere(int x, int y) {
        xpos=x;
        ypos=y;
    }
    
    public void anzeigen(Graphics g) {
        //g.drawImage(bild,Feld.FELD*(xpos+1),Feld.FELD*(ypos+1),(Feld.FELD),(Feld.FELD),applet);
        g.drawImage(bild,Feld.FELD*xpos,Feld.FELD*ypos-32,Feld.FELD,Feld.FELD+32,applet);
    }
}
