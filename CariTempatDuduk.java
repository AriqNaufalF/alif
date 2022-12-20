import java.util.*;

class CariTempatDuduk {
    public Node root;

    // --------------------------------------
    public CariTempatDuduk() {
        root = null;
    }

    public int totalTempatDuduk(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = leftHeight(root);
        int rh = rightHeight(root);

        if (lh == rh) {
            return (1 << lh) - 1;
        }

        return 1 + totalTempatDuduk(root.leftChild) + totalTempatDuduk(root.rightChild);
    }

    public int leftHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.leftChild;
        }

        return height;
    }

    public int rightHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.rightChild;
        }

        return height;
    }

    // --------------------------------------
    public void memasukan(int id) {
        Node nodeBaru = new Node();
        nodeBaru.iData = id;

        if (root == null)
            root = nodeBaru;
        else {
            Node baru = root;
            Node parent;
            while (true) {
                parent = baru;
                if (id < baru.iData) { // tambah ke kiri
                    baru = baru.leftChild;
                    if (baru == null) {
                        parent.leftChild = nodeBaru;
                        return;
                    }
                } // end if ke kiri
                else {
                    baru = baru.rightChild;
                    if (baru == null) {
                        parent.rightChild = nodeBaru;
                        return;
                    }
                } // end else ke kanan

            } // end while
        } // end ketika bukan root
    } // end insert
      // ----------------------------------

    public boolean hapus(int key) {
        Node baru = root;
        Node parent = root;
        boolean cekLeftChild = true;

        while (baru.iData != key) {
            parent = baru;
            if (key < baru.iData) { // cek ke kiri
                cekLeftChild = true;
                baru = baru.leftChild;
            } else { // cek ke kanan
                cekLeftChild = false;
                baru = baru.rightChild;
            }
            if (baru == null)
                return false;
        } // end while, ditemukan node untuk dihapus.

        // --jika node yang akan dihapus tidak memiliki children
        if (baru.leftChild == null && baru.rightChild == null) {
            if (baru == root)
                root = null;
            else if (cekLeftChild)
                parent.leftChild = null; // hapus leftChild
            else
                parent.rightChild = null; // hapus rightChild
        }
        // --jika tidak memiliki rightChild, ganti dgn subtree kiri
        else if (baru.rightChild == null) {
            if (baru == root)
                root = baru.leftChild;
            else if (cekLeftChild)
                parent.leftChild = baru.leftChild;
            else
                parent.rightChild = baru.leftChild;
        }
        // --jika tdk memiliki leftChild, ganti dgn subtree kiri
        else if (baru.leftChild == null) {
            if (baru == root)
                root = baru.rightChild;
            else if (cekLeftChild)
                parent.leftChild = baru.rightChild;
            else
                parent.rightChild = baru.rightChild;
        }
        // --jika memiliki 2 children, ganti dengan successor
        else {
            // ambil successor untuk node yang akan dihapus
            Node succ = getSuccessor(baru);

            // sambung parent baru ke successor
            if (baru == root)
                root = succ;
            else if (cekLeftChild)
                parent.leftChild = succ;
            else
                parent.rightChild = succ;

            // sambung successor ke node leftChild-nya node baru
            succ.leftChild = baru.leftChild;
        } // end else 2 children
        return true;
    }// end delete

    // ----------------------------------
    private Node getSuccessor(Node hapusNode) {
        Node succParent = hapusNode;
        Node succ = hapusNode;
        Node baru = hapusNode.rightChild; // ke rightChild
        while (baru != null) { // berulang hingga leftChild ujung
            succParent = succ;
            succ = baru;
            baru = baru.leftChild; // ke leftChild
        }
        if (succ != hapusNode.rightChild) {
            // jika succ bukan rightChild, maka hubungkan
            succParent.leftChild = succ.rightChild;
            succ.rightChild = hapusNode.rightChild;
        }
        return succ;
    }

    // -------------------------------------------------
    public Node cari(int key) {
        Node cari = root;
        while (cari.iData != key) {
            if (key < cari.iData)
                cari = cari.leftChild;
            else
                cari = cari.rightChild;
            if (cari == null) // tdk ada child
                return null; // tdk ditemukan
        }
        return cari; // ditemukan
    }

    // --------------------------------------------------------
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int n = 20;
        boolean cekBarisKosong = false;
        System.out.println("..."
                + "..."
                + "..."
                + "..."
                + "..."
                + "..."
                + "...");
        while (cekBarisKosong == false) {
            Stack localStack = new Stack();
            cekBarisKosong = true;

            for (int j = 0; j <= n; j++)
                System.out.print(" ");

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null)
                        cekBarisKosong = false;
                } else {
                    System.out.print("..");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j <= n * 2 - 2; j++)
                    System.out.print(" ");
            } // end while globalStack tidak kosong
            System.out.println();
            n /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        } // end while cek Baris Kosong bernilai salah
        System.out.println(" "
                + "  "
                + "  ");

    }// end displayTree

    public void inorder(Node n) {
        if (n != null) {
            inorder(n.leftChild);
            System.out.print(n.iData + " ");
            inorder(n.rightChild);
        }
    }

    // --------------------------------------------
    public void postorder(Node n) {
        if (n != null) {
            postorder(n.leftChild);
            postorder(n.rightChild);
            System.out.print(n.iData + " ");
        }
    }

    // --------------------------------------------
    public void preorder(Node n) {
        if (n != null) {
            System.out.print(n.iData + " ");
            preorder(n.leftChild);
            preorder(n.rightChild);
        }
    }
} // end class BST
