
import java.util.logging.Level;
import java.util.logging.Logger;


public class JeuThread extends Thread {
    private JeuEquipe jeu;
    private FeuilleDessin feuille;
    
    public JeuThread(JeuEquipe j,FeuilleDessin f ){
        jeu = j;
        feuille =f;
    }
    
    
     public void run() 
     {
        int fin = 0;
        while(true) 
        {
            try {
                    if(jeu.JouerEquipe()==1){
                        fin += 1;
                    }
                    feuille.repaint();
                    Thread.sleep(500);
                    
                    if(fin == 2){
                        throw  new InterruptedException("Passage 10 fois de chaque équipe fini :) ");
                    }
                   
            } catch (InterruptedException ex) {
                break;
            } 
        }
     }    
}
