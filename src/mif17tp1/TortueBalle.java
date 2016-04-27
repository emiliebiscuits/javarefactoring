
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Iterator;




public class TortueBalle extends Tortue{

    private TortueAmelioree MaMaitresse;

    public void setMaMaitresse(TortueAmelioree MaMaitresse) {
        this.MaMaitresse = MaMaitresse;
    }

    public TortueAmelioree getMaMaitresse() {
        return MaMaitresse;
    }
    public TortueBalle(TortueAmelioree maitre){
        super();
        MaMaitresse = maitre;
        this.setPosition(maitre.x,maitre.y);
    }
    public void DeplacerTortue(){
        setPosition(this.getMaMaitresse().x, this.getMaMaitresse().y);
    }
    public void ChangerMaitre(TortueAmelioree nouvelle){
        if(nouvelle != null){
            this.MaMaitresse = nouvelle;
            this.x = nouvelle.x;
            this.y = nouvelle.y;
        }
    }
    @Override
    public void drawTurtle (Graphics graph) {
        if (graph==null)
            return;

        graph.setColor(Color.pink);
	graph.drawOval(x,y,20,20);
	graph.fillOval(x,y,20,20);
    }
    
}
