package baitonghop.presentation;

import baitonghop.business.config.InputMethods;
import baitonghop.business.design.Manageable;
import baitonghop.business.implementation.DepartmentImplement;
import baitonghop.business.implementation.EmployeeImplement;

public class MainEmDept
{
    public static void main(String[] args)
    {
        Manageable departmentManager = new DepartmentImplement();
        Manageable employeeManager = new EmployeeImplement();
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
                    departmentManager.manageEntities();
                    break;
                case 2:
                    employeeManager.manageEntities();
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
