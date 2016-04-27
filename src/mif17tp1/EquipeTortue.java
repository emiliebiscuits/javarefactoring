
import java.awt.Color;
import java.util.ArrayList;


public class EquipeTortue {
    private String NomEquipe;
    private ArrayList<TortueEquipe> Membres;
    private Color ColorEquipe;

    public ArrayList<TortueEquipe> getMembres() {
        return Membres;
    }

    public Color getColorEquipe() {
        return ColorEquipe;
    }
    
    public EquipeTortue(String nom, Color col){
        NomEquipe = nom;
        Membres = new ArrayList<>();
        ColorEquipe = col;
    }
    public void AjouterMembre(TortueEquipe une){
        if(une != null)
        Membres.add(une);
    }
    public void EnleverMembre(TortueEquipe une){
        if(une != null)
        Membres.remove(une);
    }
   
}
