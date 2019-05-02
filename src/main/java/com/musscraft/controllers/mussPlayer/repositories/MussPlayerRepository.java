package com.musscraft.controllers.mussPlayer.repositories;

import com.musscraft.controllers.mussPlayer.exceptions.MussPlayerNotExistsException;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import com.musscraft.database.ConnectionFactory;
import com.musscraft.utils.LocationSerializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MussPlayerRepository {
    private static final String MUSS_PLAYERS_TABLE = "MussPlayers";

    private Connection connection;

    public MussPlayerRepository() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public MussPlayer add(MussPlayer mussPlayer) {
        String sql = createSql("INSERT INTO _TABLE_(UID, username, password, email, money, experience, logged)" +
                "VALUES (?,?,?,?,?,?,?);");

        UUID mussPlayerUUID = UUID.randomUUID();

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, mussPlayerUUID.toString());
            preparedStatement.setString(2, mussPlayer.getUsername());
            preparedStatement.setString(3, mussPlayer.getPassword());
            preparedStatement.setString(4, mussPlayer.getEmail());
            preparedStatement.setDouble(5, mussPlayer.getMoney());
            preparedStatement.setDouble(6, mussPlayer.getExperience());
            preparedStatement.setBoolean(7, mussPlayer.isLogged());

            preparedStatement.executeUpdate();

            mussPlayer.setUid(mussPlayerUUID);

            return mussPlayer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MussPlayer get(String username) throws MussPlayerNotExistsException {
        String sql = createSql("SELECT * FROM _TABLE_ WHERE username = ?");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                MussPlayer mussPlayer = new MussPlayer(username);

                mussPlayer.setUid(UUID.fromString(resultSet.getString("UID")));
                mussPlayer.setUsername(resultSet.getString("username"));
                mussPlayer.setPassword(resultSet.getString("password"));
                mussPlayer.setEmail(resultSet.getString("email"));
                mussPlayer.setMoney(resultSet.getDouble("money"));
                mussPlayer.setExperience(resultSet.getDouble("experience"));
                mussPlayer.setLocation(
                        LocationSerializer.getDeserializedLocation(resultSet.getString("location"))
                );

                return mussPlayer;
            }

            throw new MussPlayerNotExistsException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MussPlayer> getAll() throws MussPlayerNotExistsException {
        List<MussPlayer> listAllMussPlayers = new ArrayList<MussPlayer>();
        String sql = createSql("SELECT * FROM _TABLE_");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                listAllMussPlayers.add(get(resultSet.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAllMussPlayers;
    }

    public boolean exists(String username) {
        String sql = createSql("SELECT * FROM _TABLE_ WHERE username = ?");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void remove(MussPlayer mussPlayer) throws MussPlayerNotExistsException {
        if (!exists(mussPlayer.getUsername()))
            throw new MussPlayerNotExistsException();

        String sql = createSql("DELETE FROM _TABLE_ WHERE UID = ?");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mussPlayer.getUid().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrUpdate(MussPlayer mussPlayer) {
        String sql = createSql("UPDATE _TABLE_ SET username=?, password=?, email=?, money=?, experience=?, logged=?, location=? WHERE UID=?");

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, mussPlayer.getUsername());
            preparedStatement.setString(2, mussPlayer.getPassword());
            preparedStatement.setString(3, mussPlayer.getEmail());
            preparedStatement.setDouble(4, mussPlayer.getMoney());
            preparedStatement.setDouble(5, mussPlayer.getExperience());
            preparedStatement.setBoolean(6, mussPlayer.isLogged());
            preparedStatement.setString(7, LocationSerializer.serializeLocation(mussPlayer.getLocation()));
            preparedStatement.setString(8, mussPlayer.getUid().toString());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSql(String sql) {
        return sql.replaceAll("_TABLE_", MUSS_PLAYERS_TABLE);
    }
}
