import javax.swing.JFrame;

public class TwoPairAndAThirdWheelLevel extends RankTrioLevel {

        // Two Pair and a Wheel Level: The goal is to find, on each turn, three cards with the same rank or suit and another two card with the same rank or suit.

        protected TwoPairAndAThirdWheelLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
                super(validTurnTime, mainFrame);
                super.turnsTakenCounter.setDifficultyModeLabel("Two and a Third");
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

                        //This block of the if statement check for all the possibilities to win
                        if(                //winning with the 5th card rank
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
                                this.turnsTakenCounter.getScore(scoreL3());
                                this.turnedCardsBuffer.clear();
                                if(GameOver1.size()==(cardsPerRow*rowsPerGrid)-cardsToTurnUp)
                                {
                                	
                                	GameOver.GameEnded(getMode());
                                }
                        }
                        else 
                        {
                                // If the player loses, update score and start the timer to turn cards down
                                this.turnsTakenCounter.getScore(scoreL3());
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
                return "HardLevel";
        }

        protected int scoreL3() {

                Card otherCard1 = turnedCardsBuffer.get(0);
                Card otherCard2 = turnedCardsBuffer.get(1);
                Card otherCard3 = turnedCardsBuffer.get(2);
                Card otherCard4 = turnedCardsBuffer.get(3);
                Card card = turnedCardsBuffer.get(4);

                if(
                                (
                                                (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard3.getRank())) && (otherCard2.getSuit().equals(otherCard4.getSuit())) || 
                                                (card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard2.getSuit().equals(otherCard3.getSuit())) || 
                                                (card.getRank().equals(otherCard2.getRank())) && (card.getRank().equals(otherCard3.getRank())) && (otherCard1.getSuit().equals(otherCard4.getSuit())) || 
                                                (card.getRank().equals(otherCard2.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard3.getSuit())) || 
                                                (card.getRank().equals(otherCard3.getRank())) && (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit()))
                                                )

                                                ||

                                                (
                                                                (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard3.getSuit())) && (otherCard2.getRank().equals(otherCard4.getRank())) || 
                                                                (card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard2.getRank().equals(otherCard3.getRank())) || 
                                                                (card.getSuit().equals(otherCard2.getSuit())) && (card.getSuit().equals(otherCard3.getSuit())) && (otherCard1.getRank().equals(otherCard4.getRank())) || 
                                                                (card.getSuit().equals(otherCard2.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard3.getRank())) || 
                                                                (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) 
                                                                )

                                                                ||

                                                                (
                                                                                (card.getSuit().equals(otherCard1.getSuit())) && (otherCard2.getRank().equals(otherCard3.getRank())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                                (card.getSuit().equals(otherCard2.getSuit())) && (otherCard1.getRank().equals(otherCard3.getRank())) && (otherCard3.getRank().equals(otherCard4.getRank())) || 
                                                                                (card.getSuit().equals(otherCard3.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) && (otherCard2.getRank().equals(otherCard4.getRank())) || 
                                                                                (card.getSuit().equals(otherCard4.getSuit())) && (otherCard1.getRank().equals(otherCard2.getRank())) && (otherCard2.getRank().equals(otherCard3.getRank()))
                                                                                ) 

                                                                                ||

                                                                                (
                                                                                                (card.getRank().equals(otherCard1.getRank())) && (otherCard2.getSuit().equals(otherCard3.getSuit())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                (card.getRank().equals(otherCard2.getRank())) && (otherCard1.getSuit().equals(otherCard3.getSuit())) && (otherCard3.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                (card.getRank().equals(otherCard3.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit())) && (otherCard2.getSuit().equals(otherCard4.getSuit())) || 
                                                                                                (card.getRank().equals(otherCard4.getRank())) && (otherCard1.getSuit().equals(otherCard2.getSuit())) && (otherCard2.getSuit().equals(otherCard3.getSuit()))
                                                                                                )                        
                                )

                        //If the player wins the turn, assign a score equal to 800 points plus the rank of the cards in the turn
                {
                        score = score + 800 + toInt(card) + toInt(otherCard1) + toInt(otherCard2) + toInt(otherCard3) + toInt(otherCard4);
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