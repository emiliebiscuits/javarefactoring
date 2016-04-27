
import java.awt.Dimension;
import java.util.ArrayList;

public class JeuDeBalle {

    private TortueBalle rond;
    private ArrayList<TortueAmelioree> ListeJoueur;
    public JeuDeBalle() {
        ListeJoueur = new ArrayList<>();
    }
    public void AjouterJoueur(TortueAmelioree une){
        if(une != null)
            ListeJoueur.add(une);
    }
    public void EnleverJoueur(TortueAmelioree une){
        if(une != null)
            ListeJoueur.remove(une);
    }
    
   public void CreerTortue(FeuilleDessin feuille){
       
        ArrayList<String> Creations = new ArrayList<>();
        Creations.add("coco");
        Creations.add("caca");
        Creations.add("cucu");
        Creations.add("cici");
        Creations.add("fofo");
        Creations.add("fafa");
        Creations.add("fufu");
        Creations.add("fifi");
        
        for(String s: Creations){
            CreerUneTortue(s,feuille);
        }
        rond = new TortueBalle(ListeJoueur.get(0));
        feuille.addTortue(rond);
     
   }
   
  private void CreerUneTortue(String nom,FeuilleDessin feuille){
       Dimension size = feuille.getSize();
       TortueAmelioree tortue = new TortueAmelioree(nom);
       tortue.setPosition((int) ((Math.random()*(size.width +1))),(int) ((Math.random()*(size.height +1))));
       feuille.addTortue(tortue);
       AjouterJoueur(tortue);
   }
   
   
   
   public void Jouer(){
       
        for(Tortue joueur: ListeJoueur){
          
                if(joueur == rond.getMaMaitresse()){
                    joueur.DeplacerTortue();
                }
                else{
                    joueur.leverCrayon();
                    joueur.DeplacerTortue();
                    joueur.baisserCrayon();
                }  
   
        }
        rond.DeplacerTortue();
        rond.ChangerMaitre(rond.getMaMaitresse().VerifDis());
        rond.DeplacerTortue();
   }
 
}
