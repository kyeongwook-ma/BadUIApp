package selab.dev.baduiapp.db;

public interface IDBCreator {
	String[] getCreateTableStmt();
	String[] getInitDataStmt();
}
