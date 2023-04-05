import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Box implements Comparable <Box> {

    public int Height=0, Width=0, Length=0;

    public int getVolume() {
        return (Length * Width * Height);
    }

    public int[] GetAsArray()
    {
        return new int[]{Height,Width,Length};
    }
    public Box(){};
    public Box(int h, int w, int l)
    {
        Height = h;
        Width = w;
        Length = l;
    }

    @Override
    public int compareTo(Box other) {
        int result = Integer.compare(getVolume(), other.getVolume());

        if (result == 0) {
            result = Integer.compare(Length, other.Length);
            if (result == 0) {
                result = Integer.compare(Width, other.Width);
                // в обратном порядке
                result *= -1;
            }
        }

        return result;
    }

    public static List<Box> GetFromArray(int[][] array)
    {
        List<Box> res = new ArrayList<Box>();

        for (int i = 0; i < array.length;i++)
        {
            res.add(new Box(array[i][0],array[i][1],array[i][2]));
        }

        return res;
    }
}
