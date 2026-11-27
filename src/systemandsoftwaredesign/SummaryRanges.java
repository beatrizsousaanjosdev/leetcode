package systemandsoftwaredesign;

import java.util.Arrays;
import java.util.TreeMap;

public class SummaryRanges {

    private final TreeMap<Integer, int[]> intervals = new TreeMap<>();

    public SummaryRanges() {

    }

    public void addNum(int value) {
        var leftEntry = intervals.floorEntry(value);
        int[] left = leftEntry == null ? null : leftEntry.getValue();

        if (left != null && value <= left[1]) {
            return; // The value already is an in interval
        }

        var rightEntry = intervals.higherEntry(value);
        int[] right = rightEntry == null ? null : rightEntry.getValue();

        boolean touchesLeft = left != null && left[1] + 1 == value;
        boolean touchesRight = right != null && right[0] - 1 == value;

        if (touchesLeft && touchesRight) {
            left[1] = right[1];
            intervals.remove(right[0]);
        } else if (touchesLeft) {
            left[1] = value;
        } else if (touchesRight) {
            intervals.remove(right[0]);
            right[0] = value;
            intervals.put(value, right);
        } else {
            intervals.put(value, new int[]{value, value});
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[intervals.size()][];
        int index = 0;

        for (int[] interval : intervals.values()) {
            result[index] = interval.clone();
            index++;
        }

        return result;
    }

    static void main() {
        SummaryRanges summaryRanges = new SummaryRanges();
        int[] values = {1, 3, 7, 2, 6};
        int[][][] expected = {
                {{1, 1}},
                {{1, 1}, {3, 3}},
                {{1, 1}, {3, 3}, {7, 7}},
                {{1, 3}, {7, 7}},
                {{1, 3}, {6, 7}}
        };

        for (int i = 0; i < values.length; i++) {
            summaryRanges.addNum(values[i]);
            int[][] actual = summaryRanges.getIntervals();

            if (!Arrays.deepEquals(expected[i], actual)) {
                throw new AssertionError("After adding " + values[i]
                        + ", expected " + Arrays.deepToString(expected[i])
                        + ", got " + Arrays.deepToString(actual));
            }

            System.out.println(Arrays.deepToString(actual));
        }
    }
}
