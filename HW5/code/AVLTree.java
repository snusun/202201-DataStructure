import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<K extends Comparable<K>, V extends Comparable<V>> {
    private AVLNode<K, V> root;
    static final AVLNode NIL = new AVLNode(null, null, null, null, 0);

    public AVLTree() {
        root = NIL;
    }

    public AVLNode<K, V> getRoot() {
        return root;
    }

    public AVLNode<K, V> search(K key) {
        return searchItem(root, key);
    }

    private AVLNode<K, V> searchItem(AVLNode<K, V> tNode, K key) {
        if (tNode == NIL) return NIL;
        else if (key.compareTo(tNode.key) == 0) return tNode;
        else if (key.compareTo(tNode.key) < 0) return searchItem(tNode.left, key);
        else return searchItem(tNode.right, key);
    }

    public void insert(K key, V value) {
        root = insertItem(root, key, value);
    }

    private AVLNode<K, V> insertItem(AVLNode<K, V> tNode, K key, V value) {
        if (tNode == NIL) {
            tNode = new AVLNode<>(key);
            tNode.insertValue(value);
        } else if (key.compareTo(tNode.key) == 0) {
            tNode.insertValue(value);
        } else if (key.compareTo(tNode.key) < 0) {
            tNode.left = insertItem(tNode.left, key, value);
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            int type = needBalance(tNode);
            System.out.println("<0 insertItem: " + type);
            if (type != NO_NEED) {
                tNode = balanceAVL(tNode, type);
            }
        } else {
            tNode.right = insertItem(tNode.right, key, value);
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            int type = needBalance(tNode);
            System.out.println(">0 insertItem: " + type);
            if (type != NO_NEED) {
                tNode = balanceAVL(tNode, type);
            }
        }
        return tNode;
    }


    private AVLNode<K, V> balanceAVL(AVLNode<K, V> tNode, int type) {
        System.out.println("type: " + type);
        AVLNode<K, V> returnNode = NIL;
        switch (type) {
            case LL:
                returnNode = rightRotate(tNode);
                break;
            case LR:
                tNode.left = leftRotate(tNode.left);
                returnNode = rightRotate(tNode);
                break;
            case RR:
                returnNode = leftRotate(tNode);
                break;
            case RL:
                tNode.right = rightRotate(tNode.right);
                returnNode = leftRotate(tNode);
                break;
            default:
                System.out.println("Impossible type!");
                break;
        }
        return returnNode;
    }

    private AVLNode<K, V> leftRotate(AVLNode<K, V> t) {
        System.out.println("left Rotate");
        AVLNode<K, V> RChild = t.right;
        if (RChild == NIL) {
            System.out.println(t.key + "'s RChild shouldn't be NIL!");
        } else {
            AVLNode<K, V> RLChild = RChild.left;
            RChild.left = t;
            t.right = RLChild;
            t.height = 1 + Math.max(t.left.height, t.right.height);
            RChild.height = 1 + Math.max(RChild.left.height, RChild.right.height);
        }
        return RChild;
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> t) {
        System.out.println("right Rotate");
        AVLNode<K, V> LChild = t.left;
        if (LChild == NIL) {
            System.out.println(t.key + "'s LChild shouldn't be NIL!");
        } else {
            AVLNode<K, V> LRChild = LChild.right;
            LChild.right = t;
            t.left = LRChild;
            t.height = 1 + Math.max(t.left.height, t.right.height);
            LChild.height = 1 + Math.max(LChild.left.height, LChild.right.height);
        }
        return LChild;
    }

    private final int LL = 1, LR = 2, RR = 3, RL = 4, NO_NEED = 0, ILLEGAL = -1;

    private int needBalance(AVLNode<K, V> t) {
        int type = ILLEGAL;
        System.out.println("left height " + t.left.height + " right height " + t.right.height);

        if (t.left.height + 2 <= t.right.height) {
            System.out.println(t.right.left.height + " " + t.right.right.height);
            if ((t.right.left.height) <= t.right.right.height) type = RR;
            else type = RL;
        } else if ((t.left.height) >= t.right.height + 2) {
            System.out.println(t.left.left.height + " " + t.left.right.height);
            if ((t.left.left.height) >= t.left.right.height) type = LL;
            else type = LR;
        } else type = NO_NEED;
        return type;
    }

    void preOrderTraversal(AVLNode<K, V> node) {
        if (node != NIL) {
            System.out.println(node.key);
            preOrderTraversal(node.left);
            //System.out.println(node.key);
            preOrderTraversal(node.right);
            //visit(node); postorder
        }
    }

    public void BFS() {
        Queue<AVLNode<K, V>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            AVLNode<K, V> node = queue.poll();
            System.out.println(node.key);
            if (node.left != NIL) {
                queue.add(node.left);
            }
            if (node.right != NIL) {
                queue.add(node.right);
            }
        }

    }

    public boolean isEmpty() {
        return root == NIL;
    }

    public void clear() {
        root = NIL;
    }
}
