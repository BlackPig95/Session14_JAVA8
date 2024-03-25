package baitap.baitonghop.business.entity;

import baitap.baitonghop.business.config.InputMethods;

import java.util.List;

public class Department
{
    private String departmentId, departmentName;
    private int totalMembers;

    public Department(String departmentId, String departmentName, int totalMembers)
    {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.totalMembers = totalMembers;
    }

    public void displayData()
    {
        System.out.printf("Mã phòng ban: %-5s | Tên phòng ban: %-20s | Số nhân viên: %-10s \n",
                this.departmentId, this.departmentName, this.totalMembers);
    }

    public void inputData(boolean isAdding, List<Department> departmentList)
    {
        if (isAdding)//Chỉ cho nhập Id nếu đây là hành động thêm mới
        {
            this.departmentId = inputId(departmentList);
        }
        this.departmentName = inputName(departmentList);
    }

    private String inputId(List<Department> departmentList)
    {
        String regex = "^D\\w{3}&";
        while (true)
        {
            System.out.println("Mời nhập mã phòng ban theo định dạng D___");
            String inputDeptId = InputMethods.getString();
            if (inputDeptId.matches(regex))//Xét định dạng trước
            {
                if (departmentList.isEmpty())//List chưa có phần tử => Có thể return ngay
                    return inputDeptId;
                boolean alreadyExist = false;
                for (Department department : departmentList)
                {   //Kiểm tra sự trùng lặp của từng phần tử
                    if (department.departmentId.equals(inputDeptId))
                    {
                        System.out.println("Mã này đã tồn tại, vui lòng nhập lại");
                        alreadyExist = true;
                        break;
                    }
                }
                if (!alreadyExist)//Hết vòng for mà giá trị không đổi => Hợp lệ
                    return inputDeptId;
            }
            System.out.println("Định dạng không hợp lệ, vui lòng nhập lại");
        }
    }

    private String inputName(List<Department> departmentList)
    {
        while (true)
        {
            System.out.println("Mời nhập tên phòng ban");
            String inputDeptName = InputMethods.getString();
            if (inputDeptName.isBlank())
            {
                System.out.println("Không được để trống");
            } else
            {   //Dùng để kiểm tra xem tên đã tồn tại chưa
                boolean alreadyExist = false;
                for (Department department : departmentList)
                {
                    if (department.getDepartmentName().equals(inputDeptName))
                    {
                        System.out.println("Tên đã tồn tại, vui lòng nhập tên khác");
                        alreadyExist = true;
                        break;
                    }
                }
                if (!alreadyExist)
                    return inputDeptName;
            }
        }
    }

    public Department()
    {
    }

    public String getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public int getTotalMembers()
    {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers)
    {
        this.totalMembers = totalMembers;
    }
}
