package baitap.baitonghop.business.implementation;

import baitap.baitonghop.business.config.CONSTANT;
import baitap.baitonghop.business.config.InputMethods;
import baitap.baitonghop.business.design.IEmployee;
import baitap.baitonghop.business.design.Manageable;
import baitap.baitonghop.business.entity.Department;
import baitap.baitonghop.business.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;
//List phòng ban
import static baitap.baitonghop.business.implementation.DepartmentManagement.departmentList;

public class EmployeeManagement implements IEmployee
{
    static final List<Employee> employeeList = new ArrayList<>();

    @Override
    public void manageEmployee()
    {
        while (true)
        {
            System.out.println("================MANAGE-EMPLOYEE===================");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Xem danh sách nhân viên");
            System.out.println("3. Chỉnh sửa thông tin nhân viên");
            System.out.println("4. Xem chi tiết thông tin nhân viên theo mã nhân viên");
            System.out.println("5. Xóa nhân viên");
            System.out.println("6. Thống kê số lượng nhân viên trung bình của mỗi phòng");
            System.out.println("7. Tìm ra 5 phòng có số lượng nhân viên đông nhất");
            System.out.println("8. Tìm ra người quản lý có nhiều nhân viên nhất");
            System.out.println("9. Tìm ra 5 nhân viên có tuổi cao nhất công ty");
            System.out.println("10. Tìm ra 5 nhân viên hưởng lương cao nhất");
            System.out.println("0. Quay lại");
            System.out.println("Nhập các số tương ứng để lựa chọn thao tác");
            int choice = InputMethods.getInteger();
            switch (choice)
            {
                case 1:
                    addEntity();
                    break;
                case 2:
                    displayAllEntities();
                    break;
                case 3:
                    editEntity();
                    break;
                case 4:
                    displayInfo();
                    break;
                case 5:
                    deleteEntity();
                    break;
                case 6:
                    getAvgEmployeesPerDepartment();
                    break;
                case 7:
                    find5DepartmentCrowded();
                    break;
                case 8:
                    findMostManager();
                    break;
                case 9:
                    find5OldestEmployee();
                    break;
                case 10:
                    find5HighestSalary();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }

    @Override
    public void addEntity()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào, vui lòng thêm phòng ban trước khi thêm nhân viên");
            return;
        }
        Employee newEm = new Employee();
        newEm.inputData(true, employeeList, departmentList);
        employeeList.add(newEm);
    }

    @Override
    public void displayAllEntities()
    {
        if (employeeList.isEmpty())
        {
            System.err.println("Hiện không có nhân viên nào để hiển thị");
        } else
        {
            employeeList.forEach(Employee::displayData);
        }
    }

    @Override
    public void displayInfo()
    {
        System.out.println("Nhập Id của nhân viên muốn xem chi tiết");
        String id = InputMethods.getString();
        Employee employee = searchById(id);
        if (employee == null)
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }
        System.out.println("Chi tiêt thông tin");
        employee.displayData();
    }

    @Override
    public void editEntity()
    {
        System.out.println("Hãy chọn nhân viên cần sửa thông tin");
        String id = InputMethods.getString();
        Employee edit = searchById(id);
        if (edit == null)
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }

        System.out.println("Thông tin cũ ");
        edit.displayData();

        // giảm số lương phòng ban cũ đi 1, ở hàm inputData sẽ cộng thêm 1
        // => Số lượng đảm bảo không thay dổi nếu chọn lại phòng ban cũ,
        // và tăng giảm đúng cách khi chọn phòng ban mới
        Department old = edit.getDepartment();
        old.setTotalMembers(old.getTotalMembers() - 1);

        System.out.println("Nhập thông tin mới");
        edit.inputData(false, employeeList, departmentList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public void deleteEntity()
    {
        System.out.println("Nhập Id của nhân viên muốn xóa");
        String id = InputMethods.getString();
        Employee delete = searchById(id);
        if (delete == null)
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }
        if (delete.getManager() != null)
        {
            System.err.println("Đây là một quản lý của nhân viên khác, không thể xóa");
            return;
        }
        delete.getDepartment().setTotalMembers(-1);//Giảm số nhân viên của phòng ban đi 1 nếu xóa thành công
        employeeList.remove(delete);
        System.out.println("Xóa thành công");
    }

    @Override
    public Employee searchById(String id)
    {
        for (Employee e : employeeList)
        {
            if (e.getEmployeeId().equals(id))
                return e;
        }
        return null;
    }

    @Override
    public void getListEmployeeByDepartment()
    {
        System.out.println("Danh sách phòng ban");
        departmentList.forEach(department -> System.out.printf("| ID : %-5s | DepartmentName : %-10s |\n", department.getDepartmentId(), department.getDepartmentName()));
        System.out.println("Hãy chọn departmentId muốn xem danh sách");
        String id = InputMethods.getString();
        if (departmentList.stream().noneMatch(t -> t.getDepartmentId().equals(id)))
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }
        // tìm thấy
        List<Employee> filterList = employeeList.stream().filter(e -> e.getDepartment().getDepartmentId().equals(id)).toList();
        if (filterList.isEmpty())
        {
            System.err.println("Phòng ban này ko có nhân viên");
        } else
        {
            filterList.forEach(Employee::displayData);
        }
    }

    @Override
    public void getAvgEmployeesPerDepartment()
    {
        System.out.println("Số lượng nhân vien trung bình là " + (employeeList.size() / departmentList.size()));
    }

    @Override
    public void findMostManager()
    {
        Map<String, Integer> map = new HashMap<>();
//        for (Employee employee : employeeList){
//            if (employee.getManager()!=null){
//                if (map.containsKey(employee.getManager().getEmployeeId())){
//                    // đã ton tại
//                    map.put(employee.getManager().getEmployeeId(),map.get(employee.getManager().getEmployeeId()+1));
//                }else{
//                  // chưa tồn tại
//                    map.put(employee.getManager().getEmployeeId(),1);
//                }
//            }
//        }
//
//        //tìm key có value lơn nhất
//        String idMax = null;
//        int maxValue = 0;
//        for (Map.Entry<String,Integer> entry: map.entrySet()){
//            if (entry.getValue()>maxValue){
//                maxValue = entry.getValue();
//                idMax = entry.getKey();
//            }
//        }
//        System.out.println("Người quản lí nhieeuuf nhan vien nhat là ");
//        findById(idMax).displayData();
//        System.out.println("Số lượng quản lí là : "+maxValue +" nhân viên");


        // c2
        // stream
        //So sánh bằng cách đếm số lượng nhân viên giữa 2 quản lý (Đảo chiều để sắp xếp giảm dần)
        Comparator<Employee> comparator = (o1, o2) -> Long.compare(countEmployeeManager(o2), countEmployeeManager(o1));
        Optional<Employee> employeeOptional = employeeList.stream().min(comparator);
        Employee manager = employeeOptional.orElseThrow(() ->
                new RuntimeException("ko tìm thay"));
        System.out.println("Người quản lý có nhiều nhân viên nhất là ");
        manager.displayData();
        System.out.println("Số nhân viên người này quản lý là " + countEmployeeManager(manager));
    }

    private long countEmployeeManager(Employee manager)
    {
        //Truyền vào nhân viên (quản lý) cần kiểm tra
        //Lọc ra các nhân viên có tồn tại manager != null
        //=> Đếm số lượng bằng hàm count()
        return employeeList.stream().filter(e -> e.getManager() != null
                && e.getManager().equals(manager)).count();
    }           //Đếm số nhân viên có quản lý khớp với quản lý được truyền vào


    @Override
    public void find5OldestEmployee()
    {
        Comparator<Employee> comparator = (e1, e2) -> e1.getBirthday().compareTo(e2.getBirthday());
        //Dùng hàm sorted để sắp xếp nhân viên theo thứ tự của comparator đã dịnh nghĩa
        //Reverse() để đào chiều sắp xếp thành lớn dến nhỏ
        //Dùng limit để giới hạn còn 5 nhân viên trong list
        //In ra từng phần tử trong list này
        List<Employee> list5Oldest = employeeList.stream().sorted(comparator.reversed()).limit(5).toList();
        list5Oldest.forEach(Employee::displayData);
    }

    @Override
    public void find5HighestSalary()
    {
        Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary);
        //Dùng hàm sorted để sắp xếp nhân viên theo thứ tự của comparator đã dịnh nghĩa
        //Reverse() để đào chiều sắp xếp thành lớn dến nhỏ
        //Dùng limit để giới hạn còn 5 nhân viên trong list
        //In ra từng phần tử trong list này
        List<Employee> list5HighestSalary = employeeList.stream().sorted(comparator.reversed()).limit(5).toList();
        list5HighestSalary.forEach(Employee::displayData);
    }

    @Override
    public void find5DepartmentCrowded()
    {
        //Sử dụng hàm hỗ trợ dếm số nhân viên trong mỗi phòng ban để tạo comparator
        Comparator<Employee> comparator = (e1, e2) -> Long.compare(countEmployeeInDepartment(e1.getDepartment()),
                countEmployeeInDepartment(e2.getDepartment()));
        //Dùng hàm sorted để sắp xếp nhân viên theo thứ tự của comparator đã dịnh nghĩa
        //Reverse() để đào chiều sắp xếp thành lớn dến nhỏ
        //Dùng limit để giới hạn còn 5 nhân viên trong list
        //In ra từng phần tử trong list này
        List<Employee> departmentMostCrowded =
                employeeList.stream().sorted(comparator.reversed()).limit(5).toList();
        departmentMostCrowded.forEach(Employee::displayData);
    }

    private long countEmployeeInDepartment(Department department)
    {
        //Truyền vào department, đếm số nhân viên có department khớp với tham số này
        //=> Trả về số lượng nhân viên mỗi phòng ban để so sánh
        return employeeList.stream().filter(e -> e.getDepartment().equals(department)).count();
    }
}
