import java.security.InvalidParameterException;
import java.util.Arrays;

public class Line implements Shape {
    private int x1;
    private int y1;	
    private int x2;
    private int y2;	

    
	public Line(int i, int j, int k, int l) {

			if ((i == k || j ==l) && i > 0 && j>0 && k>0 && l>0)
			{
				x1=i;
				y1=j;
				x2=k;
				y2=l;
			}
			else
				throw new InvalidParameterException("The parameters are not valid to create a line");
			
	}

	
	
	public Boolean[][] Draw(Boolean[][] pixels) {


		for (int i=x1;i<=x2;i++)
		{
			pixels[i][y1] = Boolean.TRUE;
			pixels[i][y2] = Boolean.TRUE;
		}
		for (int j=y1;j<=y2;j++)
		{
			pixels[x1][j] = Boolean.TRUE;
			pixels[x2][j] = Boolean.TRUE;
		}

		return pixels;
	}



}
