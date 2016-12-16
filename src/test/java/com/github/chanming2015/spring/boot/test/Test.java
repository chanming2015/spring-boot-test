package com.github.chanming2015.spring.boot.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Description: Create Date:2016年12月16日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class Test
{
    public static void main(String[] args)
    {
        Predicate<File> predicate = f -> f.getName().endsWith("sql");
        File f1 = new File("D:/");
        for (File file : f1.listFiles(f -> f.isFile()))
        {
            if (predicate.test(file))
            {
                System.out.println(file.getName() + "   " + predicate.test(file));
            }
        }
        Consumer<Runnable> consumer = r -> r.run();
        consumer.accept(() -> System.out.println("dfgdfg"));
        Function<String, Integer> function = str -> Integer.valueOf(str);
        System.out.println(function.apply("65424"));
        Supplier<String> supplier = () -> "gdfgdf";
        System.out.println(supplier.get());
        UnaryOperator<String> unaryOperator = str -> str + "fsdfef";
        System.out.println(unaryOperator.apply("ojlkjr"));
        BinaryOperator<String> binaryOperator = (str1, str2) -> str1 + str2;
        System.out.println(binaryOperator.apply("545", "gdfg"));
        testCollectionsAndStream();

    }

    private static <T extends Comparable<? super T>> void testCollectionsAndStream()
    {
        List<Integer> aList = new ArrayList<Integer>();
        aList.add(1);
        aList.add(2);
        aList.add(3);
        aList.add(4);
        aList.add(5);
        System.out.println(aList.stream().mapToDouble(T -> T.intValue()).average().getAsDouble());
        Comparator<List<T>> comparator = Comparator.comparing(List<T>::size).reversed();
        List<List<T>> allList = new ArrayList<List<T>>();
        allList.sort(comparator);
        allList.forEach(list -> list.sort(T::compareTo));
        aList.parallelStream().filter(number ->
        {
            System.out.println("   --" + number.intValue());
            return number.intValue() > 3;
        }).mapToInt(Integer::intValue).sum();
        System.out.println(aList.stream().filter(number -> number.intValue() > 3).mapToInt(Integer::intValue).sum());
        System.out.println(aList.stream().filter(number -> number.intValue() > 3).mapToInt(Integer::intValue).findFirst().getAsInt());
    }
}
