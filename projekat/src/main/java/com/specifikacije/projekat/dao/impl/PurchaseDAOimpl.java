package com.specifikacije.projekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.RealEstateService;
import com.specifikacije.projekat.service.UserService;
import java.sql.Date;
@Repository
public class PurchaseDAOimpl {
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;

	 @Autowired
	 private UserService userService;

	 @Autowired
	 private RealEstateService estateService;
	    
	    
	    private class PurchaseCallBackHandler implements RowCallbackHandler {


	        private Map<Long, Purchase> purchases = new LinkedHashMap<>();


	        @Override
	        public void processRow(ResultSet resultSet) throws SQLException {
	            int index = 1;
	            Long id = resultSet.getLong(index++);
	            Long agentId = resultSet.getLong(index++);
	            Long estateId = resultSet.getLong(index++);
				Date purchaseDate = resultSet.getDate(index++);

	            User user = userService.findOne(agentId);
	            RealEstate estate = estateService.findOne(estateId);

	            Purchase purchase = purchases.get(id);
	            if(purchase == null){
	            	purchase = new Purchase(id, user, estate, purchaseDate);
	            	purchases.put(purchase.getId(), purchase);

	            }
	        }



	        public List<Purchase> getPurchases() {
	            return new ArrayList<>(purchases.values());
	        }
	    }
	    
	    
	    
	    
	    
	    public Purchase findOne(Long id) {
	        String sql = "select * from purchase" +
	                " where id = ?" +
	                " order by id";
	        PurchaseCallBackHandler handler = new PurchaseCallBackHandler();
	        jdbcTemplate.query(sql, handler, id);
	        return handler.getPurchases().get(0);
	    }

	    
	    public List<Purchase> findAll() {
	        String sql = "select * from purchase";
	        PurchaseCallBackHandler handler = new PurchaseCallBackHandler();
	        jdbcTemplate.query(sql, handler);
	        return handler.getPurchases();
	    }

	    @Transactional
	    public Purchase save(Purchase purchase) {
	        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                String sql = "INSERT INTO purchase (user_id, estate_id, purchase_date ) VALUES (?, ?, ?)";

	                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                int index = 1;
	                preparedStatement.setLong(index++, purchase.getUser().getId());
	                preparedStatement.setLong(index++, purchase.getEstate().getId());
	                preparedStatement.setDate(index++, purchase.getPurchaseTime());


	                return preparedStatement;
	            }

	        };
	        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
			return purchase;
	    }

	    @Transactional
	    public void update(Purchase purchase) {
	        String sql = "update purchase set user_id = ?, estate_id = ?, purchase_date = ?, is_read = ? where id = ?";
	        jdbcTemplate.update(sql, purchase.getUser().getId(), purchase.getEstate().getId(), purchase.getPurchaseTime(), purchase.getId());
	    }

	    @Transactional
	    public void delete(Long id) {
	        String sql = "DELETE FROM purchase WHERE id = ?";
	        jdbcTemplate.update(sql, id);
	    }


		

	    
	    


	    
	    
	    
}
