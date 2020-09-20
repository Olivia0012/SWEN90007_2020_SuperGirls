/**
 * 
 */
package shared;

import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;
import mapper.DataMapper;
import mapper.LockingMapper;

/**
 * @author Super Girls
 *
 */
public class UnitOfWorkImp implements UnitOfWork<DomainObject> {
	private static ThreadLocal current = new ThreadLocal();

	private List<DomainObject> newObjects = new ArrayList<DomainObject>();
	private List<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private List<DomainObject> deletedObjects = new ArrayList<DomainObject>();

	public static void newCurrent() {
		setCurrent(new UnitOfWorkImp());
	}

	/**
	 * @return the current
	 */
	public static UnitOfWorkImp getCurrent() {
		return (UnitOfWorkImp) current.get();
	}

	/**
	 * @param current the current to set
	 */
	@SuppressWarnings("unchecked")
	public static void setCurrent(UnitOfWorkImp uow) {
		current.set(uow);
	}

	@Override
	public void registerDirty(DomainObject obj) {
		if (!objectInAnyList(obj)) {
			dirtyObjects.add(obj);
		}

	}

	private boolean objectInAnyList(DomainObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerNew(DomainObject obj) {
		if (!objectInAnyList(obj)) {
			newObjects.add(obj);
		}

	}

	@Override
	public void registerDeleted(DomainObject obj) {
		if (!objectInAnyList(obj)) {
			deletedObjects.add(obj);
		}

	}

	@Override
	public Boolean commit() {
		Boolean result = true;

		// commit new objects
		for (DomainObject obj : newObjects) {
			try {
				// get the mapper for the object
				DataMapper mapper = (DataMapper) Class.forName("mapper." + obj.getClass().getSimpleName() + "Mapper")
						.getDeclaredConstructor().newInstance();
				result = mapper.insert(obj);

				// if one of the insert failed, do not proceed with the rest
				if (!result) {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		// commit updated objects
		for (DomainObject obj : dirtyObjects) {
			try {
				// get the mapper for the object
				DataMapper mapper = (DataMapper) Class.forName("mapper." + obj.getClass().getSimpleName() + "Mapper")
						.getDeclaredConstructor().newInstance();
				
				//make a locking mapper
				LockingMapper lckMapper = new LockingMapper(mapper);
				result = lckMapper.update(obj);
				
				
				// if one of the insert failed, do not proceed with the rest
				if (!result) {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		// commit deleted objects
		for (DomainObject obj : deletedObjects) {
			try {
				// get the mapper for the object
				DataMapper mapper = (DataMapper) Class.forName("mapper." + obj.getClass().getSimpleName() + "Mapper")
						.getDeclaredConstructor().newInstance();
				result = mapper.delete(obj);

				// if one of the insert failed, do not proceed with the rest
				if (!result) {
					return false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return result;

	}
}
