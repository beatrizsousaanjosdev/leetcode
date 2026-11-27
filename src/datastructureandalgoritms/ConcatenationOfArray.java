package datastructureandalgoritms;

import java.util.Arrays;

/**
 * Given an integer array {@code nums} of length {@code n}, return an array
 * {@code ans} of length {@code 2n} formed by concatenating two copies of
 * {@code nums}. For every zero-based index {@code i} with {@code 0 <= i < n},
 * both {@code ans[i]} and {@code ans[i + n]} equal {@code nums[i]}.
 *
 * <p>Examples:
 * <ul>
 *   <li>{@code nums = [1, 2, 1]} returns {@code [1, 2, 1, 1, 2, 1]}.</li>
 *   <li>{@code nums = [1, 3, 2, 1]} returns {@code [1, 3, 2, 1, 1, 3, 2, 1]}.</li>
 * </ul>
 */
public class ConcatenationOfArray {

    public int[] getConcatenation(int[] nums) {
        int[] output = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            output[i] = nums[i];
            output[i + nums.length] = nums[i];
        }

        return output;

    }

    static void main() {
        ConcatenationOfArray solution = new ConcatenationOfArray();

        System.out.println(Arrays.toString(solution.getConcatenation(
                new int[]{1, 2, 1})));

        System.out.println(Arrays.toString(solution.getConcatenation(
                new int[]{1,3,2,1})));

    }
}
