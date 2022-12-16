package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import entitys.Traveler;

@Component
public class TravelerFileDao implements FileDao{

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
