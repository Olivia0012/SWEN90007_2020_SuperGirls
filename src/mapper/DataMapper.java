package mapper;

import domain.DomainObject;

public abstract class DataMapper {

	public DataMapper() {
		// TODO Auto-generated constructor stub
	}
	
	protected abstract Boolean insert(DomainObject obj);
	protected abstract Boolean update(DomainObject obj);
	protected abstract Boolean delete(DomainObject obj);
	protected abstract DomainObject findById(int id);
		

}
