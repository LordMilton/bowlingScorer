public class Frame
{
	// Cumulative score
	protected int cumScore;
	protected char firstThrow;
	protected char secondThrow; //Values less than 47 will be interpreted as a non-existent
				    //throw (in the case of strikes)
	// Next frame in the game for purposes of calculating spares and strikes
	protected Frame nextFrame;

	//In the case of a strike, @param secondThrow should be some value less than 47
	public Frame(char firstThrow, char secondThrow)
	{
		cumScore = 0;
		this.firstThrow = firstThrow;
		this.secondThrow = secondThrow;
		nextFrame = null;
	}

	public void setNextFrame(Frame frame)
	{
		nextFrame = frame;
	}

	public Frame getNextFrame()
	{
		return nextFrame;
	}

	public char getFirstThrow()
	{
		return firstThrow;
	}

	public char getSecondThrow()
	{
		return secondThrow;
	}

	//Calculates score for this frame
	//setNextFrame() MUST BE USED BEFORE THIS METHOD, else a null pointer exception will be thrown
	public void calcScore(int latestScore)
	{
		if(nextFrame == null)
		{
			System.err.println("No next frame set");
			throw new NullPointerException();
		}

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

	public int getCumScore()
	{
		return cumScore;
	}
	
	//Helper method for translating the character representing a throw into an integer score
	protected int charToScore(char c)
	{
		int score = c - 48;
		if(score > 9 || score < 0)
			score = 10;
		return score;
	}
}
