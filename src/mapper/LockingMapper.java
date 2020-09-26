package mapper;

import domain.DomainObject;

public class LockingMapper extends DataMapper{
	private DataMapper mapper;

	public LockingMapper(DataMapper mapper) {
		this.setMapper(mapper);
	}

	private void setMapper(DataMapper mapper) {
		this.mapper = mapper;
		
	}

	@Override
	public int insert(DomainObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean update(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(DomainObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainObject findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
