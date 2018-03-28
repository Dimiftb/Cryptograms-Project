import java.util.Comparator;

public class Player
{
		private String name;
		private double playerAccuracy;
		private int avgCompletionTime;
		private int nrOfPlayedCryptograms;
		private int nrOfCompletedCryptograms;
		
		public Player(String name)
		{
			this.name = name;
			playerAccuracy = 0;
			avgCompletionTime = 0;
			nrOfPlayedCryptograms = 0;
			nrOfCompletedCryptograms = 0;
		}
		public Player(String name, double Acc, int Avg, int Played, int Comp)
		{
			this.name = name;
			playerAccuracy = Acc;
			avgCompletionTime = Avg;
			nrOfPlayedCryptograms = Played;
			nrOfCompletedCryptograms = Comp;
		}
		
		public String getName()
		{
			return name;
		}
		public void setName(String newName)
		{
			name = newName;
		}
		public void showStats()
		{
			System.out.println("This player's accuracy is: " + playerAccuracy + " .");
			System.out.println("This player's average cryptogram completion time is: " + avgCompletionTime + " .");
			System.out.println("This player has played " + nrOfPlayedCryptograms + " cryptograms.");
			System.out.println("This player has  completed " + nrOfCompletedCryptograms + " cryptograms.\n");
		}
		public void updateAccuracy(double lastRecAccuracy)
		{
			if(nrOfPlayedCryptograms == 1)
			{
				playerAccuracy = lastRecAccuracy;
				return;
			}
			playerAccuracy = (playerAccuracy + lastRecAccuracy) / nrOfPlayedCryptograms;
		}
		public void updateAvgTime(int lastRecTime)
		{
			if(nrOfCompletedCryptograms == 1)
			{
				avgCompletionTime = lastRecTime;
				return;
			}
			avgCompletionTime = (avgCompletionTime + lastRecTime) / nrOfCompletedCryptograms;
		}
		public  void incrementPlayedCryptos()
		{
			nrOfPlayedCryptograms++;
		}
		public void incrementCompletedCryptos()
		{
			nrOfCompletedCryptograms++;
		}
		public double getPlayerAccuracy()
		{
			return playerAccuracy;
		}
		public int getAvgCompletionTime()
		{
			return avgCompletionTime;
		}
		public int getNrOfPlayedCryptograms()
		{
			return nrOfPlayedCryptograms;
		}
		public int getNrOfCompletedCryptograms()
		{
			return nrOfCompletedCryptograms;
		}
}
