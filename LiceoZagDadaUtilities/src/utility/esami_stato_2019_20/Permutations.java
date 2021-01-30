package utility.esami_stato_2019_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public <T> List<List<T>> permute(List<T> arr) {
        List<List<T>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), arr);
        return list;
    }

    private <T> void permuteHelper(List<List<T>> list, List<T> resultList, List<T> arr) {

        // Base case
        if (resultList.size() == arr.size()) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < arr.size(); i++) {

                if (resultList.contains(arr.get(i))) {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(arr.get(i));
                // Explore
                permuteHelper(list, resultList, arr);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }

    }


    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Permutations p =new Permutations();
        List<List<Integer>> permute = p.permute(a);
        for (List<Integer> x : permute) {
            System.out.println(x);
        }
    }
}
