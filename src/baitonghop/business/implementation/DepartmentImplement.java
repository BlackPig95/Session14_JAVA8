package baitonghop.business.implementation;

import baitonghop.business.design.Manageable;
import baitonghop.business.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentImplement implements Manageable
{
    public static final List<Department> departmentList = new ArrayList<>();

    @Override
    public void displayAll()
    {
        if (departmentList.isEmpty())
        {
            System.out.println("Hiện không có phòng ban nào");
            return;
        }
        for (Department d : departmentList)
        {
            d.displayData();
        }
    }

    @Override
    public void displayInfo()
    {

    }

    @Override
    public void addElement()
    {

    }

    @Override
    public void editInfo()
    {

    }

    @Override
    public void displayInfoById()
    {

    }

    @Override
    public void deleteElement()
    {

    }

    @Override
    public int searchById(String id)
    {
        return 0;
    }
}
