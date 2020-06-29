package zad3;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Owner")
public class OwnerAuto {

	@Id
	@GeneratedValue
	private Long id;

	@Property(name = "name")
	private String name;
	
	public OwnerAuto() {
	}

	public OwnerAuto(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Owner about\nID: " + id + "\nname: " + name;
	}
}