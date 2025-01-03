package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
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
//		app.test();
		app.launch();
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
		boolean keepGoing = true;
		System.out.println("Welcome to SD Vid!");

		while (keepGoing) {
			displayUserMenu();
			String userChoice = input.nextLine();

			switch (userChoice) {
			case "1":
				case1(input);
				break;

			case "2":
				case2(input);
				break;

			case "3":
				System.out.println("\nThanks for visiting SD Vid! Goodbye.");
				keepGoing = false;
				break;

			default:
				System.out.println("Invalid input.\n");
			}
		}
	}

	private void case1(Scanner input) {
		System.out.println("\nPlease enter the film id: ");
		try {
			int filmId = input.nextInt();
			input.nextLine();
			Film film = db.findFilmById(filmId);

			if (film == null) {
				System.out.println("\nFilm not found.\n");
			}

			else {
				displayFilmInfo(film);
				filmSubMenu();
				String choice = input.nextLine();
				subMenuChoice(choice, film);
			}
		} catch (InputMismatchException ime) {
			System.out.println("Invalid input.\n");
			input.nextLine();
		}
	}

	private void case2(Scanner input) {
		System.out.println("\nPlease enter a keyword to search: ");
		String keyword = input.nextLine();
		List<Film> films = db.findFilmByKeyword(keyword);
		if (films.isEmpty()) {
			System.out.println("\nThere are no films containing the keyword " + keyword + ".\n");
			return;
		}
		System.out.println("\nFilms containing \"" + keyword + "\": ");
		displayMultipleFilms(films);
		filmSubMenu();
		String choice = input.nextLine();
		subMenuChoice(choice, films);
	}

	private void subMenuChoice(String choice, Film film) {
		switch (choice) {
		case "1":
			break;
		case "2":
			displayFullFilmInfo(film);
			displayInventoryAndCondition(film);
			break;
		}
	}

	private void subMenuChoice(String choice, List<Film> films) {
		switch (choice) {
		case "1":
			break;
		case "2":
			for (Film film : films) {
				displayFullFilmInfo(film);					
				displayInventoryAndCondition(films);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			break;
		default:
			System.out.println("Invalid input.");
		}
	}

	private void displayUserMenu() {
		System.out.println("..........................................");
		System.out.println(".  What would you like to do today?      .");
		System.out.println(".                                        .");
		System.out.println(". 1. Look up a film by film id           .");
		System.out.println(". 2. Look up a film by a search keyword  .");
		System.out.println(". 3. Exit application                    .");
		System.out.println(".                                        .");
		System.out.println("..........................................");
	}

	private void displayFilmInfo(Film film) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println(film.getTitle() + " (" + film.getReleaseYear() + ") | " + film.getLanguage() + " | Rated "
				+ film.getRating());
		System.out.println("\n" + film.getDescription());
		displayActorsInFilm(film);
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
	}

	private void displayMultipleFilms(List<Film> films) {
		for (Film film : films) {
			displayFilmInfo(film);
		}
	}

	private void displayFullFilmInfo(Film film) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println(film.getTitle() + " (" + film.getReleaseYear() + ") | " + film.getCategory() +
				" | " + film.getLanguage() + " | " + film.getLength() + " minutes | Rated " + film.getRating());
		System.out.println("\n" + film.getDescription());
		displayActorsInFilm(film);
		System.out.println("\nSpecial Features: " + film.getSpecialFeatures());
		System.out.println(
				"\nFilm Id: " + film.getId() + " | Rental Rate: $" + film.getRentalRate() + " | Rental Duration: "
						+ film.getRentalDuration() + " days | Replacement Cost: $" + film.getReplacementCost());
//		displayInventoryAndCondition(film);
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
	
	private void displayInventoryAndCondition (Film indFilm) {
		System.out.println("\nInventory & Condition\n"); 
		for (Film film : indFilm.getInventoryCondition()) {
			System.out.println("Store Id: " + film.getStoreId() + " | Media Condition: "
					+ film.getMediaCondition());
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
	}
	
	
	private void displayInventoryAndCondition (List<Film> films) {
		System.out.println("\nInventory & Condition\n"); 
			for (Film film : films) {
			System.out.println("Store Id: " + film.getStoreId() + " | Media Condition: "
					+ film.getMediaCondition());
		}
	}
	
	private void filmSubMenu() {
		System.out.println("\nEnter \"1\" to return to the main menu.");
		System.out.println("Enter \"2\" to see all film details.");
	}
}
