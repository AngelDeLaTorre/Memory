import javax.swing.JFrame;

public class EqualPairLevel extends EasyLevel {

        protected EqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {

                super(validTurnTime,mainFrame);
                super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
        }

        @Override
        protected boolean addToTurnedCardsBuffer(Card card) {
                this.turnedCardsBuffer.add(card);
                GameOver1.add(card);
                if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
                {
                        // there are two cards faced up
                        // record the player's turn

                        this.turnsTakenCounter.increment();

                        // get the other card (which was already turned up)
                        Card otherCard = (Card) this.turnedCardsBuffer.get(0);
                        
                        // the cards match, so remove them from the list (they will remain face up)
                        if( otherCard.getNum() == card.getNum())
                        {
                                this.turnsTakenCounter.getScore(scoreL1());
                                this.turnedCardsBuffer.clear();
                                if(GameOver1.size() == this.cardsPerRow*this.rowsPerGrid)
                                {
                                	
                                	GameOver.GameEnded(getMode());
                                }
                        }
                        
                        // the cards do not match, so start the timer to turn them down
                        else
                        {
                                this.turnsTakenCounter.getScore(scoreL1());
                                this.turnDownTimer.start();
                                GameOver1.remove(card);
                                GameOver1.remove(otherCard);
                        }
                }
                return true;
        }

        @Override
        protected boolean turnUp(Card card) {
                // the card may be turned
                if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
                {
                        return this.addToTurnedCardsBuffer(card);
                }
                // there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
                return false;
        }

        @Override
        protected String getMode() {
                // TODO Auto-generated method stub
                return "MediumMode";
        }

        protected int scoreL1() {

                Card otherCard = turnedCardsBuffer.get(0);
                Card card = turnedCardsBuffer.get(1);

                if (otherCard.getNum() == card.getNum())
                {
                        score = score + 50;
                }
                else
                {
                        if(score !=0)
                        {
                                score = score -5;
                        }
                        else
                        {
                                //If score is 0 points, it remains in 0.
                        }
                }
                return score;
        }
}