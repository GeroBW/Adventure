import java.awt.*;

public class Feld
{
    private int x,y,typ;
    public static final int FELD = 32;
    //0=leer, 1=wand, 2=tür zu, 3=tür auf
    Gegenstand ding;
    NPC npc;
    public Feld(int x, int y, int typ)
    {
        this.x = x;
        this.y = y;
        this.typ = typ;
        ding=null;
        npc=null;
    }
    
    public void anzeigen(Graphics g) {
        if(ding!=null) {
            ding.minianzeigen(g,x*FELD, y*FELD);
        }
    }
    
    public void setzeTyp(int typ) {
        this.typ=typ;
    }
    
    public int gibTyp() {
        return typ;
    }
    
    public void hinterlegeGegenstand(Gegenstand neu) {
        ding = neu;
    }
    
    public Gegenstand entferneGegenstand() {
        Gegenstand weg = ding;
        ding = null;
        return weg;
    }
    
    public void setzeNPC(NPC neu) {
        npc=neu;
    }
    
    public NPC gibNPC() {
        return(npc);
    }
}
