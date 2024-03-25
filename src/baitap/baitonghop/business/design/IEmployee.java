package baitap.baitonghop.business.design;

import baitap.baitonghop.business.entity.Department;
import baitap.baitonghop.business.entity.Employee;

public interface IEmployee extends Manageable<Employee, String>
{
    void manageEmployee();

    void displayInfo();

    void getListEmployeeByDepartment();

    void getAvgEmployeesPerDepartment();

    void findMostManager();

    void find5OldestEmployee();

    void find5HighestSalary();

    void find5DepartmentCrowded();
}
