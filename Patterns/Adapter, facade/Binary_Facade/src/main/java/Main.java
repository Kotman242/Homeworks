public class Main {
    public static void main(String[] args) {
        String a = Integer.toBinaryString(7);
        String b = Integer.toBinaryString(9);
        BinOps bins = new BinOps();
        System.out.println(Integer.toBinaryString(16).equals(bins.sum(a, b)));
        System.out.println(Integer.toBinaryString(63).equals(bins.mult(a, b)));
    }
}