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

		try {
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
