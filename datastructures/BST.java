package datastructures;

import java.util.ArrayList;
import java.util.List;

public class BST {

     private static class Node {
          int value;
          Node left, right;

          Node(int value) {
               this.value = value;
          }
     }

     private Node root;
     private int size;

     public void add(int value) {
          root = add(root, value);
     }

     private Node add(Node node, int value) {
          if (node == null) {
               size++;
               return new Node(value);
          }
          if (value < node.value) {
               node.left = add(node.left, value);
          } else if (value > node.value) {
               node.right = add(node.right, value);
          }
          // duplicates are ignored
          return node;
     }

     public int size() {
          return size;
     }

     public List<Integer> inorder() {
          List<Integer> out = new ArrayList<>();
          inorder(root, out);
          return out;
     }

     public List<Integer> preorder() {
          List<Integer> out = new ArrayList<>();
          preorder(root, out);
          return out;
     }

     public List<Integer> postorder() {
          List<Integer> out = new ArrayList<>();
          postorder(root, out);
          return out;
     }

     private void inorder(Node node, List<Integer> out) {
          if (node == null) return;
          inorder(node.left, out);
          out.add(node.value);
          inorder(node.right, out);
     }

     private void preorder(Node node, List<Integer> out) {
          if (node == null) return;
          out.add(node.value);
          preorder(node.left, out);
          preorder(node.right, out);
     }

     private void postorder(Node node, List<Integer> out) {
          if (node == null) return;
          postorder(node.left, out);
          postorder(node.right, out);
          out.add(node.value);
     }
}