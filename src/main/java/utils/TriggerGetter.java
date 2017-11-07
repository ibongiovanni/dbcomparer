package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import descriptor.Trigger;

public interface TriggerGetter {
	
    public List<Trigger> getTriggers(Connection conn, String schema, String tableName) throws SQLException;

}
