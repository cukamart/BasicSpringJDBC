package cuka.martin.jdbc_propertyfile;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans/bean.xml");

		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		
		// editne offer s ID = 90
		/*Offer updateOffer = new Offer(90, "Claire", "claire@gmail.com", "PHP coding for free");
		if(offerDao.update(updateOffer)){
			System.out.println("Object update");
		} else {
			System.out.println("Object cannot updated - wrong ID");
		}*/
		try {
			Offer offer1 = new Offer("Dave", "dave@caveofprogramming.com", "Coding Java");
			Offer offer2 = new Offer("Karen", "karen@caveofprogramming.com", "Software testing for demand");
			
			if (offersDao.create(offer1)){
				System.out.println("Created offer1 object");
			}
			
			if (offersDao.create(offer2)){
				System.out.println("Created offer2 object");
			}
			
			offersDao.delete(80);
			
			List<Offer> offers = offersDao.getOffers();

			for (Offer offer : offers) {
				System.out.println(offer);
			}
			
			Offer specificOffer = offersDao.getOffer(2);
			
			System.out.println("Should be Mike: " + specificOffer);
			
		} catch (CannotGetJdbcConnectionException ex) {
			System.out.println("Cannot get database connection");
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}

		((ClassPathXmlApplicationContext) context).close();
	}
}
