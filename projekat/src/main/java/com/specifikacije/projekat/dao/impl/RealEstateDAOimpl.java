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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.specifikacije.projekat.dao.RealEstateDAO;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.RealEstateType;
import com.specifikacije.projekat.model.RentOrBuy;
import com.specifikacije.projekat.service.AgentService;


@Repository
@Primary
public class RealEstateDAOimpl implements RealEstateDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AgentService agentService;
	
	private class RealEstateCallBackHandler implements RowMapper<RealEstate>  {

		private Map<Long, RealEstate> estates = new LinkedHashMap<>();
		
		@Override
		public RealEstate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			RealEstateType type = RealEstateType.valueOf(resultSet.getString(index++));
			Long agent_id = resultSet.getLong(index++);
//			System.out.println("Agent id je " + agent_id);
			Agent agent = agentService.findOne(agent_id);
//			System.out.println("Agent je " + agent);

			String location = resultSet.getString(index++);
			String picture = resultSet.getString(index++);
			Double price = resultSet.getDouble(index++);
			RentOrBuy rentOrBuy = RentOrBuy.valueOf(resultSet.getString(index++));
			Integer numOfVisitReq = resultSet.getInt(index++);
			Double grade = resultSet.getDouble(index++);
			Double viewNumber = resultSet.getDouble(index++);
			boolean isActive = resultSet.getBoolean(index++);
			Integer surface = resultSet.getInt(index++);


			RealEstate estate = estates.get(id);
			if (estate == null) {
				estate = new RealEstate(id, type, agent, location, picture, price, rentOrBuy, surface, numOfVisitReq, grade, viewNumber, isActive);
				estates.put(estate.getId(), estate); // dodavanje u kolekciju
				
			}
			return estate;
			
			
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
				String sql = "INSERT INTO estate (estate_type, agent_id, location, picture, price, rentOrBuy, surface, numOfVisitReq, grade, viewNumber, isActive) VALUES (?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, realEstate.getEstateType().toString());
				preparedStatement.setLong(index++, realEstate.getAgent().getId());
				preparedStatement.setString(index++, realEstate.getLocation());
				preparedStatement.setString(index++, realEstate.getPicture());
				preparedStatement.setDouble(index++, realEstate.getPrice());
				preparedStatement.setString(index++, realEstate.getRentOrBuy().toString());
				preparedStatement.setInt(index++, realEstate.getSurface());
				preparedStatement.setInt(index++, realEstate.getNumberOfVisitRequests());
				preparedStatement.setDouble(index++, realEstate.getGrade());
				preparedStatement.setDouble(index++, realEstate.getViewNumber());
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
		String sql = "UPDATE estate SET estate_type = ?, agent_id = ?, location = ?, picture = ?, price = ?, rentOrBuy = ?, surface = ?, numOfVisitReq = ?, grade = ?, viewNumber = ?,  isActive = ? WHERE id = ?";	
		jdbcTemplate.update(sql, realEstate.getEstateType().toString(), realEstate.getAgent().getId(), realEstate.getLocation(), realEstate.getPicture(), realEstate.getPrice(), realEstate.getRentOrBuy().toString(), realEstate.getSurface(), realEstate.getNumberOfVisitRequests() != null ? realEstate.getNumberOfVisitRequests() : null,  realEstate.getGrade() != null ? realEstate.getGrade() : null, realEstate.getViewNumber() != null ? realEstate.getViewNumber() : null, realEstate.getIsActive(), realEstate.getId());
			
	}

	@Transactional
	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM estate WHERE id = ?";
		jdbcTemplate.update(sql, id);	}

	
	@SuppressWarnings("deprecation")
	public List<RealEstate> find(String location, Integer surfaceFrom, Integer surfaceTo, Integer priceMin,
			Integer priceMax, String rent, String buy, boolean house, boolean apartment, boolean land,
			boolean office) {
		
		ArrayList<Object> args = new ArrayList<Object>();
		
//		System.out.println(location);
//		System.out.println(surfaceFrom);
//		System.out.println(surfaceTo);
//		System.out.println(priceMax);
//		System.out.println(priceMin);
//		System.out.println(rent);
//		System.out.println(buy);
//		System.out.println(house);
//		System.out.println(apartment);
//		System.out.println(land);
//		System.out.println(office);
		
		String sql = "SELECT p.id, p.estate_type, p.agent_id, p.location, p.picture, p.price, p.rentOrBuy, p.numOfVisitReq, p.grade, p.viewNumber, p.isActive, p.surface FROM estate p";
			
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean hasArgs = false;
		
		//dynamically make a sql request
		if (location != null) {
		    if (hasArgs)
		        whereSql.append(" AND ");
		    whereSql.append("p.location LIKE ?");
		    hasArgs = true;
		    args.add("%" + location + "%");
		}
		if (priceMax != null) {
		    if (hasArgs)
		        whereSql.append(" AND ");
		    whereSql.append("p.price <= ?");
		    hasArgs = true;
		    args.add(priceMax);
		}
		if (priceMin != null) {
		    if (hasArgs)
		        whereSql.append(" AND ");
		    whereSql.append("p.price >= ?");
		    hasArgs = true;
		    args.add(priceMin);
		}
		if (surfaceFrom != null) {
		    if (hasArgs)
		        whereSql.append(" AND ");
		    whereSql.append("p.surface >= ?");
		    hasArgs = true;
		    args.add(surfaceFrom);
		}
		if (surfaceTo != null) {
		    if (hasArgs)
		        whereSql.append(" AND ");
		    whereSql.append("p.surface <= ?");
		    hasArgs = true;
		    args.add(surfaceTo);
		}
		
		if(rent != null) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("p.rentOrBuy like 'Rent'");
			hasArgs = true;
		}else if(buy != null) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("p.rentOrBuy like 'Buy'");
			hasArgs = true;
		}
		
		
		if(house == true) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("(p.estate_type LIKE 'House'");
			hasArgs = true;
			if(apartment == true) {
				whereSql.append(" OR p.estate_type LIKE 'Apartment'");
			}
			if(office == true) {
				whereSql.append(" OR p.estate_type LIKE 'Office'");
			}
			if(land == true) {
				whereSql.append(" OR p.estate_type LIKE 'Land'");
			}
		    whereSql.append(")");

		}
		else if(apartment == true) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("(p.estate_type like 'Apartment'");
			hasArgs = true;
			
			if(house == true) {
				whereSql.append(" OR p.estate_type LIKE 'House'");
			}
			if(office == true) {
				whereSql.append(" OR p.estate_type LIKE 'Office'");
			}
			if(land == true) {
				whereSql.append(" OR p.estate_type LIKE 'Land'");
			}
		    whereSql.append(")");

		}
		else if(office == true) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("(p.estate_type like 'Office'");
			hasArgs = true;
			
			if(house == true) {
				whereSql.append(" OR p.estate_type LIKE 'House'");
			}
			if(apartment == true) {
				whereSql.append(" OR p.estate_type LIKE 'Apartment'");
			}
			if(land == true) {
				whereSql.append(" OR p.estate_type LIKE 'Land'");
			}
		    whereSql.append(")");

		}
		else if(land == true) {
			if(hasArgs)
				whereSql.append(" AND ");
			whereSql.append("(p.estaet_type like 'Land'");
			hasArgs = true;
			
			if(house == true) {
				whereSql.append(" OR p.estate_type LIKE 'House'");
			}
			if(apartment == true) {
				whereSql.append(" OR p.estate_type LIKE 'Apartment'");
			}
			if(office == true) {
				whereSql.append(" OR p.estate_type LIKE 'Office'");
			}
		    whereSql.append(")");

		}
		
		// add arguments if they exist
		if(hasArgs)
			sql=sql + whereSql.toString()+" ORDER BY p.id";
		else
			sql=sql + " ORDER BY p.id";
		
//		System.out.println("SQL");
//		System.out.println(sql);
		
		return jdbcTemplate.query(sql.toString(), args.toArray(), new RealEstateCallBackHandler());
		
	}

	
}
