package cancel.business.entity;

import cancel.business.config.InputMethods;
import cancel.business.implementation.DepartmentImplement;

import java.time.LocalDate;
import java.util.List;

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

    public void inputData(List<Employee> employeeList)
    {
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
        inputDepartment();
        inputManagerInfo(employeeList);
    }

    private void inputDepartment()
    {
        //Dùng để break vòng while khi thỏa điều kiện
        departmentouter:
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
    }


    private void inputManagerInfo(List<Employee> employeeList)
    {
        outer:
        while (true)
        {
            System.out.println("Nhập 1 để nhập thông tin người quản lý. Nhập 2 nếu nhân viên này không có quản lý");
            int choice = InputMethods.getInteger();
            switch (choice)
            {
                case 1://Nếu không có người quản lý nào trong danh sách thì bắt nhập mới
                    if (employeeList.isEmpty())
                    {
                        System.out.println("Mời nhập thông tin người quản lý");
                        Employee newManager = new Employee();
                        newManager.inputData(employeeList);
                        this.manager = newManager;
                        break outer;
                    }
                    System.out.println("Nhập 1 nếu muốn chọn quản lý từ danh sách hiện có, nhập 2 nếu muốn thêm mới");
                    int managerChoice = InputMethods.getInteger();
                    if (managerChoice == 1)
                    {
                        System.out.println("Danh sách nhân viên có thể chọn làm quản lý");
                        for (int i = 1; i <= employeeList.size(); i++)
                        {
                            System.out.println("Số thứ tự: " + i);
                            System.out.println("Tên nhân viên: " + employeeList.get(i).employeeName);
                        }
                        System.out.println("Nhập số thứ tự người muốn chọn");
                        int index = InputMethods.getInteger();
                        if (index >= 1 && index <= employeeList.size())
                        {
                            this.manager = employeeList.get(index - 1);
                        } else
                        {
                            System.out.println("Lựa chọn không đúng");
                            break; //Nhập sai thì không break vòng while, để lựa chọn lại
                        }
                    } else if (managerChoice == 2)
                    {
                        System.out.println("Mời nhập thông tin người quản lý");
                        Employee newManager = new Employee();
                        newManager.inputData(employeeList);
                        this.manager = newManager;
                    }
                    if (managerChoice != 1 && managerChoice != 2)
                    { //Nếu nhập lựa chọn sai thì không break vòng while để bắt lựa chọn lại
                        System.out.println("Lựa chọn không khả dụng");
                        break;
                    }
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
