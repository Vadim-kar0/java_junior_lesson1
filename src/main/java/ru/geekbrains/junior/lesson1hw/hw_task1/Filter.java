package ru.geekbrains.junior.lesson1hw.hw_task1;

import java.util.*;

public class Filter {
    /*
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
     */
    public static List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9,100,100,100);
    public static Set<Integer> integerSet = new HashSet<>(integerList);
    public static Queue<Integer> integerQueue = new PriorityQueue<>(integerList);

    public static void main(String[] args) {
        averageEvenNumbersInList(integerList);
        averageEvenNumbersInList(integerSet);
        averageEvenNumbersInList(integerQueue);
    }

    public static <T extends Collection<Integer>> void averageEvenNumbersInList(T t){
        int[] sum = {0};
        int[] count = {0};
        t.stream().filter(integer -> integer % 2 == 0).forEach(integer -> {
            sum[0] += integer;
            count[0]++;
        });

        if(count[0] != 0){
            System.out.printf("Среднее значение всех четных чисел в коллекции = %d\n",sum[0]/count[0]);
        } else {
            System.out.println("Чётных числа в коллекции отсутствуют");
        }
    }

}
