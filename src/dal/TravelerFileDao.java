package dal;
import java.util.List;

import org.springframework.stereotype.Component;


import entitys.Traveler;

//bean
@Component
public class TravelerFileDao implements FileDao<Traveler>{
	
	private int l;

	@Override
	public List<Traveler> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Traveler t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Traveler t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Traveler get(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
