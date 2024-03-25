package cancel.business.implementation;

import cancel.business.config.InputMethods;
import cancel.business.design.Manageable;
import cancel.business.entity.Department;
import cancel.business.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImplement implements Manageable
{
    public static final List<Department> departmentList = new ArrayList<>();

    @Override
    public void manageEntities()
    {
        while (true)
        {
            System.out.println("==================MANAGE-DEPARTMENT=========================");
            System.out.println("1. Thêm phòng ban");
            System.out.println("2. Xem danh sách phòng ban");
            System.out.println("3. Chỉnh sửa thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Hiển thị danh sách nhân viên của phòng ban theo mã phòng");
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
                    deleteElement();
                    break;
                case 5:
                    displayInfoById();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Thao tác không khả dụng");
                    break;
            }
        }

    }

    @Override
    public void displayAll()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào");
            return;
        }
        System.out.println("Danh sách các phòng ban hiện có:");
        for (Department d : departmentList)
        {
            d.displayData();
            System.out.println("-------------------------------------");
        }
    }


    @Override
    public void addElement()
    {
        Department newDept = new Department();
        System.out.println("Mời nhập thông tin phòng ban mới");
        newDept.inputData();
        departmentList.add(newDept);
        System.out.println("Đã thêm phòng ban thành công");
    }

    @Override
    public void editInfo()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào");
            return;
        }
        int index = searchById();
        if (index == -1)
        {
            System.out.println("Không tìm thấy phòng ban để chỉnh sửa");
            return;
        }
        //Chỉ cho chỉnh sửa tên phòng ban => Id không bị ảnh hưởng
        //=> Map quản lý số lượng nhân viên mỗi phòng ban không bị ảnh hưởng
        System.out.println("Mời nhập tên mới cho phòng ban này");
        String newName = InputMethods.getString();
        departmentList.get(index).setDepartmentName(newName);
        departmentList.get(index).setTotalMembers();//Kiểm tra lại số nhân viên của phòng ban để set giá trị tương ứng
    }

    @Override
    public void displayInfoById()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào");
            return;
        }
        int index = searchById();
        if (index == -1)
        {
            System.out.println("Không tìm thấy phòng ban");
            return;
        }
        System.out.println("Thông tin của nhân viên thuộc phòng ban này:");
        for (Employee e : EmployeeImplement.employeeList)
        {
            String departmentId = e.getDepartment().getDepartmentId();
            if (departmentId.equals(departmentList.get(index).getDepartmentId()))
            {
                System.out.println("Tên nhân viên: " + e.getEmployeeName());
                System.out.println("Mã nhân viên: " + e.getEmployeeId());
            }
        }
    }

    @Override
    public void deleteElement()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào");
            return;
        }
        int index = searchById();
        if (index == -1)
        {
            System.out.println("Không tìm thấy phòng ban muốn xóa");
            return;
        }
        if (departmentList.get(index).getTotalMembers() > 0)
        {
            System.out.println("Không thể xóa phòng ban này vì vẫn còn nhân viên ở đây");
            return;
        }
        departmentList.remove(index);
        System.out.println("Đã xóa phòng ban thành công");
    }

    @Override
    public int searchById()
    {
        System.out.println("Nhập mã id để tìm phòng ban");
        String id = InputMethods.getString();
        if (departmentList.isEmpty())
        {
            return -1;
        }
        for (int i = 0; i < departmentList.size(); i++)
        {
            if (departmentList.get(i).getDepartmentId().equals(id))
                return i;
        }
        return -1;
    }
}
