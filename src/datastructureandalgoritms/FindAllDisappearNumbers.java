package datastructureandalgoritms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array {@code nums} of {@code n} integers with each value in
 * {@code [1, n]}, return all integers in {@code [1, n]} that do not appear
 * in {@code nums}.
 *
 * <p>For example, {@code [4, 3, 2, 7, 8, 2, 3, 1]} produces {@code [5, 6]}.
 */
public class FindAllDisappearNumbers {


    public List<Integer> findDisappearedNumbers(int[] nums) {

        boolean[] seen = new boolean[nums.length + 1];

        for (int num : nums) {
            seen[num] = true;
        }

        List<Integer> missing = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            if(!seen[i]){
                missing.add(i);
            }
        }
        return missing;
    }

    static void main() {
        FindAllDisappearNumbers solution = new FindAllDisappearNumbers();

        System.out.println(solution.findDisappearedNumbers(
                new int[]{4, 3, 2, 7, 8, 2, 3, 1}));

        System.out.println(solution.findDisappearedNumbers(
                new int[]{1, 1}));

        System.out.println(solution.findDisappearedNumbers(
                new int[]{3, 1, 2}));
    }
}
