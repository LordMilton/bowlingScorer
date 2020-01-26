public class TenthFrame extends Frame
{
	private char thirdThrow; //Values less than 47 will be interpreted as a non-existent throw
				 //in the case that the third throw is not earned

	//In the case that the third throw is not earned, @param thirdThrow should be some value less
	//than 47
	public TenthFrame(char firstThrow, char secondThrow, char thirdThrow)
	{
		super(firstThrow, secondThrow);
		this.thirdThrow = thirdThrow;
		isTenth = true;
	}

	//This can return null
	public char getThirdThrow()
	{
		return thirdThrow;
	}

	//Calculates score for this frame which should be the final score for the game
	public void calcScore(int latestScore)
	{
		int tempScore = latestScore;
		boolean isBonus = false;
		int firstThrowVal = firstThrow - 48; //Ascii 0 is represented by 48
		int secondThrowVal = secondThrow - 48;
		int thirdThrowVal = 0;
		if(thirdThrow >= 47)
			thirdThrowVal = thirdThrow - 48;

		if(firstThrowVal > 9) //Ascii X is after the digits (88)
		{
			isBonus = true;
		}
		else if(firstThrowVal < 0)
		{
			System.out.println("Invalid first throw of '/'");
			System.exit(1);
		}
		tempScore += charToScore(firstThrow);

		if(secondThrowVal > 9 || secondThrowVal < 0)
		{
			isBonus = true;
		}
		tempScore += charToScore(secondThrow);
		if(secondThrowVal < 0)
		{
			tempScore -= charToScore(firstThrow);
		}

		if(isBonus)
		{
			tempScore += charToScore(thirdThrow);
		}

		cumScore = tempScore;
	}
}
