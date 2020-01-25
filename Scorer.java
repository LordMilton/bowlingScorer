public class Scorer
{
	public static final int numFrames = 10;

	public static void main(String[] args)
	{
		String[] frameStrings = args[0].split("-");
		Frame[] frames = buildFrames(frameStrings);

		frames[0].calcScore(0);
		for(int i = 1; i < numFrames; ++i)
		{
			frames[i].calcScore(frames[i-1].getCumScore());
		}

		System.out.println("Final Score: "+ frames[numFrames-1].getCumScore());
	}

	public static Frame[] buildFrames(String[] frameStrings)
	{
		Frame[] frames = new Frame[numFrames];

		//First 9 frames
		for(int i = 0; i < numFrames-1; ++i)
		{
			String curFrameString = frameStrings[i];
			char firstThrow = curFrameString.charAt(0);
			char secondThrow = 0;
			try{
				secondThrow = curFrameString.charAt(1);
			}catch(StringIndexOutOfBoundsException e){
			}

			Frame curFrame = new Frame(firstThrow, secondThrow);
			frames[i] = curFrame;
		}

		//Tenth Frame
		String curFrameString = frameStrings[9];
		char firstThrow = curFrameString.charAt(0);
		char secondThrow = curFrameString.charAt(1);
		char thirdThrow = 0;
		try{
			thirdThrow = curFrameString.charAt(2);
		}catch(StringIndexOutOfBoundsException e){
		}

		frames[numFrames-1] = new TenthFrame(firstThrow, secondThrow, thirdThrow);

		//Tie the frames together (nextFrames)
		for(int i = 0; i < numFrames-1; ++i)
		{
			frames[i].setNextFrame(frames[i+1]);
		}

		return frames;
	}
}
