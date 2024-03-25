package baitap.baitonghop.presentation;

import baitap.baitonghop.business.config.InputMethods;
import baitap.baitonghop.business.design.IDepartment;
import baitap.baitonghop.business.design.IEmployee;
import baitap.baitonghop.business.design.Manageable;
import baitap.baitonghop.business.entity.Department;
import baitap.baitonghop.business.entity.Employee;
import baitap.baitonghop.business.implementation.DepartmentManagement;
import baitap.baitonghop.business.implementation.EmployeeManagement;

import java.util.ArrayList;
import java.util.List;

public class MainEmDept
{
    public static void main(String[] args)
    {
        IDepartment departmentManagement = new DepartmentManagement();
        IEmployee employeeManagement = new EmployeeManagement();
        while (true)
        {
            System.out.println("=============MENU===================");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("0. Thoát");
            System.out.println("Nhập các số tương ứng để lựa chọn thao tác");
            int choice = InputMethods.getInteger();
            switch (choice)
            {
                case 1:
                    departmentManagement.manageDepartment();
                    break;
                case 2:
                    employeeManagement.manageEmployee();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }
}
