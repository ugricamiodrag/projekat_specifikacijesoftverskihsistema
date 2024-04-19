package com.specifikacije.projekat.dao.impl;

import com.specifikacije.projekat.dao.NotificationDAO;
import com.specifikacije.projekat.model.*;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.ScheduledTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AgentService agentService;

    @Autowired
    private ScheduledTourService tourService;

    private class NotificationCallBackHandler implements RowCallbackHandler {


        private Map<Long, Notification> notifications = new LinkedHashMap<>();


        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            Long agentId = resultSet.getLong(index++);
            Long tourId = resultSet.getLong(index++);
            Boolean isApproved = resultSet.getBoolean(index++);

            Agent agent = agentService.findOne(agentId);
            ScheduledTour tour = tourService.findOne(tourId);

            Notification notification = notifications.get(id);
            if(notification == null){
                notification = new Notification(id, agent, tour, isApproved);
                notifications.put(notification.getId(), notification);

            }
        }



        public List<Notification> getNotifications() {
            return new ArrayList<>(notifications.values());
        }
    }




    @Override
    public Notification findOne(Long id) {
        String sql = "select * from notifications" +
                " where id = ?" +
                " order by id";
        NotificationCallBackHandler handler = new NotificationCallBackHandler();
        jdbcTemplate.query(sql, handler, id);
        return handler.getNotifications().get(0);
    }

    @Override
    public List<Notification> findAll() {
        String sql = "select * from notifications order by is_read, id desc";
        NotificationCallBackHandler handler = new NotificationCallBackHandler();
        jdbcTemplate.query(sql, handler);
        return handler.getNotifications();
    }

    @Transactional
    @Override
    public void save(Notification notification) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO notifications (agent_id, tour_id, is_read) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setLong(index++, notification.getAgent().getId());
                preparedStatement.setLong(index++, notification.getTour().getId());
                preparedStatement.setBoolean(index++, notification.isRead());

                return preparedStatement;
            }

        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
    }

    @Transactional
    @Override
    public void update(Notification notification) {
        String sql = "update notifications set agent_id = ?, tour_id = ?, is_read = ? where id = ?";
        jdbcTemplate.update(sql, notification.getAgent().getId(), notification.getTour().getId(), notification.isRead(), notification.getId());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM notifications WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Notification> findByAgent(Long id) {
        String sql = "select * from notifications" +
                " where agent_id = ?" +
                " order by is_read, id desc";
        NotificationCallBackHandler handler = new NotificationCallBackHandler();
        jdbcTemplate.query(sql, handler, id);
        return handler.getNotifications();
    }
}
