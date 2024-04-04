package jamesngnm.travelbookingsystem.interfaces;

import java.util.List;

public interface GenericDAO<T> {
    T create(T t);
    T read(long id);
    T update(T t);
    T delete(long id);
    List<T> readAll();
}
