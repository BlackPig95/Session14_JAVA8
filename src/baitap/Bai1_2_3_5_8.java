package baitap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bai1_2_3_5_8
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arrayList = Stream.generate(() -> new Random().nextInt(1000)).limit(100)
                .collect(Collectors.toList());

//        ArrayList<Integer> arrayList = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 100; i++)
//        {
//            arrayList.add(random.nextInt(1, 1000));
//        }
        System.out.println(arrayList);
        //bai 1 tìm max
//        Optional<Integer> max = arrayList.stream().max(Integer::compareTo);
        Optional<Integer> max = arrayList.stream().max(Comparator.naturalOrder());
//        Optional<Integer> max = arrayList.stream().max(Comparator.comparingInt(o -> o));
        System.out.println(max.get());
        //bai 2 tìm số chẵn
        List<Integer> evenNum = arrayList.stream().filter(num -> num % 2 == 0).toList();
        System.out.println(evenNum);
        //bai 3 tính tổng    //giá trị return sẽ liên tục được thay vào accumulator cho các lần duyệt tiếp theo
        int sum = arrayList.stream().reduce((accumulator, element) -> accumulator + element).get();
        System.out.println(sum);
        //bai 5 lọc các số lớn hơn một số cụ thể
        System.out.println("Nhập một số để xác định giới hạn dưới");
        int underBound = Integer.parseInt(scanner.nextLine());
        arrayList.stream().filter(num -> num > underBound).forEach(num -> System.out.print(num + " "));
        //bai 8 anyMatch
        System.out.println();
        boolean isExist = arrayList.stream().anyMatch(num -> num % 2 == 0);
        System.out.println(isExist);

        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = new Random().nextInt(100);
//            * 2 + 1;
        }
        System.out.println(arrayList.stream().findFirst());
        OptionalInt firstNumberFound = Arrays.stream(arr).filter(value -> value % 2 == 0).findFirst();
        System.out.println(firstNumberFound.orElseThrow(() -> new RuntimeException("Không có số chẵn")));
    }
}
