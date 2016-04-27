
import static java.awt.Color.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;


public class TortueAmelioree extends Tortue {
    private String NomTortue;

   
    private static ArrayList<TortueAmelioree> Copains = new ArrayList<>();

    public static ArrayList<TortueAmelioree> getCopains() {
        return Copains;
    }
     public String getNomTortue() {
        return NomTortue;
    }
    public TortueAmelioree(String nom){
        super();
        NomTortue = nom;
        if(NomTortue == null)
            NomTortue = "Defaut";
        System.out.println(NomTortue + " Créee" );
        AjouterTortue(this);
    }

    public void AjouterTortue(TortueAmelioree Une){
        if(Une != null)
            Copains.add(Une);
    }
    public void EnleverTortue(TortueAmelioree Une){
        if(Une != null)
            Copains.remove(Une);
    }

   
    public void DeplacerTortue(){
       int angle = (int) (Math.random()*361);
       int dist = (int) (Math.random()*50)+50;
       this.gauche(angle);
       this.avancer(dist);
       if(this.x<50 || this.x>800 || this.y<50 || this.y>350)
       {
           this.avancer(-dist);
       }
    }
            
    public int DistanceTortue(Tortue Une){
        if(Une != null){
            double disx = Math.pow(this.x-Une.x,2);
            double disy = Math.pow(this.y-Une.y,2);
            double dist =  Math.sqrt(disx + disy);
            return (int)dist;
        }
        return 0;
    }
    
    public TortueAmelioree VerifDis(){
         int dist;
         System.out.println("=======");
        for(TortueAmelioree une: Copains){
            dist = DistanceTortue(une);
            System.out.println(une.NomTortue + dist);
            if(dist < 50 && (une != this)){
                System.out.println(une.NomTortue + " trop proche, Maitre changé" );
                return une;
            }
        }
        return null;
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
		graph.setColor(magenta);
		graph.fillPolygon(arrow);
    }
}
