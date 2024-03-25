package baitap.baitonghop.business.design;

public interface Manageable<T, E>
{
    void addEntity();

    void displayAllEntities();


    void editEntity();

    void deleteEntity();

    T searchById(E id);
}
