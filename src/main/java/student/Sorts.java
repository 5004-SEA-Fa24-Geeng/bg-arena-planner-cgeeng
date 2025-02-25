package student;

import java.util.Comparator;

/**
 * Class to hold the sorting strategies for the BoardGame class.
 * 
 */
public final class Sorts {

    /**
     * Private constructor to prevent instantiation.
     */
    private Sorts() {
        // private constructor to prevent instantiation
    }

    /**
     * Get the sorting strategy based on the column and the order.
     * 
     * @param column    the column to sort on
     * @param ascending the order to sort in
     * @return the sorting strategy
     */
    public static Comparator<BoardGame> getSortStrategy(GameData column, boolean ascending) {
        switch (column) {
            case NAME:
                return ascending ? new NameAscending() : new NameDescending();

            case RATING:
                return ascending ? new RatingAscending() : new RatingDescending();

            case DIFFICULTY:
                return ascending ? new DifficultyAscending() : new DifficultyDescending();

            case RANK:
                return ascending ? new RankAscending() : new RankDescending();

            case MIN_PLAYERS:
                return ascending ? new MinPlayersAscending() : new MinPlayersDescending();

            case MAX_PLAYERS:
                return ascending ? new MaxPlayersAscending() : new MaxPlayersDescending();

            case MIN_TIME:
                return ascending ? new MinTimeAscending() : new MinTimeDescending();

            case MAX_TIME:
                return ascending ? new MaxTimeAscending() : new MaxTimeDescending();

            case YEAR:
                return ascending ? new YearAscending() : new YearDescending();

            default:
                throw new IllegalArgumentException("Invalid column");
        }
    }

    /**
     * Comparator to sort by name in ascending order.
     */
    public static class NameAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
        }
    }

    /**
     * Comparator to sort by name in descending order.
     */
    public static class NameDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return b.getName().toLowerCase().compareTo(a.getName().toLowerCase());
        }
    }

    /**
     * Comparator to sort by rating in ascending order.
     */
    public static class RatingAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Double.valueOf(a.getRating()).compareTo(Double.valueOf(b.getRating()));
        }
    }

    /**
     * Comparator to sort by rating in descending order.
     */
    public static class RatingDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Double.valueOf(b.getRating()).compareTo(Double.valueOf(a.getRating()));
        }
    }

    /**
     * Comparator to sort by difficulty in ascending order.
     */
    public static class DifficultyAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Double.valueOf(a.getDifficulty()).compareTo(Double.valueOf(b.getDifficulty()));
        }
    }

    /**
     * Comparator to sort by difficulty in descending order.
     */
    public static class DifficultyDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Double.valueOf(b.getDifficulty()).compareTo(Double.valueOf(a.getDifficulty()));
        }
    }

    /**
     * Comparator to sort by rank in ascending order.
     */
    public static class RankAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getRank()).compareTo(Integer.valueOf(b.getRank()));
        }
    }

    /**
     * Comparator to sort by rank in descending order.
     */
    public static class RankDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getRank()).compareTo(Integer.valueOf(a.getRank()));
        }
    }

    /**
     * Comparator to sort by min players in ascending order.
     */
    public static class MinPlayersAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getMinPlayers()).compareTo(Integer.valueOf(b.getMinPlayers()));
        }
    }

    /**
     * Comparator to sort by min players in descending order.
     */
    public static class MinPlayersDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getMinPlayers()).compareTo(Integer.valueOf(a.getMinPlayers()));
        }
    }

    /**
     * Comparator to sort by max players in ascending order.
     */
    public static class MaxPlayersAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getMaxPlayers()).compareTo(Integer.valueOf(b.getMaxPlayers()));
        }
    }

    /**
     * Comparator to sort by max players in descending order.
     */
    public static class MaxPlayersDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getMaxPlayers()).compareTo(Integer.valueOf(a.getMaxPlayers()));
        }
    }

    /**
     * Comparator to sort by min play time in ascending order.
     */
    public static class MinTimeAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getMinPlayTime())
                    .compareTo(Integer.valueOf(b.getMinPlayTime()));
        }
    }

    /**
     * Comparator to sort by min play time in descending order.
     */
    public static class MinTimeDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getMinPlayTime())
                    .compareTo(Integer.valueOf(a.getMinPlayTime()));
        }
    }

    /**
     * Comparator to sort by max play time in ascending order.
     */
    public static class MaxTimeAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getMaxPlayTime())
                    .compareTo(Integer.valueOf(b.getMaxPlayTime()));
        }
    }

    /**
     * Comparator to sort by max play time in descending order.
     */
    public static class MaxTimeDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getMaxPlayTime())
                    .compareTo(Integer.valueOf(a.getMaxPlayTime()));
        }
    }

    /**
     * Comparator to sort by year published in ascending order.
     */
    public static class YearAscending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(a.getYearPublished())
                    .compareTo(Integer.valueOf(b.getYearPublished()));
        }
    }

    /**
     * Comparator to sort by year published in descending order.
     */
    public static class YearDescending implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame a, BoardGame b) {
            return Integer.valueOf(b.getYearPublished())
                    .compareTo(Integer.valueOf(a.getYearPublished()));
        }
    }

}
