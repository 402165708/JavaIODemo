package aqiyi;

import java.util.TreeMap;

/**
 * Created by chenzhitao on 2018/9/15
 */
public class TreeMapDemo
{
    public static void main(String[] args)
    {
        TreeMap<Hero, String> map = new TreeMap<Hero, String>();  //声明Map对象；
        map.put(new Hero("a", 22), "1");//增加元素；
        map.put(new Hero("c", 34), "2");
        map.put(new Hero("b", 24), "3");
        System.out.println(map); //输出内容；
        System.out.println(map.firstEntry());  //输出一个元素；
    }
}


class Hero implements Comparable<Hero>  //实现Comparable接口；
{
    String name;
    Integer age;

    public Hero(String name, Integer age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()   //重写toString方法；
    {
        return "name=" + this.name + "  age=" + this.age + "  String";
    }


    @Override
    public int compareTo(Hero h) //重写compareTo方法；
    {
        int num = 0;
        return  h.name.compareTo(this.name);

    }

}
