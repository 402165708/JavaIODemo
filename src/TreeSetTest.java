import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {

        String a1 = "100.69.84.18    100.69.238.11   100.90.207.22   100.90.207.22   06/Ju";
        String[] arr = a1.split("   ");

        //带自定义排序规则的TreeSet  
        TreeSet set = new TreeSet(new MyComparator());

        Person p1 = new Person(10);
        Person p2 = new Person(20);
        Person p3 = new Person(30);
        Person p4 = new Person(40);
        Person p5 = new Person(50);

        set.add(p3);
        set.add(p1);
        set.add(p2);
        set.add(p4);
        set.add(p5);

        int a  = Integer.parseInt("356");
        System.out.println(set);
    }
}

/**
 * 自定义类，放入TreeSet希望根据Person的score进行排序 
 * @author Administrator
 *
 */
class Person {
    int score;

    Person(int score){
        this.score = score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}

 class MyComparator implements Comparator {

    /**
     * 比较的规则，由自己定义,这里是根据Person的score的属性进行比较的 
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Object o1, Object o2) {
        Person p1 = (Person)o1;
        Person p2 = (Person)o2;

        return (p1.score - p2.score);
    }
}  
