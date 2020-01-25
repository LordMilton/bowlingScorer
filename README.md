Scores a bowling game in a professional and very employable manner.

## Input

Input is expected as a string of 10 frames each separated by a '-' where a frame is either a 'X' for a strike, a digit and then a '/' for a spare, or two digits in a row for an open frame. Frames beginning with a '/' or having a second throw of 'X' will result in an appropriate error message and a final score will not be provided. The tenth frame should be a combination of two: digits, '/'s, 'X's as long as the first value is not a '/'. The tenth frame should contain a third throw if either a '/' or 'X' occurred in the first two throws.

## Issues (acceptable within expected scope)

Normal frames in which a strike occurs will accept a second throw but the second throw will be ignored.
Normal open frames with two values adding to more than 9 will be accepted.
Tenth frames will not check if the bonus throw(s) has been recorded.
Tenth frames will accept a spare immediately after a strike or two spares in a row.
