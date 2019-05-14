import java.util.ArrayList;
import java.util.Arrays;

public class Canvas {

    private int width;
    private int height;
    private Boolean[][] pixels;
    
    private static char EMPTY_CHAR = ' ';
    private static char H_BOUND_CHAR = '-';
    private static char V_BOUND_CHAR = '|';
    private static char LINE_CHAR ='x';
    
    public ArrayList<Shape> shapes;

    
    Canvas(int w, int h)
    {
    	width = w;
    	height = h;
    	
    	if (width>0 && height>0)
    	{
    		shapes = new ArrayList<Shape>();
    	
    		pixels = new Boolean[width][height];
    		ClearPixels();
    		Render();
    	}
     }
   
    public Boolean[][] GetPixels()
    {
    	return pixels;
    }
    
    public void Render() {    	
    	RenderHBorder();
    	RenderRows();
    	RenderHBorder();
    }

    private void RenderHBorder()
    {
    	for (int w=0;w<=width+1;w++)
    		System.out.print(H_BOUND_CHAR);
    	
    	System.out.println();
    }
    
    private void RenderRows()
    {
    	for (int y=0;y<height;y++)
    	{
    		System.out.print(V_BOUND_CHAR);
        	for (int x=0;x<width;x++)
        		if (pixels[x][y])
        			System.out.print(LINE_CHAR);
        		else
        			System.out.print(EMPTY_CHAR);
        	System.out.println(V_BOUND_CHAR);
    	}
    }
    
    private void ClearPixels()
    {
    	for (Boolean[] row: pixels)
    	    Arrays.fill(row, Boolean.FALSE);
    }

	public void AddShape(Shape aShape) {
		shapes.add(aShape);
		pixels = aShape.Draw(pixels);
		Render();
	}

	public boolean IsValidShapeDimention(Point point) {
		if (point.getX() > width || point.getY() > height)
			return false;
		else
			return true;
	}
}
