/**
 * 1105. Filling Bookcase Shelves
 *
 * You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. 
 * You are also given an integer shelfWidth.
 *
 * We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
 *
 * We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth,
 * then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum 
 * height of the books we just put down. We repeat this process until there are no more books to place.
 *
 * Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
 *
 * For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, 
 * the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 * Example 1:
 *
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 *
 * Example 2:
 * Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * Output: 4
 *
 * Constraints:
 * 1 <= books.length <= 1000
 * 1 <= thicknessi <= shelfWidth <= 1000
 * 1 <= heighti <= 1000
 */

import java.util.Arrays;
public class FillingBookcaseShelves {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] dp = new int[books.length];
        Arrays.fill(dp, -1);
        return solve(0, books, shelfWidth, dp);
    }

    private int solve(int index, int[][] books, int shelfWidth, int[] dp) {
        if (index >= books.length) return 0;

        if (dp[index] != -1) return dp[index];
        int width = 0, maxH = 0;
        int ans = Integer.MAX_VALUE;
        for (int j = index; j < books.length; j++) {
            width += books[j][0];
            if (width > shelfWidth) break;
            maxH = Math.max(maxH, books[j][1]);
            ans = Math.min(maxH + solve(j + 1, books, shelfWidth, dp), ans);
        }

        return dp[index] = ans;
    }

    public static void main(String[] args) {
        FillingBookcaseShelves solution = new FillingBookcaseShelves();
        
        int[][] books1 = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        int shelfWidth1 = 4;
        System.out.println("Minimum height of bookshelf: " + solution.minHeightShelves(books1, shelfWidth1)); // Output: 6

        int[][] books2 = {{1, 3}, {2, 4}, {3, 2}};
        int shelfWidth2 = 6;
        System.out.println("Minimum height of bookshelf: " + solution.minHeightShelves(books2, shelfWidth2)); // Output: 4
    }
}

