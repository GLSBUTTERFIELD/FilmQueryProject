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
		Film film = null;

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT film.id, title, description, release_year, "
					+ "language_id, language.name AS 'language', rental_duration, "
					+ "rental_rate, length, replacement_cost, rating, special_features, "
					+ "category.name AS 'category' "
					+ "FROM film JOIN language ON film.language_id = language.id "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON category_id = category.id "
					+ "WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setLanguage(filmResult.getString("language"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
				film.setCategory(filmResult.getString("category"));
				film.setInventoryCondition(getInventoryCondition(filmId));
			}
		}

		catch (SQLException e) {
			System.err.println("Error getting film " + filmId);
			e.printStackTrace();
		}
		return film;
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
		}

		catch (SQLException e) {
			System.err.println(e);
		}
		return actor;
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
		}

		catch (SQLException e) {
			System.err.println("Error finding actor(s) in film" + filmId);
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT film.id AS 'film id', title, description, release_year, "
					+ "language_id, language.name AS 'language', rental_duration, "
					+ "rental_rate, length, replacement_cost, rating, special_features, "
					+ "category.name AS 'category' "
					+ "FROM film JOIN language ON film.language_id = language.id "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON category_id = category.id "
					+ "WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				Film film = new Film();
				film.setId(filmResult.getInt("film id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setLanguage(filmResult.getString("language"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmResult.getInt("film id")));
				film.setCategory(filmResult.getString("category"));
				film.setInventoryCondition(getInventoryCondition(filmResult.getInt("film id")));
				films.add(film);
			}
		}

		catch (SQLException e) {
			System.err.println("Error finding films with " + keyword);
			e.printStackTrace();
		}
		return films;
	}

	
//	public List<Film> findFilmAndInventoryByKeyword(String keyword) {
//		List<Film> films = new ArrayList<>();
//		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
//			String sqlText = "SELECT film.id AS 'film id', title, description, release_year, "
//					+ "language_id, language.name AS 'language', rental_duration, "
//					+ "rental_rate, length, replacement_cost, rating, special_features, "
//					+ "category.name AS 'category', store_id, media_condition "
//					+ "FROM film JOIN language ON film.language_id = language.id "
//					+ "JOIN film_category ON film.id = film_category.film_id "
//					+ "JOIN category ON category_id = category.id "
//					+ "JOIN inventory_item ON film.id = inventory_item.film_id "
//					+ "WHERE title LIKE ? OR description LIKE ? ORDER BY store_id";
//			PreparedStatement stmt = conn.prepareStatement(sqlText);
//			stmt.setString(1, "%" + keyword + "%");
//			stmt.setString(2, "%" + keyword + "%");
//			ResultSet filmResult = stmt.executeQuery();
//
//			while (filmResult.next()) {
//				Film film = new Film();
//				film.setId(filmResult.getInt("film id"));
//				film.setTitle(filmResult.getString("title"));
//				film.setDescription(filmResult.getString("description"));
//				film.setReleaseYear(filmResult.getInt("release_year"));
//				film.setLanguageId(filmResult.getInt("language_id"));
//				film.setLanguage(filmResult.getString("language"));
//				film.setRentalDuration(filmResult.getInt("rental_duration"));
//				film.setRentalRate(filmResult.getDouble("rental_rate"));
//				film.setLength(filmResult.getInt("length"));
//				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
//				film.setRating(filmResult.getString("rating"));
//				film.setSpecialFeatures(filmResult.getString("special_features"));
//				film.setActors(findActorsByFilmId(filmResult.getInt("film id")));
//				film.setCategory(filmResult.getString("category"));
//				film.setStoreId(filmResult.getInt("store_id"));
//				film.setMediaCondition(filmResult.getString("media_condition"));
//				films.add(film);
//			}
//		}
//
//		catch (SQLException e) {
//			System.err.println("Error finding films with " + keyword);
//			e.printStackTrace();
//		}
//		return films;
//	}

	
	public List<Film> getInventoryCondition (int filmId) {
		List<Film> filmInventory = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sqlText = "SELECT * FROM inventory_item WHERE film_id=? ORDER BY store_id";
			PreparedStatement stmt = conn.prepareStatement(sqlText);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setStoreId(rs.getInt("store_id"));
				film.setMediaCondition(rs.getString("media_condition"));
				filmInventory.add(film);
			}
		}

		catch (SQLException e) {
			System.err.println("Error finding inventory information for " + filmId);
			e.printStackTrace();
		}

		return filmInventory;
	}
}
