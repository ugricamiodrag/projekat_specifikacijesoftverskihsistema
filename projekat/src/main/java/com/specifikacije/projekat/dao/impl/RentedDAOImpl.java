package com.specifikacije.projekat.dao.impl;

import com.specifikacije.projekat.dao.RentedDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.RealEstateService;
import com.specifikacije.projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@Repository
public class RentedDAOImpl implements RentedDAO {

    @Autowired
    private UserService userService;

    @Autowired
    private RealEstateService estateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class RentedCallBackHandler implements RowCallbackHandler {
        private Map<Long, Rented> rented = new LinkedHashMap<>();
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            int index = 1;
            Long id = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            Long estateId = rs.getLong(index++);
            LocalDate startDate = rs.getDate(index++).toLocalDate();
            LocalDate endDate = rs.getDate(index++).toLocalDate();

            User user = userService.findOne(userId);
            RealEstate estate = estateService.findOne(estateId);

            Rented rentedEstate = rented.get(id);
            if (rentedEstate == null) {
                rentedEstate = new Rented(id, user, estate, startDate, endDate);
                rented.put(rentedEstate.getId(), rentedEstate);
            }
        }

        public List<Rented> getRented() {
            return new ArrayList<>(rented.values());
        }
    }

    @Override
    public Rented findOne(Long id) {
        String sql = "select * from rented " +
                " where id = ? " +
                " order by id";
        RentedCallBackHandler handler = new RentedCallBackHandler();
        jdbcTemplate.query(sql, handler, id);
        return handler.getRented().get(0);
    }

    @Override
    public List<Rented> findAll() {
        String sql = "select * from rented " +
                " order by id";
        RentedCallBackHandler handler = new RentedCallBackHandler();
        jdbcTemplate.query(sql, handler);
        return handler.getRented();
    }

    @Transactional
    @Override
    public void save(Rented rented) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {


            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO rented (user_id, estate_id, start_date, end_date) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setLong(index++, rented.getUser().getId());
                preparedStatement.setLong(index++, rented.getEstate().getId());
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rented.getStartDate()));
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rented.getEndDate()));
                return preparedStatement;
            }
        };

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean success = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
    }

    @Transactional
    @Override
    public void update(Rented rented) {
        String sql = "update rented set user_id = ?, estate_id = ?, start_date = ?, end_date = ? where id = ? ";
        jdbcTemplate.update(sql, rented.getUser().getId(), rented.getEstate().getId(), rented.getStartDate(), rented.getEndDate(), rented.getId());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        String sql = "delete from rented where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean rentedExists(RealEstate estate) {
        String sql = "SELECT COUNT(*) > 0 AS rented_exists " +
                "FROM rented " +
                "WHERE estate_id = ? " +
                "AND start_date <= CURRENT_DATE " +
                "AND end_date >= CURRENT_DATE";


        return jdbcTemplate.queryForObject(sql, Boolean.class, estate.getId());
    }

    @Override
    public boolean rentedExists(RealEstate estate, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) > 0 AS rented_exists " +
                "FROM rented " +
                "WHERE estate_id = ? " +
                "AND ((start_date < ? AND end_date > ?) OR (start_date < ? AND end_date > ?))";

        return jdbcTemplate.queryForObject(sql, Boolean.class, estate.getId(), endDate, startDate, startDate, endDate);
    }

    @Override
    public Rented findOneRequest(Long id) {
        String sql = "select * from rented_request " +
                " where id = ? " +
                " order by id";
        RentedCallBackHandler handler = new RentedCallBackHandler();
        jdbcTemplate.query(sql, handler, id);
        return handler.getRented().get(0);
    }

    @Override
    public List<Rented> findAllRequests() {
        String sql = "select * from rented_request " +
                " order by id";
        RentedCallBackHandler handler = new RentedCallBackHandler();
        jdbcTemplate.query(sql, handler);
        return handler.getRented();
    }

    @Transactional
    @Override
    public void saveRequest(Rented rented) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {


            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO rented_request (user_id, estate_id, start_date, end_date) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setLong(index++, rented.getUser().getId());
                preparedStatement.setLong(index++, rented.getEstate().getId());
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rented.getStartDate()));
                preparedStatement.setDate(index++, java.sql.Date.valueOf(rented.getEndDate()));
                return preparedStatement;
            }
        };

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean success = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
    }

    @Transactional
    @Override
    public void updateRequest(Rented rented) {
        String sql = "update rented_request set user_id = ?, estate_id = ?, start_date = ?, end_date = ? where id = ? ";
        jdbcTemplate.update(sql, rented.getUser().getId(), rented.getEstate().getId(), rented.getStartDate(), rented.getEndDate(), rented.getId());
    }

    @Transactional
    @Override
    public void deleteRequest(Long id) {
        String sql = "delete from rented_request where id = ?";
        jdbcTemplate.update(sql, id);
    }


    @Override
    public boolean rentedRequestExists(RealEstate estate) {
        String sql = "SELECT COUNT(*) > 0 AS rented_exists " +
                "FROM rented_request " +
                "WHERE estate_id = ? " +
                "AND start_date <= CURRENT_DATE " +
                "AND end_date >= CURRENT_DATE";


        return jdbcTemplate.queryForObject(sql, Boolean.class, estate.getId());
    }

    @Override
    public boolean rentedRequestExists(RealEstate estate, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) > 0 AS rented_exists " +
                "FROM rented_request " +
                "WHERE estate_id = ? " +
                "AND ((start_date < ? AND end_date > ?) OR (start_date < ? AND end_date > ?))";

        return jdbcTemplate.queryForObject(sql, Boolean.class, estate.getId(), endDate, startDate, startDate, endDate);
    }
}
