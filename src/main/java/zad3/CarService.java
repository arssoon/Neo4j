package zad3;

import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.Map;

class CarService extends GenericService<Car> {

    public CarService(Session session) {
		super(session);
	}
    
	@Override
	Class<Car> getEntityType() {
		return Car.class;
	}
	
    Iterable<Map<String, Object>> getCarRelationships() {
        String query = 
        		"MATCH (c:Car)-[r]-() " +
        		"WITH type(r) AS t, COUNT(r) AS count " +
        		"WHERE count >= 1 " +
        		"RETURN t, count";
        System.out.println("Executing query successful");
        HashMap<String, Object> params = new HashMap<String, Object>();
        return session.query(query, params).queryResults();
    }

	public static void deleteEverything(Session session) {
		String command = "MATCH (n) DETACH DELETE n";
		System.out.println("Executing: " + command);
		HashMap<String, Object> params = new HashMap<String, Object>();
		session.query(command, params).queryResults();
	}

	public static void updateData(Session session, String oldName, String newName) {
		String command =
				"MATCH (c:Car{name:'" + oldName + "'})\n" +
				"WITH c, c {.*} as snapshot \n" +
				"SET c.name = '" + newName + "'" +
				"RETURN snapshot";
		System.out.println("Pomyślnie zaktualizowano");
		HashMap<String, Object> params = new HashMap<String, Object>();
		session.query(command, params).queryResults();
	}

	public static void showCars(CarService carService) {
		System.out.println("All car");
		System.out.println("-------------------------------------------------");
		for (Car c : carService.readAll()) {
			System.out.println(c);
		}
	}

	public static void showOnlyIdAndNameCar(CarService carService) {
		for (Car c : carService.readAll()) {
			System.out.println(c);
		}
	}

    
}