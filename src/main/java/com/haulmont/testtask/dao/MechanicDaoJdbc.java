package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.haulmont.testtask.entity.Mechanic;

class MechanicDaoJdbc implements MechanicDao {

	private DataSource ds = null;

	public MechanicDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized List<Mechanic> findAll() throws DaoException {
		final String SQL = "select * from mechanic;";
		List<Mechanic> result = new ArrayList<Mechanic>();
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				Mechanic mechanic = new Mechanic();
				mechanic.setId(rs.getLong("id"));
				mechanic.setFirstName(rs.getString("first_name"));
				mechanic.setLastName(rs.getString("last_name"));
				mechanic.setPatronnymic(rs.getString("patronnymic"));
				mechanic.setWages(rs.getBigDecimal("wages"));
				result.add(mechanic);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public synchronized Mechanic findById(long id) throws DaoException {
		final String SQL = "select * from mechanic where id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Mechanic mechanic = new Mechanic();
			mechanic.setId(rs.getLong("id"));
			mechanic.setFirstName(rs.getString("first_name"));
			mechanic.setLastName(rs.getString("last_name"));
			mechanic.setPatronnymic(rs.getString("patronnymic"));
			mechanic.setWages(rs.getBigDecimal("wages"));
			return mechanic;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void update(Mechanic mechanic) throws DaoException {
		final String SQL = "UPDATE mechanic SET last_name=?, first_name=?, patronnymic=?, wages=? WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, mechanic.getLastName());
			pstmt.setString(2, mechanic.getFirstName());
			pstmt.setString(3, mechanic.getPatronnymic());
			pstmt.setBigDecimal(4, mechanic.getWages());
			pstmt.setLong(5, mechanic.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void create(Mechanic mechanic) throws DaoException {
		final String SQL = "INSERT INTO mechanic (last_name, first_name, patronnymic, wages) VALUES (?,?,?,?);";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, mechanic.getLastName());
			pstmt.setString(2, mechanic.getFirstName());
			pstmt.setString(3, mechanic.getPatronnymic());
			pstmt.setBigDecimal(4, mechanic.getWages());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void delete(long id) throws DaoException {
		final String SQL = "DELETE FROM mechanic WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
