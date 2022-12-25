import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    private Random random = new Random();
    private int max;
    private int min;

    public Randoms(int min, int max) {
        this.max = max;
        this.min = min - 1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return random.nextInt(max - min) + min + 1;
                //return (int)(Math.random()*(max-min)+min+1);
            }
        };
    }
}
