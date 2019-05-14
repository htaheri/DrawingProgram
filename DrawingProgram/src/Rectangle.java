import java.security.InvalidParameterException;

public class Rectangle implements Shape
{
    private int x1;
    private int y1;	
    private int x2;
    private int y2;	
    
    
	public Rectangle(int i, int j, int k, int l) {
		if (i > 0 && j>0 && k>0 && l>0)
		{
			x1=i;
			y1=j;
			x2=k;
			y2=l;
		}
		else
			throw new InvalidParameterException("The parameters are not valid to create a rectangle");

	}

    
	@Override
	public Boolean[][] Draw(Boolean[][] pixels) {
		Line line1 = new Line(x1,y1,x2,y1);
		Line line2 = new Line(x1,y2,x2,y2);
		Line line3 = new Line(x1,y1,x1,y2);
		Line line4 = new Line(x2,y1,x2,y2);
		
		pixels = line1.Draw(pixels);
		pixels = line2.Draw(pixels);
		pixels = line3.Draw(pixels);
		pixels = line4.Draw(pixels);
		
		return pixels;
	}

}
