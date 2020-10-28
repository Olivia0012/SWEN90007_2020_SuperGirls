package mapper;

import domain.DomainObject;

public class LockingMapper extends DataMapper{
	private String token;
	private ExclusiveWriteLockManager lm;
	private DataMapper dmImp;
	private String table;


	public LockingMapper(DataMapper dmImp,String token,String table) {
		this.dmImp = dmImp;
		this.lm = ExclusiveWriteLockManager.getInstance();
		this.token = token;
		this.table = table;
	}
	
	@Override
	public int insert(DomainObject obj) {
		return dmImp.insert(obj);
	}

	@Override
	public Boolean update(DomainObject obj) {
		if(lm.acquireLock(obj.getId(),table, token).equals("true")) {
			return dmImp.update(obj);
		}else {
			return false;
		}
	}

	@Override
	public Boolean delete(DomainObject obj) {
		if(lm.acquireLock(obj.getId(),table, token).equals("true")) {
			return dmImp.delete(obj);
		}else {
			return false;
		}
	}

	@Override
	public DomainObject findById(int id) {
		if(lm.acquireLock(id,table, token).equals("true")) {
			return dmImp.findById(id);
		}else {
			return null;
		}
		
	}
	
	
}
