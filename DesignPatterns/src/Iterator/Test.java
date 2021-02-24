package Iterator;

/**
 * @author:李罡毛
 * @date:2021/2/23 15:52
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("aaa").add("bbb").add("ccc").add("123").add("2").add("").add("12").add("")
        .add("").add("").add("").add("");
        System.out.println(list.toString());
        try {
            System.out.println(list.get(3));
            System.out.println(list.get(20));
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

    }
}
