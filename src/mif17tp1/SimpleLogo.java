
// package logo;


import java.awt.*;
import static java.awt.Color.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;



/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

				J. Ferber - 1999-2001

				Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/


**************************************************************************/


public class SimpleLogo extends JFrame implements ActionListener {
	public static final Dimension VGAP = new Dimension(1,5);
	public static final Dimension HGAP = new Dimension(5,1);

	private FeuilleDessin feuille;
	private Tortue courante;
	private JTextField inputValue;
        private JeuDeBalle jeu = null;
        private JeuEquipe jeu2 = null;
        private JeuThread UnThread = null;
        private int compteur1 = 0;
        private int compteur2 = 0;
	// la procedure principale
	public static void main(String[] args) {
		System.out.println( "Logo demarre!" );
		new SimpleLogo();
	}

	private void quitter() {
		System.exit(0);
	}

	public SimpleLogo() {
		super("un logo tout simple");
		logoInit();
	}

	public void logoInit() {
		getContentPane().setLayout(new BorderLayout(10,10));

		// Boutons
		JToolBar toolBar = new JToolBar();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(toolBar);

		getContentPane().add(buttonPanel,"North");

		addButton(toolBar,"Effacer","Nouveau dessin","/icons/New24.gif");

		toolBar.add(Box.createRigidArea(HGAP));
		inputValue=new JTextField("45",5);
		toolBar.add(inputValue);
		addButton(toolBar, "Avancer", "Avancer 50", null);
		addButton(toolBar, "Droite", "Droite 45", null);
		addButton(toolBar, "Gauche", "Gauche 45", null);
		addButton(toolBar, "Lever", "Lever Crayon", null);
		addButton(toolBar, "Baisser", "Baisser Crayon", null);

		String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
								 "vert", "gris clair", "magenta", "orange",
								 "gris", "rose", "jaune"};

		// Create the combo box
		toolBar.add(Box.createRigidArea(HGAP));
		JLabel colorLabel = new JLabel("   Couleur: ");
		toolBar.add(colorLabel);
		JComboBox colorList = new JComboBox(colorStrings);
		toolBar.add(colorList);

		colorList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int n = cb.getSelectedIndex();
				courante.setColor(n);
			}
		});


		// Menus
		JMenuBar menubar=new JMenuBar();
		setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
		menubar.add(menuFile);

		addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
		menubar.add(menuCommandes);
		addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
		addMenuItem(menuCommandes, "Droite", "Droite", -1);
		addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
		addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
		addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
		menubar.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// les boutons du bas
		JPanel p2 = new JPanel(new GridLayout());
		JButton b20 = new JButton("Proc1");
		p2.add(b20);
		b20.addActionListener(this);
		JButton b21 = new JButton("Proc2");
		p2.add(b21);
		b21.addActionListener(this);
		JButton b22 = new JButton("Proc3");
		p2.add(b22);
		b22.addActionListener(this);
                JButton b23 = new JButton("Proc4");
		p2.add(b23);
		b23.addActionListener(this);
                JButton b24 = new JButton("Maison");
		p2.add(b24);
		b24.addActionListener(this);
                JButton b25 = new JButton("Etoile");
		p2.add(b25);
		b25.addActionListener(this);
                JButton b26 = new JButton("JeuDeBalle");
		p2.add(b26);
		b26.addActionListener(this);
                JButton b27 = new JButton("JeuEquipe");
		p2.add(b27);
		b27.addActionListener(this);

		getContentPane().add(p2,"South");

		feuille = new FeuilleDessin(); //500, 400);
		feuille.setBackground(Color.white);
		feuille.setSize(new Dimension(600,400));
		feuille.setPreferredSize(new Dimension(600,400));
			
		getContentPane().add(feuille,"Center");
		
		// Creation de la tortue
		Tortue tortue = new Tortue();
		
		// Deplacement de la tortue au centre de la feuille
		tortue.setPosition(436, 200); 		
                
		courante = tortue;
		feuille.addTortue(tortue);
                
		pack();
		setVisible(true);
	}

	public String getInputValue(){
		String s = inputValue.getText();
		return(s);
	}

	/** la gestion des actions des boutons */
	public void actionPerformed(ActionEvent e)
	{
		String c = e.getActionCommand();

		// actions des boutons du haut
		if (c.equals("Avancer")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.avancer(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
			
		}
		else if (c.equals("Droite")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Gauche")) {
			try {
				int v = Integer.parseInt(inputValue.getText());
				courante.gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
			}
		}
		else if (c.equals("Lever")) 
			courante.leverCrayon();
		else if (c.equals("Baisser"))
			courante.baisserCrayon();
		// actions des boutons du bas
		else if (c.equals("Proc1"))
			proc1();
		else if (c.equals("Proc2"))
			proc2();
		else if (c.equals("Proc3"))
			proc3();
                else if (c.equals("Proc4"))
			proc4();
                else if (c.equals("Maison"))
			procmaison();
                else if (c.equals("Etoile"))
			procetoile();
                else if (c.equals("JeuDeBalle"))
			jeudeballe();
                else if (c.equals("JeuEquipe"))
			jeuequipe();
		else if (c.equals("Effacer"))
			effacer();
		else if (c.equals("Quitter"))
			quitter();

		feuille.repaint();
	}

  	/** les procedures Logo qui combine plusieurs commandes..*/
	public void proc1() {
		courante.carre();
	}

	public void proc2() {
		courante.poly(60,8);
	}

	public void proc3() {
		courante.spiral(50,40,6);
	}
        
        public void proc4(){
                courante.triangle();
        }
        public void jeudeballe(){
            if(compteur1==0)
            {
                jeu = new JeuDeBalle();
                jeu.CreerTortue(feuille);
                compteur1=1;
            }
            else{
                      jeu.Jouer();
                }      
  
            

        }
        public void jeuequipe(){
             if(compteur2==0){
                jeu2 = new JeuEquipe("e1","e2",cyan,orange);
                jeu2.CreerJoueurs(feuille);
                compteur2=1;
             }
             else{
                  UnThread = new JeuThread(jeu2,feuille);
                  UnThread.start();
             }      
        }
        public void procmaison(){
                courante.maison();
        }
        public void procetoile(){
                courante.etoile(6,100);
        }
	// efface tout et reinitialise la feuille
	public void effacer() {
                if(UnThread != null){
                    UnThread.interrupt();
                }
                feuille.reset();//supression de toutes 
                feuille.repaint();
                //Vider la liste static
                TortueAmelioree.getCopains().clear();
                //RE-creer la courante
                Tortue tortue = new Tortue();
		courante = tortue;
		feuille.addTortue(tortue);
		// Replace la tortue au centre
		Dimension size = feuille.getSize();
		courante.setPosition(size.width/2, size.height/2);
                compteur1=0;
                compteur2=0;
                
	}

	//utilitaires pour installer des boutons et des menus
	public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
		JButton b;
		if ((imageName == null) || (imageName.equals(""))) {
			b = (JButton)p.add(new JButton(name));
		}
		else {
			java.net.URL u = this.getClass().getResource(imageName);
			if (u != null) {
				ImageIcon im = new ImageIcon (u);
				b = (JButton) p.add(new JButton(im));
			}
			else
				b = (JButton) p.add(new JButton(name));
			b.setActionCommand(name);
		}

		b.setToolTipText(tooltiptext);
		b.setBorder(BorderFactory.createRaisedBevelBorder());
		b.setMargin(new Insets(0,0,0,0));
		b.addActionListener(this);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		menuItem.addActionListener(this);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
	}
}
