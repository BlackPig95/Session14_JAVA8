package baitap;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Bai9_DateTimeAPI
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        //Bai 1 zone date time
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);//System current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - Z");
        ZonedDateTime tokyoDate = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(tokyoDate.format(formatter));//Asia/Tokyo
        ZonedDateTime sydneyDate = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));
        System.out.println(sydneyDate.format(formatter));
        ZonedDateTime saoPauloDate = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println(saoPauloDate.format(formatter));
        //Bai 2 current time: HH-mm-ss
        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);
        //bai 3
//        between2Days();
        //Bai 4 Tính số ngày trong tháng hiện tại
        System.out.println("Số ngày trong tháng hiện tại: ");
        YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());
        System.out.println(yearMonth.lengthOfMonth());
//        Bài tập 5: Viết chương trình để tính toán số ngày trong năm hiện tại.
        System.out.println("Số ngày trong năm hiện tại");
        System.out.println(yearMonth.lengthOfYear());
//        Bài tập 6: Viết chương trình để chuyển đổi một chuỗi ngày sang một đối tượng LocalDate.
//            stringToDate();
//        Bài tập 7: Viết chương trình để chuyển đổi một đối tượng LocalDate sang một chuỗi ngày (dd/MM/yyyy).
//        dateToString();
//        Bài tập 8: Viết chương trình để chuyển đổi một đối tượng
//        LocalDateTime sang một chuỗi ngày(dd/MM/yyyy HH:mm:ss).
        LocalDateTime bai8Time = LocalDateTime.now();
        System.out.println(bai8Time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
//        Bài tập 9: Viết chương trình để so sánh hai ngày LocalDate ( trả về int ).
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plus(Period.ofDays(10));
        LocalDate date3 = LocalDate.now().minus(Period.ofDays(5));
        System.out.println(date1.compareTo(date2));
        System.out.println(date1.compareTo(date3));
//        Bài tập 10: Viết chương trình để so sánh hai thời gian  LocalTime.
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now().plus(Duration.ofSeconds(50));
        LocalTime time3 = LocalTime.now().minus(Duration.ofSeconds(50));
        System.out.println(time1.compareTo(time2));
        System.out.println(time1.compareTo(time3));
//        Bài tập 11: Viết chương trình để thêm hoặc bớt một số ngày, giờ,
//        phút, giây hoặc mili giây vào một ngày hoặc thời gian nhập vào.
//        addToDate();

//        Bài tập 12: Viết chương trình để tính toán ngày (LocalDate) tiếp theo hoặc ngày trước đó của một ngày.
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plus(Period.ofDays(1));
        LocalDate yesterday = LocalDate.now().minus(Period.ofDays(1));
        System.out.println(tomorrow);
        System.out.println(yesterday);
//        Bài tập 13: Viết chương trình để tính toán thời gian
//        (LocalTime) tiếp theo hoặc thời gian trước đó của một thời gian.
        //Không rõ yêu cầu
    }

    //Bai 3 Khoảng cách giữa 2 ngày
    public static void between2Days()
    {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Nhập ngày thứ nhất theo format dd-MM-yyyy");
        LocalDate firstDate = LocalDate.parse(scanner.nextLine(), formatDate);
        System.out.println(firstDate);
        LocalDate secondDate = LocalDate.parse(scanner.nextLine(), formatDate);
        System.out.println(secondDate);
        System.out.println("Khoảng cách giữa 2 ngày: " + ChronoUnit.DAYS.between(firstDate, secondDate));
    }

    public static void stringToDate()
    {
        DateTimeFormatter stringToLocalDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Nhập ngày theo định dạng dd-MM-yyyy");
        LocalDate stringLocalDate = LocalDate.parse(scanner.nextLine(), stringToLocalDate);
        System.out.println(stringLocalDate);
    }

    public static void dateToString()
    {
        LocalDate dateToString = LocalDate.now();
        DateTimeFormatter formatDateToString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(dateToString.format(formatDateToString));
    }

    public static void addToDate()
    {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        while (true)
        {
            System.out.println("Thời gian hiện tại: " + current.format(dateTimeFormatter));
            System.out.println("Nhập số để lựa chọn thêm khoảng thời gian nào");
            System.out.println("1. Thêm ngày");
            System.out.println("2. Thêm giờ");
            System.out.println("3. Thêm tháng");
            System.out.println("4. Thêm năm");
            System.out.println("0. Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice)
            {
                case 1:
                    System.out.print("Nhập ngày để cộng");
                    System.out.println(current.plus
                            (Period.ofDays(Integer.parseInt(scanner.nextLine()))).format(dateTimeFormatter));
                    break;
                case 2:
                    System.out.print("Nhập số giờ muốn cộng :");
                    System.out.println(current.plus
                            (Duration.ofHours(Integer.parseInt(scanner.nextLine()))).format(dateTimeFormatter));
                    break;
                case 3:
                    System.out.print("Nhập số tháng muốn cộng :");
                    System.out.println(current.plus
                            (Period.ofMonths(Integer.parseInt(scanner.nextLine()))).format(dateTimeFormatter));
                    break;
                case 4:
                    System.out.print("Nhập số năm muốn cộng :");
                    System.out.println(current.plus
                            (Period.ofYears(Integer.parseInt(scanner.nextLine()))).format(dateTimeFormatter));
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
