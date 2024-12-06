package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() {
		Film film = db.findFilmById((int) (Math.random() * 1000));
		displayFilmInfo(film);
		System.out.println();

		Actor actor = db.findActorById((int) (Math.random() * 100));
		displayActorInfo(actor);
		System.out.println();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

	private void displayFilmInfo(Film film) {
		System.out.println(film.getTitle() + " (" + film.getReleaseYear() + ") | " + film.getLanguage() + " | "
		+ film.getLength() +" minutes | Rated " + film.getRating());
		System.out.println("\n" + film.getDescription());
		displayActorsInFilm(film);
		System.out.println("\nSpecial Features: " + film.getSpecialFeatures());
		System.out.println("\nFilm Id: " + film.getId() + " | Rental Rate: $" + film.getRentalRate() 
		+ " | Rental Duration: " + film.getRentalDuration() + " days | Replacement Cost: $" + film.getReplacementCost());
		System.out.println("-----------------------------------------------------------------------------------------------");
	}

	private void displayActorInfo(Actor actor) {
		System.out.println(actor.getFirstName() + " " + actor.getLastName() + " | Actor Id: " + actor.getId());
	}

	private void displayActorsInFilm(Film film) {
		System.out.println("\nCast List: ");
		for (Actor actor : film.getActors()) {
			System.out.println("- " + actor.getFirstName() + " " + actor.getLastName());
		}
	}
}
