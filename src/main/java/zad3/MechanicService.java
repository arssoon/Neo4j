package zad3;

import org.neo4j.ogm.session.Session;

class MechanicService extends GenericService<Mechanic> {

    public MechanicService(Session session) {
		super(session);
	}
    
	@Override
	Class<Mechanic> getEntityType() {
		return Mechanic.class;
	}
    
}