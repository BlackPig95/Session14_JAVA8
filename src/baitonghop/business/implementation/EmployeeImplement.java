package baitonghop.business.implementation;

import baitonghop.business.config.InputMethods;
import baitonghop.business.design.Manageable;
import baitonghop.business.entity.Employee;

import java.util.*;

public class EmployeeImplement implements Manageable
{
    public final static List<Employee> employeeList = new ArrayList<>();
    public final static Map<String, Integer> departmentMember = new TreeMap<>();

    private void addMemberToDepartment()
    {
        for (Employee e : employeeList)
        {   //Lấy key là tên phòng ban, nếu nhân viên
            // có tên phòng ban trùng khớp thì tăng số đếm lên 1, không thì tạo key value pair mới
            String key = e.getDepartment().getDepartmentName();
            if (!departmentMember.containsKey(key))
                departmentMember.put(key, 1);
            else departmentMember.put(key, departmentMember.get(key) + 1);
        }
    }

    private void removeMemberFromDepartment()
    {
        for (Employee e : employeeList)
        {   //Lấy key là tên phòng ban, nếu nhân viên
            // có tên phòng ban trùng khớp thì giảm số đếm đi 1
            String key = e.getDepartment().getDepartmentName();
            //Chỉ giảm số lượng nhân viên khi số này lớn hơn 0 (đang có nhân viên)
            if (departmentMember.containsKey(key) && departmentMember.get(key) > 0)
                departmentMember.put(key, departmentMember.get(key) - 1);
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
    }

    @Override
    public void displayInfo()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("Không có nhân viên nào để hiển thị");
            return;
        }
        System.out.println("Nhập id nhân viên để tìm");
        String id = InputMethods.getString();
        int index = searchById(id);
        if (index != -1)
        {
            employeeList.get(index).displayData();
            return;
        }
        System.out.println("Không tìm thấy nhân viên có mã id trùng khớp");
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
            e.displayData();
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
        System.out.println("Nhập id nhân viên để chỉnh sửa");
        String id = InputMethods.getString();
        int index = searchById(id);
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
        System.out.println("Nhập id nhân viên để tìm thông tin");
        String id = InputMethods.getString();
        int index = searchById(id);
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
        System.out.println("Nhập id nhân viên muốn xóa");
        String id = InputMethods.getString();
        int index = searchById(id);
        if (index != -1)
        {
            employeeList.remove(index);
            System.out.println("Đã xóa nhân viên");
        } else System.out.println("Không tìm thấy nhân viên");
    }

    @Override
    public int searchById(String id)
    {
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
