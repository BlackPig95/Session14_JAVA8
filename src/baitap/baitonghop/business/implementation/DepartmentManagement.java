package baitap.baitonghop.business.implementation;

import baitap.baitonghop.business.config.CONSTANT;
import baitap.baitonghop.business.design.IDepartment;
import baitap.baitonghop.business.entity.Department;
import cancel.business.config.InputMethods;

import static baitap.baitonghop.business.implementation.EmployeeManagement.employeeList;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManagement implements IDepartment
{
    static final List<Department> departmentList = new ArrayList<>();

    @Override
    public void manageDepartment()
    {
        while (true)
        {
            System.out.println("==================MANAGE-DEPARTMENT=========================");
            System.out.println("1. Thêm phòng ban");
            System.out.println("2. Xem danh sách phòng ban");
            System.out.println("3. Chỉnh sửa thông tin phòng ban");
            System.out.println("4. Xóa phòng ban");
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
                    deleteEntity();
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
    public void addEntity()
    {
        Department newDept = new Department();
        newDept.inputData(true, departmentList);
        departmentList.add(newDept);
    }

    @Override
    public void displayAllEntities()
    {
        if (departmentList.isEmpty())
        {
            System.err.println("Danh sach trống");
        } else
        {
            departmentList.forEach(Department::displayData);
        }
    }

    @Override
    public void editEntity()
    {
        System.out.println("Nhập Id của phòng ban muốn cập nhật");
        String id = InputMethods.getString();
        Department edit = searchById(id);
        if (edit == null)
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }
        System.out.println("Thông tin cũ:");
        edit.displayData();
        // yêu cầu nhập thông tin mới
        System.out.println("Mời nhập thong tin mới");
        edit.inputData(false, departmentList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public void deleteEntity()
    {
        System.out.println("Nhập Id của phòng ban muốn xóa");
        String id = InputMethods.getString();
        Department delete = searchById(id);
        if (delete == null)
        {
            System.err.println(CONSTANT.ID_NOT_EXIST);
            return;
        }
        // kiểm  tra xem có nhân viên hay ko
        if (employeeList.stream().anyMatch(e -> e.getDepartment().getDepartmentId().equals(id)))
        {
            // có nhân viên
            System.err.println("Phòng ban này vẫn còn nhân viên, không thể xóa");
            return;
        }
        departmentList.remove(delete);
        System.out.println("Xóa thành công");
    }

    @Override
    public Department searchById(String id)
    {
        for (Department d : departmentList)
        {
            if (d.getDepartmentId().equals(id))
                return d;
        }
        return null;
    }
}
