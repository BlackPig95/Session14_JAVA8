package baitonghop.business.design;

public interface Manageable
{
    void displayAll(); //Hiển thị thông tin của tất cả các phần tử

    void displayInfo();//Hiển thị thông tin của từng phần tử

    void addElement();//Thêm phần tử mới

    void editInfo();//Chỉnh sửa thông tin phần tử

    void displayInfoById();//Tìm kiếm và hiển thị phần tử

    void deleteElement();//Xóa phần tử

    int searchById(String id);//Trả về index của id cần tìm để thực hiện các thao tác quản lý khác
}
