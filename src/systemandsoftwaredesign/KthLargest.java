package systemandsoftwaredesign;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * A university admissions office tracks the {@code k}th highest test score
 * as applicants submit new scores. The rank is determined by sorting all
 * scores in descending order, counting repeated scores separately.
 *
 * <ul>
 *   <li>{@code KthLargest(int k, int[] nums)} initializes the rank and the
 *       initial scores.</li>
 *   <li>{@code add(int val)} adds a score and returns the {@code k}th highest
 *       score after the addition.</li>
 * </ul>
 *
 * <p>Examples:
 * <ul>
 *   <li>With {@code k = 3} and {@code nums = [4, 5, 8, 2]}, adding
 *       {@code [3, 5, 10, 9, 4]} in order returns {@code [4, 5, 5, 8, 8]}.</li>
 *   <li>With {@code k = 4} and {@code nums = [7, 7, 7, 7, 8, 3]}, adding
 *       {@code [2, 10, 9, 9]} in order returns {@code [7, 7, 7, 8]}.</li>
 * </ul>
 */
public class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        heap.offer(val);

        if(heap.size() > k) {
            heap.poll();
        }

        return heap.peek();
    }

    static void main() {
        KthLargest first = new KthLargest(3, new int[]{4, 5, 8, 2});
        int[] firstResults = {first.add(3), first.add(5), first.add(10), first.add(9), first.add(4)};
        int[] firstExpected = {4, 5, 5, 8, 8};
        if (!Arrays.equals(firstExpected, firstResults)) {
            throw new AssertionError("Example 1: expected " + Arrays.toString(firstExpected)
                    + ", got " + Arrays.toString(firstResults));
        }
        System.out.println(Arrays.toString(firstResults));

        KthLargest second = new KthLargest(4, new int[]{7, 7, 7, 7, 8, 3});
        int[] secondResults = {second.add(2), second.add(10), second.add(9), second.add(9)};
        int[] secondExpected = {7, 7, 7, 8};
        if (!Arrays.equals(secondExpected, secondResults)) {
            throw new AssertionError("Example 2: expected " + Arrays.toString(secondExpected)
                    + ", got " + Arrays.toString(secondResults));
        }
        System.out.println(Arrays.toString(secondResults));
    }
}

