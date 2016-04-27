

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Iterator;


public class TortueEquipe extends TortueAmelioree {

    private EquipeTortue MonEquipe;
    
    public TortueEquipe(String nom, EquipeTortue equipe) {
        super(nom);
        this.MonEquipe = equipe;
        equipe.AjouterMembre(this);
    }
    public int VerifEquipe(TortueEquipe une){
        if(this.MonEquipe == une.MonEquipe){
            return 1;
        }
        else
            return 0;
    }
    public void DeplacementSansBalle(){
        this.leverCrayon();
        this.DeplacerTortue();
        this.baisserCrayon();
    }
    public void DeplacementAvecBalle(TortueBalle balle){
        this.DeplacerTortue();
        balle.setPosition(this.x,this.y);
    }
    public void PasserEquipe(TortueEquipe copine,TortueBalle balle){
        balle.ChangerMaitre(copine);
    }
    public void PriseAdverse(TortueBalle balle){
        balle.ChangerMaitre(this);
    }
    
   
    
   
    public void drawTurtle (Graphics graph) {
		if (graph==null)
			return;
		
		// Dessine les segments
		for(Iterator it = listSegments.iterator();it.hasNext();) {
			Segment seg = (Segment) it.next();
			seg.drawSegment(graph);
		}

		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(x,y);
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=ratioDegRad*(-dir);
		//Demi angle au sommet du triangle
		double alpha=Math.atan( (float)rb / (float)rp );
		//Rayon de la fleche
		double r=Math.sqrt( rp*rp + rb*rb );
		//Sens de la fleche

		//Pointe
		Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
						 (int) Math.round(p.y-r*Math.sin(theta)));
		arrow.addPoint(p2.x,p2.y);
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

		//Base2
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

		arrow.addPoint(p2.x,p2.y);
		graph.setColor(this.MonEquipe.getColorEquipe());
		graph.fillPolygon(arrow);
    }
}
