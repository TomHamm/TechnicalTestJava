import demo.CustomNumberEntity;
import demo.FastestComparator;

/**
 * Get an int and CustomNumberEntity values as input and compare them as a int numbers
 * Time needed to make the comparison will be between 5 and 10 seconds
 */
public class FastestComparatorThread extends Thread {

    private int valueToFind;
    private CustomNumberEntity customNumberEntity;
    private FastestComparator fastComparator = new FastestComparator();
    private int result = 1;

    public FastestComparatorThread(int valueToFind, CustomNumberEntity customNumberEntity) {
        this.valueToFind = valueToFind;
        this.customNumberEntity = customNumberEntity;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            result = fastComparator.compare(valueToFind, customNumberEntity);
        } catch (NumberFormatException e) {}
    }
}