package zad3;

public interface Service<T> {
	
    T read(Long id);

    Iterable<T> readAll();

    void delete(Long id);
    
    void deleteAll();

    void createOrUpdate(T object);
}