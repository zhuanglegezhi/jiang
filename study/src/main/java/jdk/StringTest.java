package jdk;

/**
 * Created by zz on 2022/11/23.
 */
public class StringTest {

    String str = new String("good");//str指向堆中good   因为是显式new，字符串常量池也有good
    int anInt = 1;
    char[] ch = {'t', 'e', 's', 't'};

    public void change(String str, char ch[], int i) {
        System.out.println("======" + str);//good
        //String不可变性，栈上的str（String类型没有加this）变为 "test ok"，即是字符串常量池新增一个 "test ok"，str指向符串常量池（
        str = "test ok";
        System.out.println("str======" + str);//str======test ok
        System.out.println("this.str======" + this.str);//this.str======good
        ch[0] = 'b';
        anInt = 2;
    }

    public void change2(String str, char ch[], int i) {
        System.out.println("======" + str);//======good
        //增加this明确是对象的str
        this.str = "test ok";
        System.out.println("str======" + str);//str======good
        System.out.println("this.str======" + this.str);//this.str======test ok
        ch[0] = 'b';
        anInt = 2;
    }

    public void change3(String a, char ch[], int i) {
        System.out.println("======" + str);//======good
        //栈上入参为a，str为this的
        str = "test ok";
        System.out.println("======" + str);//======test ok
        ch[0] = 'b';
        this.anInt = 2;
    }

    public static void main(String[] args) {
        /**
         * ======good
         * str======test ok
         * this.str======good
         * good
         * best
         * 2
         */

        StringTest test1 = new StringTest();
        test1.change(test1.str, test1.ch, test1.anInt);
        System.out.println(test1.str);//good
        System.out.println(test1.ch);//best
        System.out.println(test1.anInt);//2

        /**
         * ======good
         * str======good
         * this.str======test ok
         * test ok
         * best
         * 2
         */
        StringTest test2 = new StringTest();
        test2.change2(test2.str, test2.ch, test2.anInt);
        System.out.println(test2.str);//test ok
        System.out.println(test2.ch);//best
        System.out.println(test2.anInt);//2
        /**
         * ======good
         * ======test ok
         * test ok
         * best
         * 2
         */
        StringTest test3 = new StringTest();
        test3.change3(test3.str, test3.ch, test3.anInt);
        System.out.println(test3.str);//test ok
        System.out.println(test3.ch);//best
        System.out.println(test3.anInt);//2

    }

}
