package baitonghop.business.entity;

import baitonghop.business.config.InputMethods;
import baitonghop.business.implementation.DepartmentImplement;

import java.time.LocalDate;

public class Employee
{
    private String employeeId, employeeName;
    private LocalDate birthday;
    private boolean sex;
    private double salary;
    private Employee manager;
    private Department department;
    private final int IDLENGTH = 5;

    public Employee()
    {
    }

    public void inputData()
    {
        if (DepartmentImplement.departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào, vui lòng thêm phòng ban trước khi thêm nhân viên");
            return;
        }
        while (true)
        {
            System.out.println("Nhập mã nhân viên, bắt đầu bằng ký tự E và có 5 ký tự");
            String input = InputMethods.getString();
            if (!input.startsWith("E") || input.length() != IDLENGTH)
            {
                System.out.println("Vui lòng nhập đúng định dạng");
            } else break;
        }
        System.out.println("Nhập tên nhân viên");
        this.employeeName = InputMethods.getString();
        System.out.println("Nhập ngày sinh của nhân viên");
        this.birthday = InputMethods.getDate();
        System.out.println("Nhập giới tính của nhân viên: True = Nam, False = Nữ");
        this.sex = InputMethods.getBoolean();
        System.out.println("Nhập lương nhân viên");
        this.salary = InputMethods.getDouble();
        departmentouter:
//Dùng để break vòng while khi thỏa điều kiện
        while (true)
        {
            System.out.println("Thông tin các phòng ban hiện có");
            for (Department d : DepartmentImplement.departmentList)
            {
                System.out.println(d.getDepartmentName());
            }
            System.out.println("Nhập tên phòng ban của nhân viên này");
            String departmentName = InputMethods.getString();
            for (Department d : DepartmentImplement.departmentList)
            {
                if (departmentName.equals(d.getDepartmentName()))
                {
                    this.department = d;
                    break departmentouter;
                } else System.out.println("Vui lòng nhập chính xác tên phòng ban");
            }
        }
        outer:
        while (true)
        {
            System.out.println("Nhập 1 để nhập thông tin người quản lý. Nhập 2 nếu nhân viên này không có quản lý");
            int choice = InputMethods.getInteger();
            switch (choice)
            {
                case 1:
                    System.out.println("Mời nhập thông tin người quản lý");
                    Employee newManager = new Employee();
                    newManager.inputData();
                    this.manager = newManager;
                    break outer;
                case 2:
                    this.manager = null;
                    break outer;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }

    public void displayData()
    {
        System.out.println("Mã nhân viên: " + this.employeeId);
        System.out.println("Tên nhân viên: " + this.employeeName);
        System.out.println("Ngày sinh: " + this.birthday);
        System.out.println("Giới tính: " + (this.sex ? "Nam" : "Nữ"));
        System.out.println("Lương nhân viên: " + this.salary);
        System.out.println("Thuộc phòng ban: " + this.department.getDepartmentName());
        System.out.println("Thông tin người quản lý: "
                + (this.manager == null ? "Không có"
                : "\nTên: " + this.manager.employeeName + "\nMã nhân viên: " + this.manager.employeeId));
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public boolean isSex()
    {
        return sex;
    }

    public void setSex(boolean sex)
    {
        this.sex = sex;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public Employee getManager()
    {
        return manager;
    }

    public void setManager(Employee manager)
    {
        this.manager = manager;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }
}
