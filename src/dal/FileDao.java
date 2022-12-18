package dal;

import java.util.List;


import entitys.Flight;


public interface FileDao {
	public List<Flight> getAll() throws Exception;
	public void save(Flight a) throws Exception;
	public void update(int id, Flight a) throws Exception;
	public void delete(int id) throws Exception;
	public Flight get(int id) throws Exception;
}
