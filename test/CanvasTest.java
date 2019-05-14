import static org.junit.Assert.*;

import org.junit.Test;

public class CanvasTest {
	private Canvas canvas = new Canvas(10,10);

	
	@Test
	public void testIsValidShapeDimentionWithValidShapes()	
	{
		Point aPoint = new Point(5,5);
		assertEquals(Boolean.TRUE, canvas.IsValidShapeDimention(aPoint));
	}
	
	@Test
	public void testIsValidShapeDimentionWithLongShapes()	
	{
		Point aPoint = new Point(15,5);
		assertEquals(Boolean.FALSE, canvas.IsValidShapeDimention(aPoint));
	}

	@Test
	public void testIsValidShapeDimentionWithWideShapes()	
	{
		Point aPoint = new Point(5,15);
		assertEquals(Boolean.FALSE, canvas.IsValidShapeDimention(aPoint));
	}
}
