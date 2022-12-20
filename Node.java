public class Node {

    public int iData;
    public Node leftChild;
    public Node rightChild;

    public void displayNode() { // method utk menampilkan data
        System.out.print("{");
        System.out.print(iData);
        System.out.print("}");
    }
}