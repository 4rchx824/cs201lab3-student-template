import java.util.Arrays;
import java.util.List;

import datastructures.BST;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }

     // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          int[][] t = { parse(traversal1), parse(traversal2), parse(traversal3) };

          // Each permutation p: t[p[0]] is inorder, t[p[1]] is preorder, t[p[2]] is
          // postorder
          int[][] perms = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };

          for (int[] p : perms) {
               int[] in = t[p[0]];
               int[] pre = t[p[1]];
               int[] post = t[p[2]];

               // Inserting a BST's preorder sequence rebuilds exactly that BST
               BST tree = new BST();
               for (int v : pre) {
                    tree.add(v);
               }

               // Regenerate all three traversals from the rebuilt tree and compare
               if (matches(tree.inorder(), in) && matches(tree.preorder(), pre)
                         && matches(tree.postorder(), post)) {
                    String[] label = new String[3];
                    label[p[0]] = "Inorder";
                    label[p[1]] = "Preorder";
                    label[p[2]] = "Postorder";
                    return "Traversal 1 - " + label[0] + ", Traversal 2 - " + label[1]
                              + ", Traversal 3 - " + label[2];
               }
          }
          return "Invalid traversals";
     }

     private static boolean matches(List<Integer> list, int[] arr) {
          if (list.size() != arr.length)
               return false;
          for (int i = 0; i < arr.length; i++) {
               if (list.get(i) != arr[i])
                    return false;
          }
          return true;
     }

     public static int[] parse(String s) {
          String[] parts = s.split("-");
          return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
     }
}