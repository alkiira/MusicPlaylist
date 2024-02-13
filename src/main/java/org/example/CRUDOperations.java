package org.example;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class CRUDOperations {
    public void createSong(Song song) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO songs (title, artist, album, duration) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getAlbum());
            statement.setInt(4, song.getDuration());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Song readSong(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM songs WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Song(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("artist"),
                        resultSet.getString("album"),
                        resultSet.getInt("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSong(Song song) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE songs SET title=?, artist=?, album=?, duration=? WHERE id=?")) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getAlbum());
            statement.setInt(4, song.getDuration());
            statement.setInt(5, song.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSong(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM songs WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, surname, gender) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getGender());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User readUser(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean login(String username, String password) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If there's a match, resultSet.next() will return true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static final String SELECT_ALL_SONGS_QUERY = "SELECT * FROM songs";

    public static List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SONGS_QUERY)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                String album = resultSet.getString("album");
                int duration = resultSet.getInt("duration");
                Song song = new Song(id, title, artist, album, duration);
                allSongs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSongs;
    }
}

