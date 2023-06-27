import models.db_book_model.Books;
import models.db_book_model.booksSchema;
import org.hibernate.exception.SQLGrammarException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Browsing {
    // Get all books currently in the database
    public static List<Books> getBooks() {
        try {
            return Books.query().list();
        } catch (SQLGrammarException e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Books> getBooksStartingAt(int position) {
        List<Books> selection = new ArrayList<>();
        List<Books> booksQueryResult = getBooks();
        int booksQueryLength = booksQueryResult.size();

        if (booksQueryResult != null) {
            if (booksQueryLength >= position) {
                int left = position - 1;
                int right = left + position;
                if (right > booksQueryLength - 1) {
                    // set right to be the end of the list
                    right = booksQueryLength - 1;
                }

                for (int idx = left; idx <= right; idx++) {
                    selection.add(booksQueryResult.get(idx));
                }

                return selection;
            } else {
                return null;
            }
        } else {
            return booksQueryResult;
        }
    }

    public static List<Books> getBooksByGenre(String genre) {
        List<Books> books = booksSchema.dump(getBooks());
        List<Books> res = new ArrayList<>();
        Map<String, Integer> genres = new HashMap<>();
        genres.put("!Error", String.format("No books found for genre '%s'. Here are the available genres:", genre));

        for (Books book : books) {
            String bookGenre = book.getBookGenre();
            String bookGenreTitled = bookGenre.substring(0, 1).toUpperCase() + bookGenre.substring(1);

            if (!genres.containsKey(bookGenreTitled)) {
                genres.put(bookGenreTitled, 1);
            } else {
                genres.put(bookGenreTitled, genres.get(bookGenreTitled) + 1);
            }

            if (bookGenre.equals(genre)) {
                res.add(book);
            }
        }

        if (res.size() > 0) {
            return res;
        } else {
            return genres;
        }
    }

    public static List<Books> getBooksWithRatingAtOrAbove(float rating) {
        List<Books> books = booksSchema.dump(getBooks());
        List<Books> res = new ArrayList<>();
        for (Books book : books) {
            if (book.getBookRating() >= rating) {
                res.add(book);
            }
        }
        return res;
    }

    public static Map<String, Object> getOptions() {
        String root = "http://localhost:81/book-browsing-sorting";
        Map<String, Object> options = new HashMap<>();
        Map<String, Object> endpoints = new HashMap<>();
        Map<String, Object> endpoint = new HashMap<>();
        Map<String, Object> description = new HashMap<>();
        Map<String, Object> examples = new HashMap<>();

        // Root endpoint
        endpoint.put("URL", root);
        endpoint.put("methods", "GET, OPTIONS");

        description.put("GET", "Returns text validating connectivity");
        description.put("OPTIONS", "Returns the endpoints and instructions of this API.");

        examples.put("Check connectivity", String.format("GET %s", root));
        examples.put("Obtain endpoints", String.format("OPTIONS %s", root));

        endpoint.put("Description", description);
        endpoint.put("Examples", examples);

        endpoints.put("/", endpoint);

        // Get books endpoint
        endpoint = new HashMap<>();
        description = new HashMap<>();
        examples = new HashMap<>();

        endpoint.put("URL", String.format("%s/get-books", root));
        endpoint.put("methods", "GET");

        description.put("Description", "Returns all books in the database if no parameters are provided. "
                + "Provide the parameter <quantity>(int) to obtain N amount of books starting at N position in the database. "
                + "Provide the parameter <genre> to obtain the books categorized as the provided genre.");

        Map<String, String> params = new HashMap<>();
        params.put("quantity", "Amount of books requested beginning at the database's <quantity> position.");
        endpoint.put("Params", params);

        examples.put("Get all books", String.format("GET %s/get-books", root));
        examples.put("Get n amount of books", String.format("GET %s/get-books?quantity=10", root));

        endpoint.put("Description", description);
        endpoint.put("Examples", examples);

        endpoints.put("get-books", endpoint);

        // Top ten endpoint
        endpoint = new HashMap<>();
        description = new HashMap<>();
        examples = new HashMap<>();

        endpoint.put("URL", String.format("%s/top-ten", root));
        endpoint.put("methods", "GET");

        description.put("Description", "Returns the top-ten sold books.");
        endpoint.put("Params", new HashMap<>());

        examples.put("Get top-ten sold books", String.format("GET %s/top-ten", root));

        endpoint.put("Description", description);
        endpoint.put("Examples", examples);

        endpoints.put("top-ten", endpoint);

        // By genre endpoint
        endpoint = new HashMap<>();
        description = new HashMap<>();
        examples = new HashMap<>();

        endpoint.put("URL", String.format("%s/by-genre", root));
        endpoint.put("methods", "GET");

        description.put("Description", "Provide the parameter <genre> to obtain the books categorized in the provided genre.");

        params = new HashMap<>();
        params.put("genre", "Book genre to be searched.");
        endpoint.put("Params", params);

        examples.put("Get books by genre", String.format("GET %s/genre?genre=sci-fi", root));

        endpoint.put("Description", description);
        endpoint.put("Examples", examples);

        endpoints.put("by-genre", endpoint);

        // By rating endpoint
        endpoint = new HashMap<>();
        description = new HashMap<>();
        examples = new HashMap<>();

        endpoint.put("URL", String.format("%s/by-rating", root));
        endpoint.put("methods", "GET");

        description.put("Description", "Provide the parameter <rating> to obtain the books that match the provided rating and higher.");

        params = new HashMap<>();
        params.put("rating", "Float. Books that have a rating at or above the provided rating will be returned.");
        endpoint.put("Params", params);

        examples.put("Get books by rating", String.format("GET %s/by-rating?rating=4.5", root));

        endpoint.put("Description", description);
        endpoint.put("Examples", examples);

        endpoints.put("by-rating", endpoint);

        options.put("endpoints", endpoints);
        return options;
    }
}

