import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ComboLevel extends SteelWheelLevel {
        private int localScore;

        // Steel Wheel LEVEL: The goal is to find five cards (A-2-3-4-5) with the same distinct suit.

        protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
                super(validTurnTime, mainFrame);
                super.turnsTakenCounter.setDifficultyModeLabel("Combo");
                cardsToTurnUp = 5;
                cardsPerRow = 10;
                rowsPerGrid = 5;
        }

        @Override
        protected boolean addToTurnedCardsBuffer(Card card) {
                // add the card to the list
                this.turnedCardsBuffer.add(card);
                GameOver1.add(card);
                if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
                {
                        // We are uncovering the last card in this turn
                        // Record the player's turn
                        this.turnsTakenCounter.increment();

                        // get the other card (which was already turned up)

                        Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
                        Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
                        Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
                        Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);

                        //Check if all the cards have the same suit
                        if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) && (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit()))) 

                        {
                                int x1 = toInt(otherCard1);
                                int x2 = toInt(otherCard2);
                                int x3 = toInt(otherCard3);
                                int x4 = toInt(otherCard4);
                                int x5 = toInt(card);

                                //If all the cards have the same suit, check if cards are A-K-Q-J-10 in any order
                                if ((x1 >= 10) && (x2 >= 10) && (x3 >= 10) && (x4 >= 10) && (x5 >= 10))

                                {        
                                        int x = JOptionPane.showConfirmDialog(null, "Your hand score is: " + scoreL5() + ". Take it?");

                                        if (x==0)
                                        {        
                                                score = score+localScore;
                                                this.turnsTakenCounter.getScore(score);
                                                this.turnedCardsBuffer.clear();
                                                localScore=0;
                                                if(GameOver1.size()==(cardsPerRow*rowsPerGrid)-cardsToTurnUp)
                                                {
                                                	
                                                	GameOver.GameEnded(getMode());
                                                }
                                        }
                                        else
                                        {
                                                localScore=0;
                                                this.turnDownTimer.start();
                                                GameOver1.remove(card);
                        						GameOver1.remove(otherCard1);
                        						GameOver1.remove(otherCard2);
                        						GameOver1.remove(otherCard3);
                        						GameOver1.remove(otherCard4);
                                        }
                                }

                                //If all the cards have the same suit, check if cards are A-2-3-4-5 in any order
                                else if ((x1 <=5 || x1 == 14) && (x2 <=5 || x2 == 14) && (x3 <=5 || x3 == 14) && (x4 <=5 || x4 == 14) && (x5 <=5 || x5 == 14))
                                {
                                        // If all cards have the same suit and the cards are A-2-3-4-5, update score and remove the cards from the list(they will remain face up)
                                        int temp = score;
                                        localScore = scoreL4()-temp;
                                        score = score-localScore;

                                        int x = JOptionPane.showConfirmDialog(null, "Your hand score is: " + localScore + ". Take it?");

                                        if (x==0)
                                        {        
                                                score = score+localScore;
                                                this.turnsTakenCounter.getScore(score);
                                                this.turnedCardsBuffer.clear();
                                                localScore=0;
                                                if(GameOver1.size()==(cardsPerRow*rowsPerGrid)-cardsToTurnUp)
                                                {
                                                	
                                                	GameOver.GameEnded(getMode());
                                                }
                                        }
                                        else
                                        {
                                                localScore=0;
                                                this.turnDownTimer.start();
                                                GameOver1.remove(card);
                        						GameOver1.remove(otherCard1);
                        						GameOver1.remove(otherCard2);
                        						GameOver1.remove(otherCard3);
                        						GameOver1.remove(otherCard4);
                                        }
                                }

                                else
                                {                                        
                                        // If the player loses, update score and start the timer to turn cards down
                                        if (score != 0)
                                        {
                                                score = score - 5;
                                                this.turnsTakenCounter.getScore(score);
                                                System.out.println(score);
                                                GameOver1.remove(card);
                        						GameOver1.remove(otherCard1);
                        						GameOver1.remove(otherCard2);
                        						GameOver1.remove(otherCard3);
                        						GameOver1.remove(otherCard4);
                                        }

                                        else
                                        {
                                                //If score is 0 points, it remains in 0.
                                        	 GameOver1.remove(card);
                     						GameOver1.remove(otherCard1);
                     						GameOver1.remove(otherCard2);
                     						GameOver1.remove(otherCard3);
                     						GameOver1.remove(otherCard4);
                                        }
                                        this.turnDownTimer.start();
                                }
                        }

                        //For Two Pair and a Third Wheel
                        else if(                //winning with the 5th card rank
                                        (
                                                        (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                        (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard3.getRank())) && (otherCard2.getSuit().equals(otherCard4.getSuit())) || 
                                                        (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard2.getSuit().equals(otherCard3.getSuit())) || 
                                                        (card.getRank().equals(otherCard2.getRank())) && (card.getRank().equals(otherCard3.getRank())) && (otherCard1.getSuit().equals(otherCard4.getSuit())) || 
                                                        (card.getRank().equals(otherCard2.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard3.getSuit())) || 
                                                        (card.getRank().equals(otherCard3.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit()))
                                                        )

                                                        ||
                                                        //winning with the 5th card suit
                                                        (
                                                                        (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                        (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard3.getSuit())) && (otherCard2.getRank().equals(otherCard4.getRank())) || 
                                                                        (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard2.getRank().equals(otherCard3.getRank())) || 
                                                                        (card.getSuit().equals(otherCard2.getSuit())) && (card.getSuit().equals(otherCard3.getSuit())) && (otherCard1.getRank().equals(otherCard4.getRank())) || 
                                                                        (card.getSuit().equals(otherCard2.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard3.getRank())) || 
                                                                        (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) 
                                                                        )

                                                                        ||
                                                                        //winning with the 5th card suit when already have 3 other cards with same rank
                                                                        (
                                                                                        (card.getSuit().equals(otherCard1.getSuit())) && (otherCard2.getRank().equals(otherCard3.getRank())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                                        (card.getSuit().equals(otherCard2.getSuit())) && (otherCard1.getRank().equals(otherCard3.getRank())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                                        (card.getSuit().equals(otherCard3.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) && (otherCard2.getRank().equals(otherCard4.getRank())) || 
                                                                                        (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) && (otherCard2.getRank().equals(otherCard3.getRank()))
                                                                                        ) 

                                                                                        ||
                                                                                        //winning with the 5th card rank when already have 3 other cards with same suit
                                                                                        (
                                                                                                        (card.getRank().equals(otherCard1.getRank())) && (otherCard2.getSuit().equals(otherCard3.getSuit())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                        (card.getRank().equals(otherCard2.getRank())) && (otherCard1.getSuit().equals(otherCard3.getSuit())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                        (card.getRank().equals(otherCard3.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit())) && (otherCard2.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                        (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit())) && (otherCard2.getSuit().equals(otherCard3.getSuit()))
                                                                                                        )

                                        ) 

                        {
                                // If the player wins, update score and remove the cards from the list(they will remain face up)

                                int temp = score;
                                localScore = scoreL3()-temp;
                                score = score-localScore;
                                int x = JOptionPane.showConfirmDialog(null, "Your hand score is: " + localScore + ". Take it?");

                                if (x==0)
                                {        
                                        score = score+localScore;
                                        this.turnsTakenCounter.getScore(score);
                                        this.turnedCardsBuffer.clear();
                                        localScore=0;
                                        if(GameOver1.size()==(cardsPerRow*rowsPerGrid)-cardsToTurnUp)
                                        {
                                        	
                                        	GameOver.GameEnded(getMode());
                                        }
                                }
                                else
                                {
                                        localScore=0;
                                        this.turnDownTimer.start();
                                        GameOver1.remove(card);
                 						GameOver1.remove(otherCard1);
                 						GameOver1.remove(otherCard2);
                 						GameOver1.remove(otherCard3);
                 						GameOver1.remove(otherCard4);
                                }
                        }

                        else 
                        {
                                // If the player loses, update score and start the timer to turn cards down
                                if (score != 0)
                                {
                                        score = score - 5;
                                        this.turnsTakenCounter.getScore(score);
                                        System.out.println(score);
                                        GameOver1.remove(card);
                 						GameOver1.remove(otherCard1);
                 						GameOver1.remove(otherCard2);
                 						GameOver1.remove(otherCard3);
                 						GameOver1.remove(otherCard4);
                                
                                }

                                else
                                {
                                        //If score is 0 points, it remains in 0.
                                	 GameOver1.remove(card);
              						GameOver1.remove(otherCard1);
              						GameOver1.remove(otherCard2);
              						GameOver1.remove(otherCard3);
              						GameOver1.remove(otherCard4);
                                }                
                                this.turnDownTimer.start();
                        }
                }
                return true;
        }

        @Override
        protected String getMode() {
                // TODO Auto-generated method stub
                return "ComboLevel";
        }
        
        protected int scoreL5() {

                String suitHex1 = turnedCardsBuffer.get(0).getSuit();
                String suitHex2 = turnedCardsBuffer.get(1).getSuit();
                String suitHex3 = turnedCardsBuffer.get(2).getSuit();
                String suitHex4 = turnedCardsBuffer.get(3).getSuit();
                String suitHex5 = turnedCardsBuffer.get(4).getSuit();

                if (suitHex1.equals(suitHex2) && suitHex2.equals(suitHex3) && suitHex3.equals(suitHex4) && suitHex4.equals(suitHex5))
                {
                        int x1 = toInt(turnedCardsBuffer.get(0));
                        int x2 = toInt(turnedCardsBuffer.get(1));
                        int x3 = toInt(turnedCardsBuffer.get(2));
                        int x4 = toInt(turnedCardsBuffer.get(3));
                        int x5 = toInt(turnedCardsBuffer.get(4));

                        if ((x1 >= 10) && (x2 >= 10) && (x3 >= 10) && (x4 >= 10) && (x5 >= 10))
                        {
                                //If the player wins the turn, assign a score equal to 1500 points plus the value of each card
                                localScore = localScore + 1500 + x1+x2+x3+x4+x5;
                        }
                }
                //If the players has no hand in the turn, loses 5 points.
                return localScore;
        }
}