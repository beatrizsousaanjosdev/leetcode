package datastructureandalgoritms;

/**
 * Given an array {@code nums}, count how many elements are smaller than
 * {@code nums[i]} for each index {@code i}. Count every index {@code j}
 * where {@code j != i} and {@code nums[j] < nums[i]}.
 * Return the counts in the same order as the input.
 *
 * <p>For example, {@code [8, 1, 2, 2, 3]} produces {@code [4, 0, 1, 1, 3]}.
 * The array contains 2 to 500 elements, each between 0 and 100 inclusive.
 */
public class SmallerNumbersThanCurrent {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] frequency = new int[101];

        for (int num : nums) {
            frequency[num]++;
        }

        int[] smallerCounts = new int[101];

        for (int x = 1; x < smallerCounts.length; x++) {
            smallerCounts[x] = smallerCounts[x - 1] + frequency[x - 1];
        }

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = smallerCounts[nums[i]];
        }

        return result;
    }

    static void main() {
        SmallerNumbersThanCurrent solution = new SmallerNumbersThanCurrent();

        int[] nums = {8, 1, 2, 2, 3};
        int[] result = solution.smallerNumbersThanCurrent(nums);

        System.out.println(java.util.Arrays.toString(result));

    }
}
