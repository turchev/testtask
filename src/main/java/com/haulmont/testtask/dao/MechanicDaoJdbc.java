package com.haulmont.testtask.dao;

import java.sql.CallableStatement;
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
		final String SQL = "SELECT * FROM mechanic;";
		List<Mechanic> result = new ArrayList<Mechanic>();
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				Mechanic mechanic = new Mechanic(rs.getLong("id"));
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
		final String SQL = "SELECT * FROM mechanic WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Mechanic mechanic = new Mechanic(rs.getLong("id"));
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

	@Override
	public synchronized Mechanic.Stat getStat(Long id) throws DaoException {
		final String SQL_0 = "SELECT CONCAT(mechanic.last_name, ' ', "
				+ "LEFT(mechanic.first_name, 1), '.', "
				+ "LEFT(mechanic.patronnymic, 1),'.') AS mechanic_fio "
				+ "FROM mechanic WHERE mechanic.id = ?";			
		final String SQL = "CALL  mechanic_stat(?);";
		Mechanic mechanic = findById(id);	
		Mechanic.Stat mechanicStat = mechanic.new Stat();
		try (Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(SQL_0);
				CallableStatement callStmt = connection.prepareCall(SQL);) {
			pstmt.setLong(1, id);
			ResultSet rs_0 = pstmt.executeQuery();
			rs_0.next();
			mechanicStat.setMechanicFio(rs_0.getString("mechanic_fio"));
			callStmt.setLong(1, id);
			callStmt.execute();
			if (callStmt.getMoreResults()) {
				ResultSet rs = callStmt.getResultSet();
				rs.next();				
				mechanicStat.setOrdersSum(rs.getInt("orders_sum"));
				mechanicStat.setHhSum(rs.getBigDecimal("hh_sum"));
				mechanicStat.setPriceSum(rs.getBigDecimal("price_sum"));
				return mechanicStat;
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return null;
	}
}
