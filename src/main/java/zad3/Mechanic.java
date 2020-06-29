package zad3;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Mechanic")
public class Mechanic {

	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "name")
	private String name;
	
	public Mechanic() {
	}

	public Mechanic(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Relationship(type = "Mechanic")
	private Set<Car> cars = new HashSet<>();

	public void addCar(Car car) {
		cars.add(car);
	}
	
	@Override
	public String toString() {
		return "Mechanic auto about\nId: " + id + "\nName: " + name;
	}
}