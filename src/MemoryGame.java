import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MemoryGame implements ActionListener {

        public static boolean DEBUG = true;
        private JFrame mainFrame;                                        // top level window
        private Container mainContentPane;                        // frame that holds card field and turn counter
        private TurnsTakenCounterLabel turnCounterLabel;
        private GameLevel difficulty;

        /**
         * Make a JMenuItem, associate an action command and listener, add to menu
         */
        private static void newMenuItem(String text, JMenu menu, ActionListener listener)
        {
                JMenuItem newItem = new JMenuItem(text);
                newItem.setActionCommand(text);
                newItem.addActionListener(listener);
                menu.add(newItem);
        }

        /**
         * Default constructor loads card images, makes window
         * @throws IOException 
         */
        public MemoryGame () throws IOException
        {

                // Make toplevel window
                this.mainFrame = new JFrame("Memory Poker");
                this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.mainFrame.setSize(800,700);
                this.mainContentPane = this.mainFrame.getContentPane();
                this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

                // Menu bar
                JMenuBar menuBar = new JMenuBar();
                this.mainFrame.setJMenuBar(menuBar);

                // Game menu
                JMenu gameMenu = new JMenu("Memory");
                menuBar.add(gameMenu);
                newMenuItem("Exit", gameMenu, this);

                // Difficulty menu
                JMenu difficultyMenu = new JMenu("New Game");
                menuBar.add(difficultyMenu);
                newMenuItem("Easy Level", difficultyMenu, this);
                newMenuItem("Equal Pair Level", difficultyMenu, this);
                newMenuItem("Same Rank Trio Level", difficultyMenu, this);
                newMenuItem("Two Pair And A Third Wheel Level", difficultyMenu, this);
                newMenuItem("Steel Wheel Level", difficultyMenu, this);
                newMenuItem("Combo Level", difficultyMenu, this);


                // Help menu
                JMenu helpMenu = new JMenu("Help");
                menuBar.add(helpMenu);
                newMenuItem("How To Play", helpMenu, this);
                newMenuItem("About", helpMenu, this);

                //this.leaderBoard = new ScoreLeaderBoard("EasyMode");
        }


        /**
         * Handles menu events.  Necessary for implementing ActionListener.
         *
         * @param e object with information about the event
         */
        public void actionPerformed(ActionEvent e)
        {
                dprintln("actionPerformed " + e.getActionCommand());
                try {
                        if(e.getActionCommand().equals("Easy Level")) newGame("easy");
                        else if(e.getActionCommand().equals("Equal Pair Level")) newGame("medium");
                        else if(e.getActionCommand().equals("Same Rank Trio Level")) newGame("trio");
                        else if(e.getActionCommand().equals("Two Pair And A Third Wheel Level")) newGame("two and a third");
                        else if(e.getActionCommand().equals("Steel Wheel Level")) newGame("steel wheel");
                        else if(e.getActionCommand().equals("Combo Level")) newGame("combo");
                        else if(e.getActionCommand().equals("How To Play")) showInstructions();
                        else if(e.getActionCommand().equals("About")) showAbout();
                        else if(e.getActionCommand().equals("Exit")) System.exit(0);
                } catch (IOException e2) {
                        e2.printStackTrace(); throw new RuntimeException("IO ERROR");
                }
        }


        /**
         * Prints debugging messages to the console
         *
         * @param message the string to print to the console
         */
        static public void dprintln( String message )
        {
                if (DEBUG) System.out.println( message );
        }

        public JPanel showCardDeck()
        {
                // make the panel to hold all of the cards
                JPanel panel = new JPanel(new GridLayout(difficulty.getRowsPerGrid(),difficulty.getCardsPerRow()));

                // this set of cards must have their own manager
                this.difficulty.makeDeck();

                for(int i= 0; i<difficulty.getGrid().size();i++){
                        panel.add(difficulty.getGrid().get(i));
                }
                return panel;
        }

        /**
         * Prepares a new game (first game or non-first game)
         * @throws IOException 
         */
        public void newGame(String difficultyMode) throws IOException
        {
                // reset the turn counter to zero
                this.turnCounterLabel = new TurnsTakenCounterLabel();
        
                // make a new card field with cards, and add it to the window

                if(difficultyMode.equalsIgnoreCase("easy")) {
                        this.difficulty = new EasyLevel(this.turnCounterLabel, this.mainFrame);
                }
                else if(difficultyMode.equalsIgnoreCase("medium")){
                        this.difficulty = new EqualPairLevel(this.turnCounterLabel, this.mainFrame);
                }

                else if(difficultyMode.equalsIgnoreCase("trio")){
                        this.difficulty = new RankTrioLevel(this.turnCounterLabel, this.mainFrame);
                }
                
                else if(difficultyMode.equalsIgnoreCase("two and a third")){
                        this.difficulty = new TwoPairAndAThirdWheelLevel(this.turnCounterLabel, this.mainFrame);
                }
                
                else if(difficultyMode.equalsIgnoreCase("steel wheel")){
                        this.difficulty = new SteelWheelLevel(this.turnCounterLabel, this.mainFrame);
                }
                
                else if(difficultyMode.equalsIgnoreCase("combo")){
                        this.difficulty = new ComboLevel(this.turnCounterLabel, this.mainFrame);
                }

                else {
                        throw new RuntimeException("Illegal Game Level Detected");
                }

                this.turnCounterLabel.reset();


                // clear out the content pane (removes turn counter label and card field)
                this.mainContentPane.removeAll();

                this.mainContentPane.add(showCardDeck());

                // add the turn counter label back in again
                this.mainContentPane.add(this.turnCounterLabel);


                

                // show the window (in case this is the first game)
                this.mainFrame.setVisible(true);
        }

        public boolean gameOver() throws FileNotFoundException, InterruptedException{
                return difficulty.gameOver();
        }

        /**
         * Shows an instructional dialog box to the user
         */
        private void showInstructions()
        {
                dprintln("MemoryGame.showInstructions()");
                final String HOWTOPLAYTEXT = 
                                "How To Play\r\n" +
                                                "\r\n" +
                                                "EQUAL PAIR Level\r\n"+
                                                "The game consists of 8 pairs of cards.  At the start of the game,\r\n"+
                                                "every card is face down.  The object is to find all the pairs and\r\n"+
                                                "turn them face up.\r\n"+
                                                "\r\n"+
                                                "Click on two cards to turn them face up. If the cards are the \r\n"+
                                                "same, then you have discovered a pair.  The pair will remain\r\n"+
                                                "turned up.  If the cards are different, they will flip back\r\n"+
                                                "over automatically after a short delay.  Continue flipping\r\n"+
                                                "cards until you have discovered all of the pairs.  The game\r\n"+
                                                "is won when all cards are face up.\r\n"+
                                                "\r\n"+
                                                "SAME RANK TRIO Level\r\n"+
                                                "The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
                                                "every card is face down.  The object is to find all the trios \r\n"+
                                                "of cards with the same rank and turn them face up.\r\n"+
                                                "\r\n"+
                                                "Click on three cards to turn them face up. If the cards have the \r\n"+
                                                "same rank, then you have discovered a trio.  The trio will remain\r\n"+
                                                "turned up.  If the cards are different, they will flip back\r\n"+
                                                "over automatically after a short delay.  Continue flipping\r\n"+
                                                "cards until you have discovered all of the pairs.  The game\r\n"+
                                                "is won when all cards are face up.\r\n"+
                                                "\r\n"
                                                ;

                JOptionPane.showMessageDialog(this.mainFrame, HOWTOPLAYTEXT
                                , "How To Play", JOptionPane.PLAIN_MESSAGE);
                
                final String HOWTOPLAYTEXT1 = 
//                        "How To Play\r\n" +
                                        "\r\n" +
                                        "TWO PAIR AND A THIRD WHEEL Level\r\n"+
                                        "The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
                                        "every card is face down.  The object is to find every posible 5 card hand which\r\n"+
                                        "contains a rank trio and suit pair or a suit trio and a rank pair\r\n"+
                                        "and turn them face up.\r\n"+
                                        "\r\n"+
                                        "Click on five cards to turn them face up. If the cards are a \r\n"+
                                        "rank trio and suit pair or suit trio and rank pair you score points.\r\n"+
                                        "The 5 cards will remain turned up.  If the cards dont match the object, they \r\n"+
                                        "will flip back over automatically after a short delay. Continue flipping\r\n"+
                                        "cards until you have discovered all of two pair and a third wheel hands. The game\r\n"+
                                        "is won when all two pair and a third wheel hands are face up.\r\n"+
                                        "\r\n"+
                                        "STEEL WHEEL Level\r\n"+
                                        "The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
                                        "every card is face down.  The object is to find 5 cards(A,2,3,4,5) of the \r\n"+
                                        "same suit and turn them face up.\r\n"+
                                        "\r\n"+
                                        "Click on five cards to turn them face up. If the cards have the \r\n"+
                                        "same suit and are the folllowing: A,2,3,4,5;  then you have discovered a steel wheel.\r\n"+
                                        "The steel wheel hand will stay turned up.  If the cards are not a steel wheel, they will\r\n"+
                                        "flip back over automatically after a short delay.  Continue flipping\r\n"+
                                        "cards until you have discovered all of the steel wheel hands.  The game\r\n"+
                                        "is won when all steel wheel hands are face up.\r\n"+
                                        "\r\n"
                                        ;

        JOptionPane.showMessageDialog(this.mainFrame, HOWTOPLAYTEXT1
                        , "How To Play (continuation)", JOptionPane.PLAIN_MESSAGE);
        
        final String HOWTOPLAYTEXT2 = 
//              "How To Play\r\n" +
                              "\r\n" +
                              "Combo Level\r\n"+
                              "The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
                              "every card is face down.  The object is to find every posible 5 card hand which\r\n"+
                              "contains a TWO PAIR AND A THIRD WHEEL, a STEEL WHEEL or a ROYELE FLUSH(A,King,Queen,Jack,10\r\n"+
                              "of same suit) and turn them face up.\r\n"+
                              "\r\n"+
                              "Click on five cards to turn them face up. If the cards are a \r\n"+
                              "TWO PAIR AND A THIRD WHEEL, a STEEL WHEEL or a ROYELE FLUSH you score points.\r\n"+
                              "The 5 cards will remain turned up.  If the cards dont match the object, they \r\n"+
                              "will flip back over automatically after a short delay. Continue flipping\r\n"+
                              "cards until you have discovered all of TWO PAIR AND A THIRD WHEEL, a STEEL WHEEL or a ROYELE FLUSH hands.\r\n"+
                              "The game is won when all TWO PAIR AND A THIRD WHEEL, a STEEL WHEEL or a ROYELE FLUSH hands are face up.\r\n"+
                              "On this Level if you want a better hand you can discard your current hand.\r\n"+
                              "\r\n"+
                              "Each time you flip a hand up, the turn counter will\r\n"+
                              "increase.  Try to win the game in the fewest number of turns!"
                              ;

JOptionPane.showMessageDialog(this.mainFrame, HOWTOPLAYTEXT2
              , "How To Play (continuation)", JOptionPane.PLAIN_MESSAGE);
                
        }

        /**
         * Shows an dialog box with information about the program
         */
        private void showAbout()
        {
                dprintln("MemoryGame.showAbout()");
                final String ABOUTTEXT = "Game Customized at UPRM. Originally written by Mike Leonhard";

                JOptionPane.showMessageDialog(this.mainFrame, ABOUTTEXT
                                , "About Memory Game", JOptionPane.PLAIN_MESSAGE);
        }


}