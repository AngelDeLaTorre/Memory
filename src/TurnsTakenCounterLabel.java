/**
 * This class inherits from JLabel and implements the turn counter widget.
 *
 * Assignment: MP2
 * Class: CS 340, Fall 2005
 * TA: Nitin Jindal
 * System: jEdit, jdk-1.5.0.4, Windows XP
 * @author Michael Leonhard (CS account mleonhar)
 * @version 22 Sep 2005
 */

import javax.swing.JLabel;

public class TurnsTakenCounterLabel extends JLabel 
{
        private int totalScore;
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        private int numTurns = 0;
        private  String DESCRIPTION, DESCRIPTION2;

        public TurnsTakenCounterLabel()
        {
                super();
                reset();
        }
        public void setDifficultyModeLabel(String difficultyMode){
                DESCRIPTION = "Turns Taken: ";
                DESCRIPTION2 = "Score: ";
                setHorizontalTextPosition(JLabel.LEFT);
        }

        public int getNumOfTurns(){
                return this.numTurns;
        }

        /**
         * Update the text label with the current counter value
         */
        private void update()
        {
                setText(DESCRIPTION + Integer.toString(this.numTurns) + " " + DESCRIPTION2 + Integer.toString(this.totalScore));
                setHorizontalTextPosition(JLabel.LEFT);
        }

        /**
         * Default constructor, starts counter at 0
         */


        /**
         * Increments the counter and updates the text label
         */
        public void increment()
        {
                this.numTurns++;
                update();
        }

        /**
         * Resets the counter to zero and updates the text label
         */
        public void reset()
        {
                this.numTurns = 0;
                totalScore=0;
                update();
        }
        
        //Display the score in the frame
        //Is declared here because in each turn taken by the user the score has to be updated.
        public int getScore(int score)
        {
                this.totalScore = score;
                update();
                return totalScore;
        }
}