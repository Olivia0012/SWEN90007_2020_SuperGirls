package mapper;

import domain.DomainObject;

public abstract class DataMapper {

	public DataMapper() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract int insert(DomainObject obj);
	public abstract Boolean update(DomainObject obj);
	public abstract Boolean delete(DomainObject obj);
	public abstract DomainObject findById(int id);
		

}
