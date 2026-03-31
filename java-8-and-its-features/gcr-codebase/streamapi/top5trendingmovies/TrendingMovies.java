package top5trendingmovies;

	import java.util.*;
	import java.util.stream.Collectors;

	class Movie {
	    String title;
	    double rating;
	    int releaseYear;

	    Movie(String title, double rating, int releaseYear) {
	        this.title = title;
	        this.rating = rating;
	        this.releaseYear = releaseYear;
	    }

	    public String getTitle() { return title; }
	    public double getRating() { return rating; }
	    public int getReleaseYear() { return releaseYear; }

	    @Override
	    public String toString() {
	        return title + " (" + releaseYear + ") - Rating: " + rating;
	    }
	}

	public class TrendingMovies {
	    public static void main(String[] args) {
	        List<Movie> movies = Arrays.asList(
	            new Movie("Movie A", 8.5, 2024),
	            new Movie("Movie B", 9.0, 2025),
	            new Movie("Movie C", 7.8, 2023),
	            new Movie("Movie D", 8.9, 2025),
	            new Movie("Movie E", 9.2, 2025),
	            new Movie("Movie F", 8.0, 2022),
	            new Movie("Movie G", 9.1, 2025)
	        );

	        // Filter recent movies (e.g., released in 2023 or later)
	        // Sort by rating (desc), then release year (desc)
	        // Limit to top 5
	        List<Movie> top5Trending = movies.stream()
	            .filter(m -> m.getReleaseYear() >= 2023) // only recent movies
	            .sorted(Comparator.comparing(Movie::getRating).reversed()
	                              .thenComparing(Movie::getReleaseYear).reversed())
	            .limit(5)
	            .collect(Collectors.toList());

	        // Print result
	        System.out.println("Top 5 Trending Movies:");
	        top5Trending.forEach(System.out::println);
	    }
	}