package student;


import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    Set<BoardGame> gamesList;

    public Planner(Set<BoardGame> games) {
        this.gamesList = games;
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        //filter for name == "Go"
        Stream<BoardGame> filteredStream = filterSingle(filter, gamesList.stream());
        return filteredStream;
    }

    private Stream<BoardGame> filterSingle(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        // remove spaces
        filter = filter.replaceAll(" ", "");

        String[] parts = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredGames;
        }
        GameData column;
        try {
            column = GameData.fromString(parts[0].trim());
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }
        String value;
        try {
            value = parts[1].trim();
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }
        System.out.print("Operator is: " + operator);
        System.out.print("Game is: " + column);
        System.out.print(column + " is: " + value);
        return filteredGames;
    }

    private Stream<BoardGame> filterString(Operations operator, GameData column, String value,
                                           Stream<BoardGame> filteredGames) {
        Stream<BoardGame> newFiltered = filteredGames.stream()
                .filter()
    }


    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return this.gamesList.stream();
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        return this.gamesList.stream();
    }

    @Override
    public void reset() {

    }


}
