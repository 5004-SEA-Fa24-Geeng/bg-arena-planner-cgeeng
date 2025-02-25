package student;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import student.GameList;
import student.IGameList;



public class TestGameList {

    static Set<BoardGame> games;

    @TempDir
    static Path tempDir;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));

    }

    @Test
    public void testAddToList() {
        IGameList list = new GameList();

        list.addToList("1", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        assertEquals(1, list.count());

        list.addToList("1-3", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        assertEquals(3, list.count());

        list.addToList("Monopoly", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        assertEquals(4, list.count());

        list.addToList("all", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));

        assertEquals(8, list.count());



    }

    @Test
    public void testClear() {
        IGameList list = new GameList();
        list.addToList("1", games.stream());
        assertEquals(1, list.count());
        list.clear();
        assertEquals(0, list.count());
    }

    @Test
    public void testRemoveFromList() {
        IGameList list = new GameList();
        list.addToList("1-3", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        assertEquals(3, list.count());
        list.removeFromList("1");
        assertEquals(2, list.count());
        list.removeFromList("1-2");
        assertEquals(0, list.count());

        list.addToList("1-3", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        list.removeFromList("1-100");
        assertEquals(0, list.count());

        list.addToList("Go", games.stream());
        list.removeFromList("Go");
        assertEquals(0, list.count());

    }

    @Test
    void testSave() {

        Path path = tempDir.resolve("games.txt");
        IGameList list = new GameList();
        list.addToList("1-3", games.stream()
                .sorted((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase())));
        list.saveGame(path.toString());

        try {
            String actual = Files.readString(path);
            String expected = "17 days\nChess\nGo\n";
            assertEquals(expected.replaceAll("\\s", ""), actual.replaceAll("\\s", ""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testThrowsArgException() {
        IGameList list = new GameList();

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToList("100-2", games.stream());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.removeFromList("Go Fish Waka");
        });

    }

}
