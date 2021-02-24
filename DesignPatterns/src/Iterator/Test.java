package Iterator;

/**
 * @author:李罡毛
 * @date:2021/2/23 15:52
 */
public class Test {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList();
        arrayList.add("aaa").add("bbb").add("ccc").add("123").add("2").add("").add("12").add("")
        .add("").add("").add("").add("123");
        System.out.println(arrayList.toString());
        try {
            System.out.println(arrayList.get(3));
            System.out.println(arrayList.get(20));
        }catch (IndexOutOfBoundsException indexOutOfBoundsException){
            System.out.println("数组下标越界");
        }

        List<Integer> linkedIntList = new LinkedList();
        linkedIntList.add(10).add(20).add(30).add(40).add(50);
        try{
            System.out.println(linkedIntList.get(5));

        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(linkedIntList.toString());
        System.out.println(linkedIntList.size());
        Integer integer = linkedIntList.get(0);

        System.out.println("------------------");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

        System.out.println("------------------");
        Iterator<Integer> iterator1 = linkedIntList.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
    }
}
