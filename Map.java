import java.awt.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.event.*;

public class Map
{
    private int[][] csvLayer, csvLayer2, csvLayer3, collisionMap;
    private int width, height;
    private Held held;
    private NPC npc;
    
    public Feld[][] feld;
    
    // Constructor
    public Map(int[][] csvLayer, int[][] csvLayer2, int[][] csvLayer3, int[][] collisionMap) {
        
        this.csvLayer = csvLayer;
        this.csvLayer2 = csvLayer2;
        this.csvLayer3 = csvLayer3;
        this.collisionMap = collisionMap;
        
        this.width = csvLayer.length;
        this.height = csvLayer[0].length;
        
        this.held = null;
        this.feld = new Feld[width][height];
        
        if(collisionMap != null) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    feld[x][y] = new Feld(x,y, collisionMap[x][y]);
                }
            }
        } else {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    feld[x][y] = new Feld(x,y,0);
                }
            }
        }
        
    }
    public Map(int[][] csvLayer, int[][] csvLayer2, int[][] csvLayer3) {
        this(csvLayer, csvLayer2, csvLayer3, null);
    }
    public Map(int[][] csvLayer, int[][] csvLayer2) {
        this(csvLayer, csvLayer2, null, null);
    }
    public Map(int[][] csvLayer) {
        this(csvLayer, null, null, null);
    }
   
    // other Methods
    public void anzeigen(Graphics g, Image tileset, int scale, int size) {
        _anzeigen(csvLayer, g, tileset, scale, size);
        
        if(csvLayer2 != null) { _anzeigen(csvLayer2, g, tileset, scale, size); }
        if(csvLayer3 != null) { _anzeigen(csvLayer3, g, tileset, scale, size); }
        
        // Aus Dungeon-Klasse
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
             feld[x][y].anzeigen(g);
            }
        }
        
        held.anzeigen(g);
        npc.anzeigen(g);
        held.rucksack.anzeigen(g,600,190);
        held.anzeigen(g,1000,60);
    }
    public void anzeigen(Graphics g, Image tileset) {
        // Default values: scale = 2; size = 16;
        anzeigen(g, tileset, 2, 16);
    }
   
    private void _anzeigen(int[][] layer, Graphics g, Image tileset, int scale, int size) {
        for(int ix = 0; ix < layer.length; ix++) {
            for(int iy = 0; iy < layer[0].length; iy++) {
                g.drawImage(tileset, ix*size*scale, iy*size*scale, ix*size*scale+size*scale, iy*size*scale+size*scale,
                    (int)layer[ix][iy]%19*size, (int)layer[ix][iy]/19*size, (int)layer[ix][iy]%19*size+size,
                    (int)layer[ix][iy]/19*size+size, null);
            }
        }
    }
    
    // Funktionen aus der Dungeon-Klasse
    public void hinterlegeGegenstand(int x, int y, Gegenstand neu) {
        feld[x][y].hinterlegeGegenstand(neu);
    }
    
    public void platziereHeld(int x, int y, Held neu) {
        held = neu;
        held.platziere(x,y);
    }
    
    public void bewegeHeld(int dx, int dy) {
        if(feld[held.gibXpos()+dx][held.gibYpos()+dy].gibTyp()!=1) {
            held.bewegen(dx,dy);
        } else {
            held.verletzen(1);
        }
    }
    
    public void platziereNPC(int x, int y, NPC neu) {
        npc=neu;
        npc.platziere(x,y);
        feld[x][y].setzeTyp(1);
        feld[x][y].setzeNPC(neu);
    }
}
