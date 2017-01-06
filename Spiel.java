import java.awt.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.event.*;
/* 
 * http://www.u-helmich.de/inf/BlueJ/adventure/folge01a.html 
 */ 
public class Spiel extends JApplet implements ActionListener
{
   Gegenstand schwert, schwert2, schwert3, schwert4, schild, schild2, schild3, schild4, ruestung, ruestung2, klheiltrank;
   //Dungeon spiel;
   Held gandalf;
   NPC haendler;
   Button btn_up, btn_down, btn_left, btn_right, btn_aufheben, btn_inventar, btn_benutzen;
   int position, richtung, level;
   boolean inventarOffen, handel;
   
   private CSV csv;
   private Map map;

   public void init()
   {
      csv = new CSV();
      map = new Map(csv.readFile("csv/map_Kachelebene1.csv", 20, 20), csv.readFile("csv/map_Kachelebene2.csv", 20, 20), csv.readFile("csv/map_Kachelebene3.csv", 20, 20), csv.readFile("csv/map_Kollisionsebene.csv", 20, 20));
      
      btn_up = new Button("A");
      btn_down = new Button("V");
      btn_left = new Button("<");
      btn_right = new Button(">");
      btn_aufheben = new Button("aufheben");
      btn_inventar = new Button("Inventar");
      btn_benutzen = new Button("benutzen");
      setLayout(null);
      btn_up.setBounds(660,40,40,40);
      btn_down.setBounds(660,120,40,40);
      btn_left.setBounds(620,80,40,40);
      btn_right.setBounds(700,80,40,40);
      btn_aufheben.setBounds(800,80,80,40);
      btn_inventar.setBounds(900,80,60,40);
      btn_benutzen.setBounds(900,130,60,40);
      add(btn_up);
      add(btn_down);
      add(btn_left);
      add(btn_right);
      add(btn_aufheben);
      add(btn_inventar);
      add(btn_benutzen);
      btn_up.addActionListener(this);
      btn_down.addActionListener(this);
      btn_left.addActionListener(this);
      btn_right.addActionListener(this);
      btn_aufheben.addActionListener(this);
      btn_inventar.addActionListener(this);
      btn_benutzen.addActionListener(this);
      inventarOffen = false;
      
      inventarOffen = false;
      position = 0;
      level = 1;
      handel = false;
      
      schwert = new Gegenstand("Silbertod", 20, 0, 100, 5, Gegenstand.WAFFE, "images/schwert.png", this);
      schwert2 = new Gegenstand("Orkrist", 15, 0, 80, 7, 1, "images/schwert.png", this);
      schwert3 = new Gegenstand("Anduril", 10, 0, 80, 7, 1, "images/schwert.png", this);
      schwert4 = new Gegenstand("Glamdring", 22, 0, 100, 7, 1, "images/schwert.png", this);
      schild = new Gegenstand("Löwenherz", 0, 20, 100, 10, 2, "images/schild.png", this);
      schild2 = new Gegenstand("Holzschild", 0, 10, 50, 12, 2, "images/schild.png", this);
      schild3 = new Gegenstand("Fluchtfeld", 0, 10, 50, 14, 2, "images/schild.png", this);
      schild4 = new Gegenstand("Orkwehr", 0, 15, 70, 10, 2, "images/schild.png", this);
      ruestung = new Gegenstand("Schattenwehr", 0, 40, 250, 17, 3, "images/ruestung.png", this);
      ruestung2 = new Gegenstand("Panzer", 0, 24, 80, 13, 3, "images/ruestung.png", this);
      klheiltrank = new Gegenstand("Kleiner Heiltrank", 15, 50, 1, 4, "images/heiltrank.jpg", this);
      gandalf = new Held("Gandalf", this);
      haendler = new NPC("Hugo", this);
      
      gandalf.anlegen(schwert);
      gandalf.anlegen(schild4);
      gandalf.anlegen(ruestung2);
      gandalf.einpacken(schwert2);
      gandalf.einpacken(schild4);
      gandalf.einpacken(schwert3);
      gandalf.einpacken(ruestung);
      
      schild.beschaedigen(60);
      gandalf.verletzen(15);
      gandalf.heilen();
      
      map.hinterlegeGegenstand(7,3,schwert);
      map.hinterlegeGegenstand(3,15,schwert4);
      map.hinterlegeGegenstand(12,4,schild);
      map.hinterlegeGegenstand(15,9,schild3);
      map.hinterlegeGegenstand(10,10,klheiltrank);
      map.platziereHeld(9, 10, gandalf);
      map.platziereNPC(7, 9, haendler);
   }

   public void paint(Graphics g)
   {
      g.setColor( getBackground() );
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.black);
      g.drawString("Die Geheimnisse von Durimar", 10, 20);
      
      map.anzeigen(g, this.getImage(this.getCodeBase(),"images/tileset.png"));
      
      if(level==1) {
           g.drawString("Die Geheimnisse von Durimar", 10, 20);
           g.drawString("Gandalf, ein junger Absolvent der Diplom-Heldenschule, hat von den Geheimnissen gehört, die in den Tiefen", 10, 500);
           g.drawString("Durimars verborgen sein sollen. Neben verborgenen Schätzen und unermesslichen Reichtümern erwarten ihn", 10, 515);
           g.drawString("jedoch auch Unglück und Gefahr. Kannst du es schaffen, den mutigen Gandalf auf seinem Weg zu begleiten,", 10, 530);
           g.drawString("seinen dunkelsten Ängsten entgegenzutreten und furchtbare Ungetüme zu besiegen?", 10, 545);
      }
      
      if(inventarOffen==true) {
          g.setColor(Color.blue);
          if(position<=5){
              g.draw3DRect(600+100*(position), 190+30-15, 80, 100, true);
            }
          if((5<position)&&(position<=11)) {
              g.draw3DRect(600+100*(position-6), 190+150-15, 80, 100, true);
            }
          if((11<position)&&(position<=17)) {
              g.draw3DRect(600+100*(position-12), 190+270-15, 80, 100, true);
            }
          if((17<position)&&(position<=23)) {
              g.draw3DRect(600+100*(position-16), 190+390-15, 80, 100, true);
            }
          if((23<position)&&(position<=29)) {
              g.draw3DRect(600+100*(position-21), 190+510-15, 80, 100, true);
            }
          g.setColor(Color.black);
        }
   }
   
   public void actionPerformed(ActionEvent event) {
       if(event.getSource()==btn_up) {
           if(inventarOffen==true) {
               if(position>=6) {
                   if(gandalf.rucksack.liste.get(position-6)!=null) {
                       position= position-6;
                    }
                }
            }
            else {
                richtung = 1;
                map.bewegeHeld(0,-1);
            }
        }
       if(event.getSource()==btn_down) {
           if(inventarOffen==true) {
               if(position<=23) {
                   if(gandalf.rucksack.liste.get(position+6)!=null) {
                       position= position+6;
                    }
                }
            }
            else {
                richtung = 2;
                map.bewegeHeld(0,1);
            }
        }
       if(event.getSource()==btn_left) {
           if(inventarOffen==true) {
               if(position>0) {
                   if(gandalf.rucksack.liste.get(position-1)!=null) {
                       position= position-1;
                    }
                }
            }
            else {
                richtung = 4;
                map.bewegeHeld(-1,0);
            }
        }
       if(event.getSource()==btn_right) {
           if(inventarOffen==true) {
               if(position<29) {
                   if(gandalf.rucksack.liste.get(position+1)!=null) {
                       position= position+1;
                    }
                }
            }
            else {
                richtung = 3;
                map.bewegeHeld(1,0);
            }
        }
       if(event.getSource()==btn_aufheben)
       gandalf.rucksack.einpacken(map.feld[gandalf.gibXpos()][gandalf.gibYpos()].entferneGegenstand());
       if(event.getSource()==btn_inventar) {
           if(inventarOffen==false) {
               inventarOffen=true;
               position=0;
            }
            else if(inventarOffen==true) {
                inventarOffen=false;
            }
        }
       if(event.getSource()==btn_benutzen) {
           if(inventarOffen == true) {
               if(gandalf.rucksack.liste.get(position).typ == 4) {
                   gandalf.heilen(gandalf.rucksack.liste.get(position).heilwert);
                   gandalf.rucksack.auspacken(gandalf.rucksack.liste.get(position).gibName());
                }
            }
           else {
               if(richtung == 1) {
                   if(map.feld[gandalf.gibXpos()][gandalf.gibYpos()-1].gibNPC().handel()==1) {
                       handel = true;
                    }
                }
               else if(richtung == 2) {
                   
                }
               else if(richtung == 3) {
                   
                }
               else if(richtung == 4) {
                   
                }
            }
        }
       repaint();
    }
}