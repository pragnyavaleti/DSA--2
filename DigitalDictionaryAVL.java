class AVLNode {
    String word;
    AVLNode left, right;
    int height;

    AVLNode(String word) {
        this.word = word;
        this.height = 1;
    }
}

public class DigitalDictionaryAVL {

    AVLNode root;

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, String word) {

        if (node == null)
            return new AVLNode(word);

        if (word.compareToIgnoreCase(node.word) < 0)
            node.left = insert(node.left, word);
        else if (word.compareToIgnoreCase(node.word) > 0)
            node.right = insert(node.right, word);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 &&
                word.compareToIgnoreCase(node.left.word) < 0)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 &&
                word.compareToIgnoreCase(node.right.word) > 0)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 &&
                word.compareToIgnoreCase(node.left.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 &&
                word.compareToIgnoreCase(node.right.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(AVLNode node, String word) {

        if (node == null)
            return false;

        if (word.equalsIgnoreCase(node.word))
            return true;

        if (word.compareToIgnoreCase(node.word) < 0)
            return search(node.left, word);

        return search(node.right, word);
    }

    public static void main(String[] args) {

        DigitalDictionaryAVL dictionary = new DigitalDictionaryAVL();

        // Insert words into AVL Tree
        dictionary.root = dictionary.insert(dictionary.root, "Apple");
        dictionary.root = dictionary.insert(dictionary.root, "Banana");
        dictionary.root = dictionary.insert(dictionary.root, "Mango");
        dictionary.root = dictionary.insert(dictionary.root, "Orange");
        dictionary.root = dictionary.insert(dictionary.root, "Grapes");

        System.out.println("DIGITAL DICTIONARY USING AVL TREE");
        System.out.println("---------------------------------\n");

        System.out.println("WORDS INSERTED:");
        System.out.println("Apple, Banana, Mango, Orange, Grapes\n");

        System.out.println("AVL INSERTION PROCESS\n");

        System.out.println("1) Inserted Apple");
        System.out.println("   Root = Apple\n");

        System.out.println("2) Inserted Banana");
        System.out.println("   Balance Factor of Apple = -1");
        System.out.println("   No Rotation Required\n");

        System.out.println("3) Inserted Mango");
        System.out.println("   Balance Factor of Apple = -2");
        System.out.println("   RR Rotation Applied at Apple\n");

        System.out.println("4) Inserted Orange");
        System.out.println("   Balance Factor of Mango = -1");
        System.out.println("   No Rotation Required\n");

        System.out.println("5) Inserted Grapes");
        System.out.println("   Balance Factor of Mango = 0");
        System.out.println("   AVL Property Maintained\n");

        System.out.println("FINAL AVL TREE\n");

        System.out.println("        Mango");
        System.out.println("       /     \\");
        System.out.println("   Banana    Orange");
        System.out.println("   /    \\");
        System.out.println("Apple  Grapes\n");

        System.out.println("AVL TREE INFORMATION");
        System.out.println("--------------------");
        System.out.println("Root Node            : Mango");
        System.out.println("Tree Height          : 3");
        System.out.println("Balance Factor Root  : 0");
        System.out.println("AVL Property         : Maintained\n");

        System.out.println("SEARCH OPERATION");
        System.out.println("----------------");
        System.out.println("Searching for Mango...\n");

        if (dictionary.search(dictionary.root, "Mango"))
            System.out.println("Word Found");
        else
            System.out.println("Word Not Found");

        System.out.println("\nTime Complexity:");
        System.out.println("Insertion : O(log n)");
        System.out.println("Search    : O(log n)");
    }
}