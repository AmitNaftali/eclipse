package cli;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AirportCLI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//show flight
		//show traveler
		//add traveler
		//update traveler
		//delete traveler
		//exit
		// Load the Spring configuration file

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.close();
	}

}
