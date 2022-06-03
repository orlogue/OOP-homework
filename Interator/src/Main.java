public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(9);
        arr.add(8);
        arr.add(4);
        arr.put(403, 2);
        arr.put(200, 2);
        arr.remove(2);
        arr.print();
        System.out.println("Index: " + arr.find(4));
        System.out.println("Got: " + arr.get(1) + "\n");


        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(0);
        list.add(7);
        list.add(7);
        list.put(502, 3);
        list.remove(0);
        list.print();
        System.out.println("Index: " + list.find(4));
        System.out.println("Got: " + list.get(1) + "\n");

        for (Integer item : arr) {
            System.out.println(item);
        }
        System.out.println();
        for (Integer item : list) {
            System.out.println(item);
        }
        list.remove(1);
    }
}
