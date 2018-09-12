package com.ef.load.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ef.entity.BlockedIPEntry;

public class BlockedIPRepository implements Repository<BlockedIPEntry, Long>{
	
	private Connection connection;

	public void createBlockedIPEntry(BlockedIPEntry blockedIPEntry, Connection conn) {}

	@Override
	public BlockedIPEntry createEntity(BlockedIPEntry blockedIPEntry) {
		String query = "INSERT INTO solution.blocked_ips (ip, reason) VALUES (?, ?)";
		try {
			PreparedStatement st = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, blockedIPEntry.getBlockedIp());
			st.setString(2, blockedIPEntry.getReason());

			int affectedRows = st.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("BlockedIP entry failed to be created in DB.");
	        }

	        try (ResultSet generatedKeys = st.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	blockedIPEntry.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("BlockedIP entry exception, Failed to retrieve id.");
	            }
	        }
			st.close();
		} catch (SQLException se) {
			throw new RuntimeException("Data insertion failed for IP: " + blockedIPEntry.getBlockedIp(), se);
		}
		return blockedIPEntry;
	}

	@Override
	public BlockedIPEntry retrieveEntity(BlockedIPEntry id) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public void deleteEntity(Long id) {
		throw new UnsupportedOperationException("Operation not supported.");

	}

	@Override
	public void updateEntity(BlockedIPEntry entity) {
		throw new UnsupportedOperationException("Operation not supported.");

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
