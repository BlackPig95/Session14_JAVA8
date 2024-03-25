package cancel.business.design;

public interface Manageable
{
    void manageEntities();//Hàm quản lý chung, để gọi tất cả các hàm khác

    void displayAll(); //Hiển thị thông tin của tất cả các phần tử

    void addElement();//Thêm phần tử mới

    void editInfo();//Chỉnh sửa thông tin phần tử

    void displayInfoById();//Tìm kiếm và hiển thị phần tử

    void deleteElement();//Xóa phần tử

    int searchById();
}
