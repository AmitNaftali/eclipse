package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dal.FileDao;
import entitys.Traveler;

//service
@Component
public class TravelerService {
	@Qualifier()//TravelerFileDao
	@Autowired
	private FileDao dependency;
}
