public class Frame
{
	protected int cumScore;
	protected char firstThrow;
	protected char secondThrow; //Values less than 47 will be interpreted as a non-existent
				    //throw (in the case of strikes)
	protected Frame nextFrame;
	protected boolean isTenth;

	//In the case of a strike, @param secondThrow should be some value less than 47
	public Frame(char firstThrow, char secondThrow)
	{
		this.firstThrow = firstThrow;
		this.secondThrow = secondThrow;
		isTenth = false;
	}

	public void setNextFrame(Frame frame)
	{
		nextFrame = frame;
	}

	public Frame getNextFrame()
	{
		return nextFrame;
	}

	//This should never return null
	public char getFirstThrow()
	{
		return firstThrow;
	}

	//This can return null
	public char getSecondThrow()
	{
		return secondThrow;
	}

	public void calcScore(int latestScore)
	{
		int tempScore = latestScore;
		int firstThrowVal = firstThrow - 48;
		int secondThrowVal = secondThrow - 48;
		boolean strike = false;
		if(firstThrowVal > 9)
		{
			strike = true;
			tempScore += charToScore(firstThrow);
			tempScore += charToScore(nextFrame.getFirstThrow());
			if(nextFrame.getSecondThrow() >= 47)
			{
				tempScore += charToScore(nextFrame.getSecondThrow());
				if(nextFrame.getSecondThrow() == 47) // If second throw is a spare, just want to double 10 instead of 10 + firstThrow
				{
					tempScore -= charToScore(nextFrame.getFirstThrow());
				}
			}
			else
			{
				tempScore += charToScore(nextFrame.getNextFrame().getFirstThrow());
			}
		}
		else if(firstThrowVal >= 0)
		{
			tempScore += charToScore(firstThrow);
		}
		else
		{
			System.err.println("Invalid first throw of '/'");
			System.exit(1);
		}

		if(!strike)
		{
			if(secondThrowVal > 9)
			{
				System.err.println("Invalid second throw of 'X'");
				System.exit(1);
			}
			else if(secondThrowVal >= 0)
			{
				tempScore += charToScore(secondThrow);
			}
			else
			{
				tempScore += charToScore(secondThrow) - charToScore(firstThrow);
				tempScore += charToScore(nextFrame.getFirstThrow());
			}
		}

		System.err.println(tempScore);
		cumScore = tempScore;
	}

	public boolean isTenth()
	{
		return isTenth;
	}

	public int getCumScore()
	{
		return cumScore;
	}

	protected int charToScore(char c)
	{
		int score = c - 48;
		if(score > 9 || score < 0)
			score = 10;
		return score;
	}
}
