package baitonghop.business.implementation;

import baitonghop.business.config.InputMethods;
import baitonghop.business.design.Manageable;
import baitonghop.business.entity.Employee;

import java.util.*;

public class EmployeeImplement implements Manageable
{
    public final static List<Employee> employeeList = new ArrayList<>();
    public final static Map<String, Integer> departmentMember = new TreeMap<>();

    @Override
    public void manageEntities()
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
                    addElement();
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    editInfo();
                    break;
                case 4:
                    displayInfoById();
                    break;
                case 5:
                    deleteElement();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }

    }

    //Dùng map để dễ tìm kiếm thông tin về số lượng nhân viên mỗi phòng ban hơn
    private void addMemberToDepartment()
    {
        for (Employee e : employeeList)
        {   //Lấy key là mã phòng ban, nếu nhân viên
            // có mã phòng ban trùng khớp thì tăng số đếm lên 1, không thì tạo key value pair mới
            String key = e.getDepartment().getDepartmentId();
            if (!departmentMember.containsKey(key))
                departmentMember.put(key, 1);
            else departmentMember.put(key, departmentMember.get(key) + 1);
            e.getDepartment().setTotalMembers();//Cập nhật số nhân viên cho phòng ban
        }
    }

    private void removeMemberFromDepartment()
    {
        for (Employee e : employeeList)
        {   //Lấy key là mã phòng ban, nếu nhân viên
            //có mã phòng ban trùng khớp thì giảm số đếm đi 1
            String key = e.getDepartment().getDepartmentId();
            //Chỉ giảm số lượng nhân viên khi số này lớn hơn 0 (đang có nhân viên)
            if (departmentMember.containsKey(key) && departmentMember.get(key) > 0)
                departmentMember.put(key, departmentMember.get(key) - 1);
            e.getDepartment().setTotalMembers();//Cập nhật số nhân viên cho phòng ban
        }
    }

    @Override
    public void addElement()
    {
        Employee newEm = new Employee();
        System.out.println("Mời nhập thông tin cho nhân viên mới");
        newEm.inputData();
        employeeList.add(newEm);
        System.out.println("Thêm nhân viên thành công");
        addMemberToDepartment();//Thêm nhân viên vào map quản lý số nhân viên mỗi phòng ban
    }

    @Override
    public void displayAll()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
        for (Employee e : employeeList)
        {
            System.out.println("Thông tin nhân viên:");
            System.out.println(e.getEmployeeName());
            System.out.println(e.getEmployeeId());
            System.out.println("---------------------------------------------");
        }
    }

    @Override
    public void editInfo()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
        int index = searchById();
        if (index != -1)
        {
            System.out.println("Thông tin cũ:");
            employeeList.get(index).displayData();
            System.out.println("Vui lòng nhập thông tin mới");
            employeeList.get(index).inputData();
        } else System.out.println("Không tìm thấy nhân viên");
    }

    @Override
    public void displayInfoById()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
        int index = searchById();
        if (index != -1)
        {
            System.out.println("Thông tin nhân viên:");
            employeeList.get(index).displayData();
        } else System.out.println("Không tìm thấy nhân viên");
    }

    @Override
    public void deleteElement()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
        int index = searchById();
        if (index != -1)
        {
            employeeList.remove(index);
            System.out.println("Đã xóa nhân viên");
            removeMemberFromDepartment();//Giảm bớt số nhân viên khỏi phòng ban trong map quản lý
        } else System.out.println("Không tìm thấy nhân viên");
    }

    @Override
    public int searchById()
    {
        System.out.println("Nhập id nhân viên để tìm");
        String id = InputMethods.getString();
        if (employeeList.isEmpty())
        {
            return -1;
        }
        for (int i = 0; i < employeeList.size(); i++)
        {
            if (employeeList.get(i).getEmployeeId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}
