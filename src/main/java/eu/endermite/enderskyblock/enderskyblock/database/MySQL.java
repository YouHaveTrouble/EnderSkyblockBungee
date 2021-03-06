package eu.endermite.enderskyblock.enderskyblock.database;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import eu.endermite.enderskyblock.enderskyblock.debug.Debug;

import java.sql.*;
import java.util.UUID;

public class MySQL {

    private static Connection connection;

    final static String host = EnderSkyblock.getConfig().getMysqlHost();
    final static String password = EnderSkyblock.getConfig().getMysqlPassword();
    final static String database = EnderSkyblock.getConfig().getMysqlDatabase();
    final static String username = EnderSkyblock.getConfig().getMysqlUsername();
    final static int port = EnderSkyblock.getConfig().getMysqlPort();


    /**
     * @return boolean for checking if database connection was established
     */
    public static boolean startConnection() {
        try {
            if (EnderSkyblock.getConfig().getMysqlSsl()) {
                String connectionString = "jdbc:mysql://" + host + ":" + port+"/" + database + "?user="+ username+"&password="+password+"&useSSL=false";
                MySQL.connection = DriverManager.getConnection(connectionString);
                Debug.debugMessage("Successfully connected to MySQL!");
                return true;
            } else {
                String connectionString = "jdbc:mysql://" + host + ":" + port+"/" + database + "?user="+ username+"&password="+password+"&verifyServerCertificate=true&useSSL=true&requireSSL=true";
                MySQL.connection = DriverManager.getConnection(connectionString);
                Debug.debugMessage("Successfully connected to MySQL!");
                return true;
            }
        } catch (SQLException ex) {
            Debug.debugMessage("Could not connect to MySQL database!");
            return false;
        }
    }
    public static void stopConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param uuid Player's UUID
     * @return boolean that reflects uuids existance in database
     */
    public static boolean checkIfPlayerExists(UUID uuid) {
        try {
            String sql = "SELECT * FROM players WHERE player_uuid='" + uuid.toString() + "';";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if(!(rs.getString("player_uuid") ==null)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    /**
     *
     * @return boolean for checking if database tables were created properly and exist
     */
    public static boolean createTables() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS players(" +
                    "player_uuid VARCHAR(36) NOT NULL," +
                    "island_uuid VARCHAR(36));";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS server_data(" +
                    "server_type VARCHAR(36) NOT NULL," +
                    "server_bungee_name VARCHAR(256) NOT NULL," +
                    "islands_loaded VARCHAR(36));";
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS worlds_data(" +
                    "world_name VARCHAR(128) NOT NULL," +
                    "owner_uuid VARCHAR(36) NOT NULL," +
                    "loaded_on VARCHAR(256));";
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

            Debug.debugMessage("Successfully initialised database tables");
            return true;
        } catch (SQLException e) {
            Debug.debugMessage("Something went wrong with creating database tables.", e);
            return false;
        }
    }
}
