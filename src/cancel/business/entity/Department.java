package cancel.business.entity;

import cancel.business.config.InputMethods;
import cancel.business.implementation.DepartmentImplement;
import cancel.business.implementation.EmployeeImplement;

public class Department
{
    private String departmentId, departmentName;
    private int totalMembers;

    public void displayData()
    {   //Lấy ra số lượng nhân viên từ thông tin tên phòng ban tương ứng
        totalMembers = EmployeeImplement.departmentMember.get(this.departmentName);
        System.out.println("Mã phòng ban: " + this.departmentId);
        System.out.println("Tên phòng ban: " + this.departmentName);
        System.out.println("Số nhân viên trong phòng ban: " + this.totalMembers);
    }

    public void inputData()
    {
        while (true)
        {
            System.out.println("Mời nhập mã phòng ban, bắt đầu bằng D và có 4 ký tự");
            String input = InputMethods.getString();
            if (!input.startsWith("D") || input.length() != 4)
            {
                System.out.println("Vui lòng nhập đúng định dạng");
                break;
            } else this.departmentId = input;
        }
        outer:
        while (true)
        {
            System.out.println("Mời nhập tên phòng ban");
            this.departmentName = InputMethods.getString();
            if (DepartmentImplement.departmentList.isEmpty())
                break;
            for (int i = 0; i < DepartmentImplement.departmentList.size(); i++)
            {
                if (departmentName.equals(DepartmentImplement.departmentList.get(i).departmentName))
                {
                    System.out.println("Tên này đã tồn tại, vui lòng nhập tên khác");
                } else break outer;
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

    public void setTotalMembers()
    {
        //Truy cập vào map quản lý phòng ban, get ra số lượng nhân viên hoặc set về 0 nếu là phòng ban mới
        this.totalMembers = EmployeeImplement.departmentMember.getOrDefault(this.departmentId, 0);
    }
}
