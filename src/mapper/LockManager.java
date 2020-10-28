package mapper;

public interface LockManager {
	
	
	public String acquireLock(int id, String table, String owner);
	public void releaseLock(int id, String table, String owner);
	public void releaseAllLocks(String owner);
	public void releaseExpiredLocks();

}
