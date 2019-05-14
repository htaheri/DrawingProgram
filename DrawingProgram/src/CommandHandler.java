import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class CommandHandler {
    private Canvas canvas;
    private ErrorType latestError = ErrorType.NoError;
    
    ErrorType GetLatestError()
    {
    	return latestError;
    }

	public static void main(String[] args) {
		CommandHandler userCommandHandler = new CommandHandler();
		userCommandHandler.HandleUserInputs();
	}

	void HandleUserInputs() {
		while (true) {
			latestError = ErrorType.NoError;
	    	ProcessUserCommand(ScanUserInput());
	    	RaiseErrors();
	      }
	}

	private void RaiseErrors() {
		 switch (latestError) {
		 case CanvasNotCreated:	
			 System.out.println("Unable To Create The Canvas!");
			 break;
		 case LineNotCreated:	
			 System.out.println("Unable To Create The Line!");
			 break;
		 case RectangleNotCreated:	
			 System.out.println("Unable To Create The Rectangle!");
			 break;
		 case CanvasNotAvailable:
			 System.out.println("There is not any canvas available!");
			 break;			 
		 }
		latestError = ErrorType.NoError;
	}

	void ProcessUserCommand(String userInput) {
		UserCommand userCommand = ParseUserCommand(userInput);
		
		 switch (userCommand) {
		 case Quit:
			 System.out.println("Quit");
             System.exit(0);
		 case CreateCanvas:
			 CreateCanvas(userInput);
			 break;
		 case CreateLine:
			 CreateLine(userInput);
			 break;
		 case CreateRectangle:
			 CreateRectangle(userInput);
			 break;
		 case Help:
			 System.out.println("C w h: Create a new canvas of width w and height h");
			 System.out.println("L x1 y1 x2 y2 :Draw a new line from coordinates (x1, y1) to (x2, y2) horizontally or vertically. Lines are made up of the x character");
			 System.out.println("R x1 y1 x2 y2 :Draw a new rectangle, with upper left corner at coordinate (x1, y1) and lower right coordinate at (x2, y2). Lines are made up of the x character");
			 System.out.println("Q Quit the program");
			 break;
		 case UnknownRequest:
			 System.out.println("Unknown Request");
			 break;
		 } 		
	}

	void CreateRectangle(String userCommandParameter) {
		if(canvas != null)
		{  
		Point[] points = GetTwoPointsValues(userCommandParameter);
		if (canvas.IsValidShapeDimention(points[0]) && canvas.IsValidShapeDimention(points[1]))
		{	
			try {
		
				Rectangle aRectangle = new Rectangle(points[0].getX(),points[0].getY(),points[1].getX(),points[1].getY());

				canvas.AddShape(aRectangle);
			}
			
			catch  (InvalidParameterException e) {latestError = ErrorType.RectangleNotCreated;}
		}
		else
			latestError = ErrorType.RectangleNotCreated;
		}
		else
			latestError = ErrorType.CanvasNotAvailable;	
	}

	void CreateLine(String userCommandParameter) {
		if(canvas != null)
		{
			Point[] points = GetTwoPointsValues(userCommandParameter);
			if (canvas.IsValidShapeDimention(points[0]) && canvas.IsValidShapeDimention(points[1]))
			{
				try {
					Line aLine = new Line(points[0].getX(),points[0].getY(),points[1].getX(),points[1].getY());
					canvas.AddShape(aLine);		
				}
				catch  (InvalidParameterException e) {latestError = ErrorType.LineNotCreated;}
					
				
			}
			else
				latestError = ErrorType.LineNotCreated;
		}
		else
			latestError = ErrorType.CanvasNotAvailable;				
	}

	Point[] GetTwoPointsValues(String userCommandParameter) {
		
		Point[] points = null;
		String[] commands = ParseStringToArray(userCommandParameter);
		if(commands != null && commands.length >= 4)
		{
			points = new Point[2];
			points[0] = ParseStringToPoint(commands);
			commands = Arrays.copyOfRange(commands, 2, commands.length);
			points[1] = ParseStringToPoint(commands);
		}
		return points;
	}

	void CreateCanvas(String userCommandParameter) {
			Point canvasBottomRightPoint = GetOnePointValue(userCommandParameter);
			if (canvasBottomRightPoint != null && canvasBottomRightPoint.getX()>0 && canvasBottomRightPoint.getY()>0)
				canvas = new Canvas(canvasBottomRightPoint.getX(),canvasBottomRightPoint.getY());
			else 
				latestError = ErrorType.CanvasNotCreated;		

	}

	Point GetOnePointValue(String userCommandParameter) {
		String[] commands = ParseStringToArray(userCommandParameter);
		return ParseStringToPoint(commands);
	}

	String[] ParseStringToArray(String userCommandParameter) {
		String[] commands = null;
		if(userCommandParameter.length() >= 5)
		{
			userCommandParameter = userCommandParameter.substring(2);
			userCommandParameter = userCommandParameter.trim();
			commands = userCommandParameter.split("\\s+");
		}
        
		return commands;
	}
	
	Point ParseStringToPoint(String[] userCommandParameterArray) {
        
		if(userCommandParameterArray != null && userCommandParameterArray.length > 1)
			  try {
				    return new Point(Integer.parseInt(userCommandParameterArray[0]),Integer.parseInt(userCommandParameterArray[1]));
				  } catch (NumberFormatException e) {
				    return null;
				  }
		else
			return null;
	}

	private String ScanUserInput() {
		String userCommand;
		
		Scanner scanner = new Scanner(System.in);
        System.out.print("enter command:");
        userCommand = scanner.nextLine();

	    return userCommand;
	}
	
	UserCommand ParseUserCommand(String userInput)
	{
		if (userInput.length()>0)
		 switch (userInput.toUpperCase().charAt(0)) {
		 case 'Q':
			 return UserCommand.Quit;
		 case 'C':
			 return UserCommand.CreateCanvas; 
		 case 'L':
			 return UserCommand.CreateLine; 
		 case 'R':
			 return UserCommand.CreateRectangle; 
		 case '?':
			 return UserCommand.Help; 
			 } 
		
		return UserCommand.UnknownRequest;
		
	}
}
