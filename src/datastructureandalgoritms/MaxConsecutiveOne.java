package datastructureandalgoritms;

/**
 * Given a binary array {@code nums}, return the maximum number of consecutive
 * {@code 1}s in the array.
 *
 * <p>Examples:
 * <ul>
 *   <li>{@code nums = [1, 1, 0, 1, 1, 1]} returns {@code 3}. The two runs
 *       of {@code 1}s have lengths {@code 2} and {@code 3}.</li>
 *   <li>{@code nums = [1, 0, 1, 1, 0, 1]} returns {@code 2}.</li>
 * </ul>
 */
public class MaxConsecutiveOne {

    public int findMaxConsecutiveOnes(int[] nums) {
        int accountant = 0;
        int maximum = 0;

        for (int num : nums) {

            if (num == 1) {
                accountant++;
                if (accountant > maximum) {
                    maximum = accountant;
                }
            } else {
                accountant = 0;
            }
        }
        return maximum;
    }

    

    static void main() {
        MaxConsecutiveOne solution = new MaxConsecutiveOne();

        System.out.println(solution.findMaxConsecutiveOnes(
                new int[]{1, 1, 0, 1, 1, 1})); // Expected: 3
        System.out.println(solution.findMaxConsecutiveOnes(
                new int[]{1, 0, 1, 1, 0, 1})); // Expected: 2
    }
}
