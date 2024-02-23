package com.specifikacije.projekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.specifikacije.projekat.dao.RealEstateDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.RealEstateType;
import com.specifikacije.projekat.model.RentOrBuy;

@Repository
@Primary
public class RealEstateDAOimpl implements RealEstateDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class RealEstateCallBackHandler implements RowCallbackHandler {

		private Map<Long, RealEstate> estates = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			RealEstateType type = RealEstateType.valueOf(resultSet.getString(index++));
			
			String location = resultSet.getString(index++);
			String picture = resultSet.getString(index++);
			Double price = resultSet.getDouble(index++);
			RentOrBuy rentOrBuy = RentOrBuy.valueOf(resultSet.getString(index++));
			boolean isActive = resultSet.getBoolean(index++);
			
			RealEstate estate = estates.get(id);
		/*	if (estate == null) {
				estate = new RealEstate(id, type, location, picture, price, rentOrBuy, isActive);
				estates.put(estate.getId(), estate); // dodavanje u kolekciju
			}
			
			TODO Kada uradimo bazu
			*/
		}

		public List<RealEstate> getRealEstates() {
			return new ArrayList<>(estates.values());
		}

	}
	
	
	@Override
	public RealEstate findOne(Long id) {
		String sql = 
				"SELECT * FROM estate ck " +
				"WHERE ck.id = ? " + 
				"ORDER BY ck.id";

		RealEstateCallBackHandler rowCallbackHandler = new RealEstateCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getRealEstates().get(0);
	}

	@Override
	public List<RealEstate> findAll() {
		String sql = 
				"SELECT * FROM estate ck " +
				"ORDER BY ck.id";

		RealEstateCallBackHandler rowCallbackHandler = new RealEstateCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getRealEstates();
	}

	@Transactional
	@Override
	public void save(RealEstate realEstate) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO estate (type, location, picture, price, rentOrBuy, isActive) VALUES (?, ? ,?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, realEstate.getType().toString());
				preparedStatement.setString(index++, realEstate.getLocation());
				preparedStatement.setString(index++, realEstate.getPicture());
				preparedStatement.setDouble(index++, realEstate.getPrice());
				preparedStatement.setString(index++, realEstate.getRentOrBuy().toString());
				preparedStatement.setBoolean(index++, realEstate.getIsActive());


				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		}

	@Transactional
	@Override
	public void update(RealEstate realEstate) {
		String sql = "UPDATE estate SET type = ?, location = ?, picture = ?, price=?, rentOrBuy=?, isActive=? WHERE id = ?";	
		jdbcTemplate.update(sql, realEstate.getType().toString(), realEstate.getLocation(), realEstate.getPicture(), realEstate.getPrice(), realEstate.getRentOrBuy().toString(), realEstate.getIsActive(), realEstate.getId());
			
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM estate WHERE id = ?";
		jdbcTemplate.update(sql, id);	}

}
