package zad3;

import org.neo4j.ogm.session.Session;

class OwnerAutoService extends GenericService<OwnerAuto> {

    public OwnerAutoService(Session session) {
		super(session);
	}
    
	@Override
	Class<OwnerAuto> getEntityType() {
		return OwnerAuto.class;
	}
    
}