package zad3;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Car")
public class Car {

	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "name")
	private String name;
	
	public Car() {
	}

	public Car(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Relationship(type = "Owner")
	private Set<OwnerAuto> ownerAutos = new HashSet<>();
	
	public void addOwner(OwnerAuto ownerAuto) {
		ownerAutos.add(ownerAuto);
	}

	@Override
	public String toString() {
		return "Car: " + id + " -> " + getName();
	}
}