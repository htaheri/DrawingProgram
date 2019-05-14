import static org.junit.Assert.*;

import org.junit.Test;

public class CommandHandlerTest {

	private CommandHandler commandHandler = new CommandHandler();
	
	@Test
	public void testParseUserCommand() {
		assertEquals(UserCommand.Quit, commandHandler.ParseUserCommand("Q"));
		assertEquals(UserCommand.Quit, commandHandler.ParseUserCommand("q"));
		assertEquals(UserCommand.CreateCanvas, commandHandler.ParseUserCommand("C"));
		assertEquals(UserCommand.CreateCanvas, commandHandler.ParseUserCommand("c"));		
		assertEquals(UserCommand.CreateLine, commandHandler.ParseUserCommand("L"));
		assertEquals(UserCommand.CreateLine, commandHandler.ParseUserCommand("l"));
		assertEquals(UserCommand.CreateRectangle, commandHandler.ParseUserCommand("R"));
		assertEquals(UserCommand.CreateRectangle, commandHandler.ParseUserCommand("r"));		
		assertEquals(UserCommand.Help, commandHandler.ParseUserCommand("?"));
		assertEquals(UserCommand.UnknownRequest, commandHandler.ParseUserCommand("A"));
		assertEquals(UserCommand.UnknownRequest, commandHandler.ParseUserCommand("1"));
		assertEquals(UserCommand.UnknownRequest, commandHandler.ParseUserCommand(""));
		}
	
	@Test
	public void testGetOnePointValueCorrectValues()	
	{
		assertEquals(1, commandHandler.GetOnePointValue("C 1 2").getX());
		assertEquals(2, commandHandler.GetOnePointValue("C 1 2").getY());	
		assertEquals(2, commandHandler.GetOnePointValue("C 1 2 ").getY());	
	}
	
	@Test
	public void testGetOnePointValueWrongValues()	
	{
		assertEquals(null, commandHandler.GetOnePointValue("C"));
		assertEquals(null, commandHandler.GetOnePointValue("C "));	
		assertEquals(null, commandHandler.GetOnePointValue("C 1 "));	
		assertEquals(null, commandHandler.GetOnePointValue("C A "));	
		assertEquals(null, commandHandler.GetOnePointValue("C 1 A"));	
	}
	
	@Test
	public void testGetTwoPointsValuesCorrectValues()	
	{
		assertEquals(1, commandHandler.GetTwoPointsValues("L 1 2 3 4")[0].getX());
		assertEquals(2, commandHandler.GetTwoPointsValues("L 1 2 3 4")[0].getY());	
		assertEquals(3, commandHandler.GetTwoPointsValues("C 1 2 3 4")[1].getX());
		assertEquals(4, commandHandler.GetTwoPointsValues("L 1 2 3 4")[1].getY());
	}
	

	@Test
	public void testGetTwoPointsValuesWrongValues()	
	{
		assertEquals(null, commandHandler.GetTwoPointsValues("L"));
		assertEquals(null, commandHandler.GetTwoPointsValues("L"));	
		assertEquals(null, commandHandler.GetTwoPointsValues("L 1 2"));	
		assertEquals(null, commandHandler.GetTwoPointsValues("L 1 2 3"));	
		assertEquals(null, commandHandler.GetTwoPointsValues("L 1 2 3 A")[1]);	
		assertEquals(null, commandHandler.GetTwoPointsValues("L 1 2 A 4")[1]);
		assertEquals(null, commandHandler.GetTwoPointsValues("L 1 A 3 4")[0]);	
		assertEquals(null, commandHandler.GetTwoPointsValues("L A 2 3 4")[0]);
	}
	
	@Test
	public void testCreateCanvasWithCorrectValues()	
	{
		commandHandler.CreateCanvas("C 10 20");
		assertEquals(ErrorType.NoError, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateCanvasWithoutValue()	
	{
		commandHandler.CreateCanvas("C");
		assertEquals(ErrorType.CanvasNotCreated, commandHandler.GetLatestError());
	}	
	
	@Test
	public void testCreateCanvasWithOneValue()	
	{
		commandHandler.CreateCanvas("C 1");
		assertEquals(ErrorType.CanvasNotCreated, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateCanvasWithWrongValue()	
	{
		commandHandler.CreateCanvas("C 1 A");
		assertEquals(ErrorType.CanvasNotCreated, commandHandler.GetLatestError());
	}

	@Test
	public void testCreateCanvasWithNegativeValue()	
	{
		commandHandler.CreateCanvas("C 1 -1");
		assertEquals(ErrorType.CanvasNotCreated, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateLineWithCorrectValues()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateLine("L 1 2 1 3");
		assertEquals(ErrorType.NoError, commandHandler.GetLatestError());
	}

	@Test
	public void testCreateLineWithoutCanvas()	
	{
		commandHandler.CreateLine("L 1 2 1 3");
		assertEquals(ErrorType.CanvasNotAvailable, commandHandler.GetLatestError());
	}

	@Test
	public void testCreateLineWithWrongValues()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateLine("L 1 2 3 4");
		assertEquals(ErrorType.LineNotCreated, commandHandler.GetLatestError());
	}	

	@Test
	public void testCreateLineWithNegativeValues()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateLine("L 1 -2 1 4");
		assertEquals(ErrorType.LineNotCreated, commandHandler.GetLatestError());
	}	
	@Test
	public void testCreateLineBiggerThanCanvas()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateLine("L 1 5 1 15");
		assertEquals(ErrorType.LineNotCreated, commandHandler.GetLatestError());
	}	
	
	@Test
	public void testCreateRectangleWithCorrectValues()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateRectangle("R 1 2 3 7");
		assertEquals(ErrorType.NoError, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateRectangleWithoutCanvas()	
	{
		commandHandler.CreateRectangle("R 1 2 1 3");
		assertEquals(ErrorType.CanvasNotAvailable, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateRectangleWithNegativeValues()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateRectangle("R 1 -2 3 4");
		assertEquals(ErrorType.RectangleNotCreated, commandHandler.GetLatestError());
	}
	
	@Test
	public void testCreateRectangleBiggerThanCanvas()	
	{
		commandHandler.CreateCanvas("C 10 10");
		commandHandler.CreateRectangle("R 1 5 4 15");
		assertEquals(ErrorType.RectangleNotCreated, commandHandler.GetLatestError());
	}
}
