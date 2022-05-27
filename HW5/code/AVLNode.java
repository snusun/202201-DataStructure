import java.util.LinkedList;

public class AVLNode<K extends Comparable<K>, V extends Comparable<V>> {
    public K key;
    public LinkedList<V> item;
    public AVLNode<K, V> left, right;
    public int height;

    public AVLNode(K key){
        this.key = key;
        this.item = new LinkedList<>();
        this.left = AVLTree.NIL;
        this.right = AVLTree.NIL;
        this.height = 1;
    }

    public AVLNode(K key, LinkedList<V> item, AVLNode<K, V> leftChild, AVLNode<K, V> rightChild){
        this.key = key;
        this.item = item;
        this.left = leftChild;
        this.right = rightChild;
        height = 1;
    }

    public AVLNode(K key, LinkedList<V> item, AVLNode<K, V> leftChild, AVLNode<K, V> rightChild, int height){
        this.key = key;
        this.item = item;
        this.left = leftChild;
        this.right = rightChild;
        this.height = height;
    }

    public void insertValue(V value){
        this.item.add(value);
    }
}
