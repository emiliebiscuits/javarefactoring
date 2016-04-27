
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;



public class JeuEquipe {
    
    private EquipeTortue equipe1;
    private EquipeTortue equipe2;
    private TortueBalle rond;
    private int NbPassage = 0;
    
    public JeuEquipe(String nom1, String nom2, Color col1, Color col2){
        equipe1 = new EquipeTortue(nom1,col1);
        equipe2 = new EquipeTortue(nom2,col2);
    }
    
    public void CreerJoueurs(FeuilleDessin feuille){
        Dimension size = feuille.getSize();
        ArrayList<String> ListeEquipe1 = new ArrayList<>();
        ListeEquipe1.add("dodo");
        ListeEquipe1.add("dada");
        ListeEquipe1.add("dudu");
        ListeEquipe1.add("didi");
        ListeEquipe1.add("jojo");
        ListeEquipe1.add("jaja");
        ListeEquipe1.add("juju");
        ListeEquipe1.add("jiji");
        for(String s: ListeEquipe1 ){
            CreerUnJoueur(feuille,equipe1,s);
        }
        ArrayList<String> ListeEquipe2 = new ArrayList<>();
        ListeEquipe2.add("bobo");
        ListeEquipe2.add("baba");
        ListeEquipe2.add("bubu");
        ListeEquipe2.add("bibi");
        ListeEquipe2.add("gogo");
        ListeEquipe2.add("gaga");
        ListeEquipe2.add("gugu");
        ListeEquipe2.add("gigi");
        for(String s: ListeEquipe2 ){
            CreerUnJoueur(feuille,equipe2,s);
        }
        
        rond = new TortueBalle(equipe2.getMembres().get(0));
        feuille.addTortue(rond);
    }
    
    private void CreerUnJoueur(FeuilleDessin feuille, EquipeTortue equipe, String nom){
        Dimension size = feuille.getSize();
        TortueEquipe tortue = new TortueEquipe(nom,equipe);
        tortue.setPosition((int) ((Math.random()*(size.width +1))),(int)((Math.random()*(size.height +1))));
        feuille.addTortue(tortue);
    }
    
    
    
    
    
    
    
    public int JouerEquipe(){
        TortueEquipe Maitre = null;
        int compteur =0;
        //Deplacements
         for(TortueEquipe t:equipe1.getMembres()){
             if(t != rond.getMaMaitresse()){
                 t.DeplacementSansBalle();
             }
             else
             {
                 Maitre = t;
             }
         }
         for(TortueEquipe t:equipe2.getMembres()){
             if(t != rond.getMaMaitresse()){
                 t.DeplacementSansBalle();
             }
             else
             {
                 Maitre = t;
             }
         }
         Maitre.DeplacementAvecBalle(rond);
         // Verifications
         TortueEquipe une = TortueProche(Maitre);
         if(une != null)
         {
             if(Maitre.VerifEquipe(une)==1 && NbPassage<10 )
             {
                 Maitre.PasserEquipe(une, rond);
                 NbPassage+=1;
                 System.out.println( "Passée" + NbPassage +"fois" );
             }
             else if(Maitre.VerifEquipe(une)!=1 && NbPassage==10)
             {
                 une.PriseAdverse(rond);
                    une.DeplacementAvecBalle(rond);
                    NbPassage = 0;
                    System.out.println("Prise");
                    System.out.println("Maitre actuel: " + une.getNomTortue());
                    compteur +=1;
             }
         }
         Maitre = null;
         return compteur;
    }
    public TortueEquipe TortueProche(TortueEquipe une){
          int dist = 99999;
          int cal;
          TortueEquipe TortueRetour = null;
          for(TortueEquipe t:equipe1.getMembres()){
               cal = une.DistanceTortue(t);
               if(cal<dist && cal<50 && une!=t){
                   dist = cal;
                   TortueRetour = t;
               }
          }
          for(TortueEquipe t:equipe2.getMembres()){
                cal = une.DistanceTortue(t);
               if(cal<dist && cal<50 && une!=t){
                   dist = cal;
                   TortueRetour = t;
               }
          }
        if(TortueRetour != null)
        {System.out.println("Tortue Proche: " + TortueRetour.getNomTortue());}
        return TortueRetour;
    }
    
}
