package baitap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bai4_6
{
    public static void main(String[] args)
    {
        List<String> stringList = Arrays.asList("1", "wrqw", "8rwr", " abc", "fefed", "febaa");
        List<String> sortedList = stringList.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);
        List<String> uppercaseList = stringList.stream().map(String::toUpperCase).toList();
        System.out.println(uppercaseList);
    }
}
