/**
 * 
 */
package domain;

/**
 * @author manlo
 *
 */
public abstract class DomainObject {
	private int Id;

	/**
	 * 
	 */
	public DomainObject(int Id) {
		this.Id = Id;
	}

	public DomainObject() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	

}