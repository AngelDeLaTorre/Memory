import javax.swing.JOptionPane;

public class GameOver 
{

	public static void GameEnded(String mode)
	{

		if(mode=="EasyMode")
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat Easy Level",JOptionPane.OK_OPTION);
			System.exit(0);
		}
		else if(mode=="MediumMode")
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat EqualPair Level",JOptionPane.OK_OPTION);
			System.exit(0);
		}
		else if(mode=="TrioLevel")
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat RankTrio Level",JOptionPane.OK_OPTION);	
			System.exit(0);
		}
		else if(mode=="SteelLevel")
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat SteelWheel Level",JOptionPane.OK_OPTION);
			System.exit(0);
		}
		else if(mode=="HardLevel")
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat TwoPairAndAThirdWheel Level",JOptionPane.OK_OPTION);			
			System.exit(0);
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "No More legal Moves","You Beat Combo Level",JOptionPane.OK_OPTION);
			System.exit(0);
		}
	}

}
