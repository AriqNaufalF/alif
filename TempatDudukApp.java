
import java.util.Scanner;

class TempatDudukApp {
    public static void main(String[] args) {
        int nilai;
        Scanner scn = new Scanner(System.in);
        CariTempatDuduk tree = new CariTempatDuduk();

        tree.memasukan(10);
        tree.memasukan(8);
        tree.memasukan(5);
        tree.memasukan(12);
        tree.memasukan(20);
        tree.memasukan(21);
        tree.memasukan(22);
        tree.memasukan(24);
        tree.memasukan(7);
        tree.memasukan(6);
        tree.memasukan(3);
        tree.memasukan(25);
        tree.memasukan(11);
        tree.memasukan(2);
        tree.memasukan(1);

        boolean stop = true;
        while (stop) {
            System.out.println("Menu:\n1. Tambah \n2. Hapus \n3. Cari \n4. Tampil \n5. Keluar ");
            System.out.print("Masukkan pilihan: ");
            int pilih = scn.nextInt();
            switch (pilih) {
                case 1:
                    System.out.println("----- Menu Tambah -----");
                    System.out.print("Masukkan nilai int: ");
                    nilai = scn.nextInt();
                    tree.memasukan(nilai);
                    break;
                case 2:
                    System.out.println("----- Menu Hapus -----");
                    System.out.print("Masukkan nilai yang akan dihapus: ");
                    nilai = scn.nextInt();
                    boolean menghapus = tree.hapus(nilai);
                    if (menghapus)
                        System.out.print("Menghapus " + nilai + "\n");
                    else
                        System.out.print("Tidak dapat menghapus " + nilai + "\n");
                    break;
                case 3:
                    System.out.println("----- Menu Cari -----");
                    System.out.print("Masukkan nilai yang akan dicari: ");
                    nilai = scn.nextInt();
                    Node ditemukan = tree.cari(nilai);
                    if (ditemukan != null) {
                        System.out.print("Ditemukan ");
                        ditemukan.displayNode();
                        System.out.println();
                    } else
                        System.out.print("Tidak ditemukan " + nilai + "\n");
                    break;
                case 4:
                    System.out.println("----- Menu Tampil -----");
                    System.out.println(tree.totalTempatDuduk(tree.root));
                    System.out.println(" ");
                    tree.displayTree();
                    System.out.println(" ");
                    System.out.println("----- Inorder -----");
                    tree.inorder(tree.root);
                    System.out.println(" ");
                    System.out.println("----- Postorder -----");
                    tree.postorder(tree.root);
                    System.out.println(" ");
                    System.out.println("----- Preorder -----");
                    tree.preorder(tree.root);
                    System.out.println(" ");
                    System.out.println(" ");
                    break;
                case 5:
                    stop = false;
                    break;
            }// end switch
        } // end while
    }// end main()
}// end class TreeApp
