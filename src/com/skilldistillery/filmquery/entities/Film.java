package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private String language;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	private String category;
	private int storeId;
	private int inventoryId;
	private String mediaCondition;
	private List<Film> inventoryCondition;
	
	public Film() {
	}
	
	public Film(int id, String title, String description, int releaseYear, int languageId, String language,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures, List<Actor> actors, String category, int storeId, int inventoryId,
			String mediaCondition) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.actors = actors;
		this.category = category;
		this.storeId = storeId;
		this.inventoryId = inventoryId;
		this.mediaCondition = mediaCondition;
	}

	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getTitle(Integer filmId) {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getMediaCondition() {
		return mediaCondition;
	}

	public void setMediaCondition(String mediaCondition) {
		this.mediaCondition = mediaCondition;
	}

	public List<Film> getInventoryCondition() {
		return inventoryCondition;
	}
	
	public void setInventoryCondition(List<Film> inventoryCondition) {
		this.inventoryCondition = inventoryCondition;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, category, description, id, inventoryCondition, inventoryId, language, languageId,
				length, mediaCondition, rating, releaseYear, rentalDuration, rentalRate, replacementCost,
				specialFeatures, storeId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(category, other.category)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(inventoryCondition, other.inventoryCondition) && inventoryId == other.inventoryId
				&& Objects.equals(language, other.language) && languageId == other.languageId && length == other.length
				&& Objects.equals(mediaCondition, other.mediaCondition) && Objects.equals(rating, other.rating)
				&& releaseYear == other.releaseYear && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && storeId == other.storeId
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", language=" + language + ", rentalDuration=" + rentalDuration
				+ ", rentalRate=" + rentalRate + ", length=" + length + ", replacementCost=" + replacementCost
				+ ", rating=" + rating + ", specialFeatures=" + specialFeatures + ", actors=" + actors + ", category="
				+ category + ", storeId=" + storeId + ", inventoryId=" + inventoryId + ", mediaCondition="
				+ mediaCondition + ", inventoryCondition=" + inventoryCondition + "]";
	}
	
	
}
