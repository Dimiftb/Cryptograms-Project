
public class Player
{
		private String name;
		private float playerAccuracy;
		private float avgCompletionTime;
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
			System.out.println("This player's accuracy is: " + playerAccuracy + " .\n");
			System.out.println("This player's average cryptogram completion time is: " + avgCompletionTime + " .\n");
			System.out.println("This player has played " + nrOfPlayedCryptograms + " cryptograms.\n");
			System.out.println("This player has  completed " + nrOfCompletedCryptograms + " cryptograms.\n");
		}
		public void updateAccuracy(float lastRecAccuracy)
		{
			if(nrOfPlayedCryptograms == 1)
			{
				playerAccuracy = lastRecAccuracy;
				return;
			}
			playerAccuracy = (playerAccuracy + lastRecAccuracy) / nrOfPlayedCryptograms;
		}
		public void updateAvgTime(float lastRecTime)
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
		public float getPlayerAccuracy()
		{
			return playerAccuracy;
		}
		public float getAvgCompletionTime()
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
