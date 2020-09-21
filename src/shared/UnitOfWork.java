/**
 * 
 */
package shared;

/**
 * @author Super Girls
 *
 */
public interface UnitOfWork<T> {
	String INSERT = "INSERT";
	  String DELETE = "DELETE";
	  String MODIFY = "MODIFY";

	  /**
	   * Any register new operation occurring on UnitOfWork is only going to be performed on commit.
	   */
	  void registerNew(T entity);

	  /**
	   * Any register modify operation occurring on UnitOfWork is only going to be performed on commit.
	   */
	  void registerDirty(T entity);

	  /**
	   * Any register delete operation occurring on UnitOfWork is only going to be performed on commit.
	   */
	  void registerDeleted(T entity);

	  /***
	   * All UnitOfWork operations batched together executed in commit only.
	   */
	  Boolean commit();

}
