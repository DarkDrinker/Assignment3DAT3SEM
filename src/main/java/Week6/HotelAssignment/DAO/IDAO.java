package Week6.HotelAssignment.DAO;

import java.util.List;

public interface IDAO<T, S>{
    public List<T> getAll();
    public T getById(S id);
    public T create(T t);
    public T update(T t, S id);
    public void delete(S t);
}