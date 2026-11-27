package datastructureandalgoritms;

import java.util.Arrays;

/**
 * Given an array {@code nums} of {@code 2n} elements in the form
 * {@code [x1, x2, ..., xn, y1, y2, ..., yn]}, return the array in the form
 * {@code [x1, y1, x2, y2, ..., xn, yn]}.
 *
 * <p>Examples:
 * <ul>
 *   <li>{@code nums = [2, 5, 1, 3, 4, 7], n = 3} returns
 *       {@code [2, 3, 5, 4, 1, 7]} by interleaving {@code [2, 5, 1]}
 *       and {@code [3, 4, 7]}.</li>
 *   <li>{@code nums = [1, 2, 3, 4, 4, 3, 2, 1], n = 4} returns
 *       {@code [1, 4, 2, 3, 3, 2, 4, 1]}.</li>
 *   <li>{@code nums = [1, 1, 2, 2], n = 2} returns
 *       {@code [1, 2, 1, 2]}.</li>
 * </ul>
 */
public class ShuffleTheArray {

    public int[] shuffle(int[] nums, int n) {
        int[] output = new int[nums.length];

        for(int i = 0; i < n; i++) {
            output[i * 2] = nums[i];
            output[i * 2 + 1] = nums[i + n];
        }

        return output;
    }

    static void main() {

        ShuffleTheArray solution = new ShuffleTheArray();

        var sol1 = solution.shuffle(new int[]{2,5,1,3,4,7}, 3);
        var sol2 = solution.shuffle(new int[]{1,2,3,4,4,3,2,1}, 4);

        System.out.println(Arrays.toString(sol1));
        System.out.println(Arrays.toString(sol2));

    }
}
