import javax.swing.JFrame;

public class SteelWheelLevel extends TwoPairAndAThirdWheelLevel {

        // Steel Wheel LEVEL: The goal is to find five cards (A-2-3-4-5) with the same distinct suit.

        protected SteelWheelLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
                super(validTurnTime, mainFrame);
                super.turnsTakenCounter.setDifficultyModeLabel("Steel Wheel");
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

                                //If all the cards have the same suit, check if cards are A-2-3-4-5 in any order
                                if ((x1 <=5 || x1 == 14) && (x2 <=5 || x2 == 14) && (x3 <=5 || x3 == 14) && (x4 <=5 || x4 == 14) && (x5 <=5 || x5 == 14))

                                {
                                        // If all cards have the same suit and the cards are A-2-3-4-5, update score and remove the cards from the list(they will remain face up)
                                        this.turnsTakenCounter.getScore(scoreL4());
                                        this.turnedCardsBuffer.clear();
                                        if(GameOver1.size() == cardsPerRow)
                                        {
                                        	
                                        	GameOver.GameEnded(getMode());
                                        }
                                }
                                else
                                {                                        
                                        //If all cards have the same suit but the cards are not A-2-3-4-5, update score and start the timer to turn cards down
                                        this.turnsTakenCounter.getScore(scoreL4());
                                        this.turnDownTimer.start();
                                        GameOver1.remove(card);
                                        GameOver1.remove(otherCard1);
                                        GameOver1.remove(otherCard2);
                                        GameOver1.remove(otherCard3);
                                        GameOver1.remove(otherCard4);
                                }
                        }

                        // If the cards don't have the same suit, start the timer to turn them down
                        else 
                        {
                                this.turnsTakenCounter.getScore(scoreL4());
                                this.turnDownTimer.start();
                                GameOver1.remove(card);
                                GameOver1.remove(otherCard1);
                                GameOver1.remove(otherCard2);
                                GameOver1.remove(otherCard3);
                                GameOver1.remove(otherCard4);
                        }
                }
                return true;
        }

        @Override
        protected String getMode() {
                // TODO Auto-generated method stub
                return "SteelLevel";
        }

        protected int scoreL4() {

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

                        if ((x1 <=5 || x1 == 14) && (x2 <=5 || x2 == 14) && (x3 <=5 || x3 == 14) && (x4 <=5 || x4 == 14) && (x5 <=5 || x5 == 14))
                        {
                                //If the player wins the turn, assign a score equal to 1100 points plus the ASCII equivalent of the hand suit
                                if(suitHex5.equals("s"))
                                {
                                        score = score + 1100 + 53;
                                }
                                else if(suitHex5.equals("h"))
                                {
                                        score = score + 1100 + 48;
                                }
                                else if(suitHex5.equals("d"))
                                {
                                        score = score + 1100 + 44;
                                }
                                else
                                {
                                        score = score + 1100 + 43;
                                }
                        }
                }

                //If the players has no hand in the turn, loses 5 points.
                else
                {
                        if (score != 0)
                        {
                                score = score - 5;
                        }

                        else
                        {
                                //If score is 0 points, it remains in 0.
                        }
                }
                return score;
        }
}