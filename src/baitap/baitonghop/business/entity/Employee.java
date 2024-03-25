package baitap.baitonghop.business.entity;

import baitap.baitonghop.business.config.InputMethods;

import java.time.LocalDate;
import java.util.List;

public class Employee
{
    private String employeeId, employeeName;
    private LocalDate birthday;
    private boolean sex;
    private double salary;

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    Department department;
    Employee manager;

    public void displayData()
    {
        System.out.printf("Mã nhân viên: %-5s | Tên nhân viên: %-20s | Ngày sinh: %-10s | Giới tính : %-4s" +
                        " | Lương: %10f | Phòng ban: %-10s | Người quản lý: %-20s \n",
                this.employeeId, this.employeeName,
                this.birthday, this.sex ? "Nam" : "Nữ", this.salary,
                this.department, this.manager != null ? this.manager.employeeName : "Không có");
    }                           //Quản lý có thể null nên phải check

    public void inputData(boolean isAdding, List<Employee> employeeList, List<Department> departmentList)
    {
        if (isAdding)//Chỉ cho thêm Id nếu là đang thêm mới nhân viên, chỉnh sửa thì không
        {
            this.employeeId = inputId(employeeList);
        }
        this.employeeName = inputName();
        System.out.println("Nhập ngày sinh theo đúng định dạng dd/MM/yyyy");
        this.birthday = InputMethods.getDate();
        System.out.println("Nhập giới tính nhân viên, true = Nam, false = Nữ");
        this.sex = InputMethods.getBoolean();
        System.out.println("Nhập lương của nhân viên");
        this.salary = InputMethods.getDouble();
        System.out.println("Nhập thông tin phòng ban của nhân viên");
        this.department = selectDepartment(departmentList);
        System.out.println("Lựa chọn quản lý cho nhân viên");
        this.manager = selectManager(employeeList);
    }

    private String inputId(List<Employee> employeeList)
    {
        String regex = "^E\\w{4}&";
        while (true)
        {
            System.out.println("Mời nhập mã nhân viên theo định dạng E____");
            String inputEmId = InputMethods.getString();
            if (inputEmId.matches(regex))//Xét định dạng trước
            {
                if (employeeList.isEmpty())//List chưa có phần tử => Có thể return ngay
                    return inputEmId;
                boolean alreadyExist = false;
                for (Employee employee : employeeList)
                {   //Kiểm tra sự trùng lặp của từng phần tử
                    if (employee.employeeId.equals(inputEmId))
                    {
                        System.out.println("Mã này đã tồn tại, vui lòng nhập lại");
                        alreadyExist = true;
                        break;
                    }
                }
                if (!alreadyExist)//Hết vòng for mà giá trị không đổi => Hợp lệ
                    return inputEmId;
            }
            System.out.println("Định dạng không hợp lệ, vui lòng nhập lại");
        }
    }

    private String inputName()
    {
        while (true)
        {
            System.out.println("Mời nhập tên nhân viên");
            String inputEmName = InputMethods.getString();
            if (inputEmName.isBlank())
            {
                System.out.println("Không được để trống");
            } else return inputEmName;
        }
    }

    private Department selectDepartment(List<Department> departmentList)
    {
        System.out.println("Danh sách phòng ban hiện có");
        for (int i = 1; i < departmentList.size(); i++)
        {
            System.out.printf("Số thứ tự: " + i +
                    " | Tên phòng ban: " + departmentList.get(i).getDepartmentName() + "\n");
        }
        System.out.println("-----------------------------------------------------------------------------");
        while (true)
        {
            System.out.println("Mời nhập số thứ tự tương ứng để chọn phòng ban cho nhân viên này");
            int index = InputMethods.getInteger();
            if (index >= 1 && index <= departmentList.size())
                return departmentList.get(index - 1);//index - 1 để thuộc khoảng 0-> departList.size -1
            System.out.println("Lựa chọn không khả dụng, vui lòng nhập lại");
        }

    }

    private Employee selectManager(List<Employee> employeeList)
    {
        while (true)
        {
            System.out.println("Nhập 1 để chọn quản lý cho nhân viên này, nhập 2 nếu không có quản lý");
            byte choice = InputMethods.getByte();
            switch (choice)
            {
                case 1:
                    if (employeeList.isEmpty())
                    {
                        System.out.println("Hiện chưa có nhân viên nào để chọn làm quản lý");
                        break;
                    }
                    System.out.println("Danh sách nhân viên có thể chọn làm quản lý");
                    for (int i = 1; i <= employeeList.size(); i++)
                    {
                        System.out.printf("Số thứ tự : " + i + " | Tên nhân viên: "
                                + employeeList.get(i).employeeName + "\n");
                    }
                    System.out.println("Mời nhập số thứ tự tương ứng để chọn người quản lý");
                    byte index = InputMethods.getByte();
                    if (index >= 1 && index <= employeeList.size())
                        return employeeList.get(index - 1);//Trừ 1 để đảm bảo index thuộc khoảng 0 -> emList.size();
                    else System.out.println("Lựa chọn không khả dụng");//Vượt quá khoảng thì bắt chọn lại
                    break;
                case 2:
                    return null;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }

    public Employee(String employeeId, String employeeName, LocalDate birthday, boolean sex, double salary, Employee manager)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.sex = sex;
        this.salary = salary;
        this.manager = manager;
    }

    public Employee()
    {
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
}
