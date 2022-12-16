package dal;

import java.util.List;
import entitys.Traveler;

public interface FileDao {
	public List<Traveler> getAll() throws Exception;
	public void save(Traveler t) throws Exception;
	public void update(Traveler t) throws Exception;
	public void delete(int id) throws Exception;
	public Traveler get(int id) throws Exception;
}
