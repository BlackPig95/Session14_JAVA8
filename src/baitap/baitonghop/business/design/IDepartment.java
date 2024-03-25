package baitap.baitonghop.business.design;

import baitap.baitonghop.business.entity.Department;

public interface IDepartment extends Manageable<Department, String>
{
    void manageDepartment();
}
