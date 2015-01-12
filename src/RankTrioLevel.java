import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RankTrioLevel extends EqualPairLevel {

        // TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank

        protected RankTrioLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
                super(validTurnTime, mainFrame);
                super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
                cardsToTurnUp = 3;
                cardsPerRow = 10;
                rowsPerGrid = 5;
        }

        @Override
        protected void makeDeck() {

                // In Trio level the grid consists of distinct cards, no repetitions
                ImageIcon cardIcon[] = this.loadCardIcons();

                //back card
                ImageIcon backIcon = cardIcon[TotalCardsPerDeck];

                int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
                for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
                {
                        cardsToAdd[i] = i;
                }

                // randomize the order of the deck
                this.randomizeIntArray(cardsToAdd);

                // make each card object
                for(int i = 0; i < cardsToAdd.length; i++)
                {
                        // number of the card, randomized
                        int num = cardsToAdd[i];
                        // make the card object and add it to the panel
                        String rank = cardNames[num].substring(0, 1);
                        String suit = cardNames[num].substring(1, 2);
                        this.grid.add( new Card(this, backIcon,cardIcon[num], num, rank, suit));
                }
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
                        if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
                                // Three cards match, so remove them from the list (they will remain face up)

                                this.turnsTakenCounter.getScore(scoreL2());
                                this.turnedCardsBuffer.clear();
                                if(GameOver1.size() == (cardsPerRow*rowsPerGrid)-14)
                                {
                                
                                	GameOver.GameEnded(getMode());
                                }
                        }
                        else 
                        {
                                this.turnsTakenCounter.getScore(scoreL2());
                                this.turnDownTimer.start();
                                GameOver1.remove(card);
                                GameOver1.remove(otherCard1);
                                GameOver1.remove(otherCard2);
                        }
                }
                return true;
        }

        @Override
        protected String getMode() {
                // TODO Auto-generated method stub
                return "TrioLevel";
        }
        
        protected int scoreL2() {

                Card card1 = turnedCardsBuffer.get(0);
                Card card2 = turnedCardsBuffer.get(1);
                Card card3 = turnedCardsBuffer.get(2);

                if (card1.getRank().equals(card2.getRank()) && card2.getRank().equals(card3.getRank()) && card1.getRank().equals(card3.getRank()))
                {
                        score = score + 100 + toInt(card1) + toInt(card2) + toInt(card3);
                }

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