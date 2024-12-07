package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Unable to load JDBC Driver.");
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = new Film();

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT * FROM film JOIN language ON film.language_id = language.id WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setLanguage(filmResult.getString("name"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
			}
			return film;
		}

		catch (SQLException e) {
			System.err.println("Error getting film " + filmId);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = new Actor();
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT * FROM actor WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actor.setId(actorResult.getInt("id"));
			}
			return actor;
		}

		catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT * FROM actor JOIN film_actor " + "ON actor.id = film_actor.actor_id "
					+ "JOIN film ON film.id = film_actor.film_id WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setId(rs.getInt("id"));
				actors.add(actor);
			}
			return actors;
		}

		catch (SQLException e) {
			System.err.println("Error finding actor(s) in film" + filmId);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT * FROM film JOIN language ON language.id = film.language_id "
					+ "WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				Film film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setLanguage(filmResult.getString("name"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmResult.getInt("id")));
				films.add(film);
			}
		}

		catch (SQLException e) {
			System.err.println("Error finding films with " + keyword);
			e.printStackTrace();
		}
		return films;
	}

}
