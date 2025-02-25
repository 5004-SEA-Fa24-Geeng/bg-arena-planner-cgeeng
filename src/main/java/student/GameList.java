package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An implementation of GameList. This is what students will write.
 */
public class GameList implements IGameList {
    /** delim for #-# formatting. */
    private static final String DELIMITER = "-";
    /** used for any issue. */
    private static final String INVALID_FEEDBACK = "Invalid range";

    /** used for keeping track of the list - since it can remain sorted. */
    private final Set<BoardGame> games;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        games = new TreeSet<>(new Sorts.NameAscending());
    }

    @Override
    public List<String> getGameNames() {
        return games.stream().map(BoardGame::getName).toList();
    }

    @Override
    public void clear() {
        games.clear();
    }

    @Override
    public void saveGame(String filename) {
        try {
            Files.write(Path.of(filename), getGameNames());
        } catch (IOException e) {
            System.err.println(filename + " could not be written to.");
        }
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        List<BoardGame> list = filtered.collect(Collectors.toList());
        Optional<BoardGame> game = checkToSeeIfGameExists(str, list.stream());
        if (game.isPresent()) {
            games.add(game.get());
            return;
        }
        if (str.equalsIgnoreCase(ADD_ALL)) {
            list.forEach(games::add);
            return;
        }

        int[] range = parseRange(str, list.size());
        int start = range[0];
        int end = range[1];

        for (int i = start; i < end; i++) {
            games.add(list.get(i));
        }

    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        Optional<BoardGame> game = checkToSeeIfGameExists(str, games.stream());
        if (game.isPresent()) {
            games.remove(game.get());
            return;
        }

        if (str.equalsIgnoreCase(ADD_ALL)) {
            clear();
            return;
        }

        int[] range = parseRange(str, games.size());
        int start = range[0];
        int end = range[1];
        List<BoardGame> list = games.stream().collect(Collectors.toList());

        for (int i = start; i < end; i++) {
            games.remove(list.get(i));
        }
    }

    /**
     * Parses the range from the string.
     *
     * @param str the string to parse
     * @param max the max value
     * @return the range as an array of start, end
     * @throws IllegalArgumentException
     */
    private int[] parseRange(String str, int max) throws IllegalArgumentException {
        int start = 0;
        int end = max;

        String[] parts = str.split(DELIMITER);
        try {
            start = Integer.parseInt(parts[0]) - 1;
            if (start < 0 || start >= max) {
                throw new IllegalArgumentException(INVALID_FEEDBACK);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FEEDBACK);
        }

        if (parts.length == 2) {
            try {
                int tmp = Integer.parseInt(parts[1]);
                if (tmp < start) {
                    throw new IllegalArgumentException(INVALID_FEEDBACK);
                } else if (tmp < end) {
                    end = tmp;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INVALID_FEEDBACK);
            }
        } else {
            end = start + 1;
        }

        return new int[] {
                start, end
        };
    }

    /**
     * Checks to see if the game exists in the list.
     *
     * @param str      the string to check
     * @param filtered the filtered stream
     * @return the game if it exists
     */
    private Optional<BoardGame> checkToSeeIfGameExists(String str, Stream<BoardGame> filtered) {
        return filtered.filter(game -> game.getName().equalsIgnoreCase(str)).findFirst();
    }

    @Override
    public int count() {
        return games.size();
    }
}
