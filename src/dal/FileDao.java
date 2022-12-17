package dal;

import java.util.List;
import entitys.Traveler;

public interface FileDao<T> {
	public List<T> getAll() throws Exception;
	public void save(T t) throws Exception;
	public void update(T t) throws Exception;
	public void delete(int id) throws Exception;
	public T get(int id) throws Exception;
}
