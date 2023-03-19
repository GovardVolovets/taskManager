import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void testSimpleTaskMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Assertions.assertTrue(simpleTask.matches("родителям"));
        Assertions.assertFalse(simpleTask.matches("покупки"));
    }
    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertFalse(epic.matches("Сок"));
    }
    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(
                3,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Assertions.assertTrue(meeting.matches("версии"));
        Assertions.assertTrue(meeting.matches("НетоБанка"));
        Assertions.assertFalse(meeting.matches("покупки"));
    }
    @Test
    public void testTodosSearch() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Meeting meeting = new Meeting(
                3,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] searchResult1 = todos.search("родителям");
        Task[] expected1 = {simpleTask};
        Assertions.assertArrayEquals(expected1, searchResult1);

        Task[] searchResult2 = todos.search("Молоко");
        Task[] expected2 = {epic};
        Assertions.assertArrayEquals(expected2, searchResult2);

        Task[] searchResult3 = todos.search("версии");
        Task[] expected3 = {meeting};
        Assertions.assertArrayEquals(expected3, searchResult3);
    }
    @Test
    public void simpleTaskConstructorAndGetters() {
        SimpleTask task = new SimpleTask(1, "Сделать домашнее задание");
        Assertions.assertEquals(1, task.getId());
        Assertions.assertEquals("Сделать домашнее задание", task.getTitle());
    }
    @Test
    public void simpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Сделать домашнее задание");
        Assertions.assertTrue(task.matches("задание"));
        Assertions.assertFalse(task.matches("покупками"));
    }
    @Test
    public void epicConstructorAndGetters() {
        String[] subtasks = { "Помыть посуду", "Помыть машину", "Помыть пол" };
        Epic epic = new Epic(2, subtasks);
        Assertions.assertEquals(2, epic.getId());
        Assertions.assertArrayEquals(subtasks, epic.getSubtasks());
    }
    @Test
    public void epicMatches() {
        String[] subtasks = { "Помыть посуду", "Помыть машину", "Помыть пол" };
        Epic epic = new Epic(2, subtasks);
        Assertions.assertTrue(epic.matches("машину"));
        Assertions.assertFalse(epic.matches("собаку"));
    }
    @Test
    public void meetingConstructorAndGetters() {
        Meeting meeting = new Meeting(3, "Создать проект", "Приложение заданий", "19.03.2023");
        Assertions.assertEquals(3, meeting.getId());
        Assertions.assertEquals("Создать проект", meeting.getTopic());
        Assertions.assertEquals("Приложение заданий", meeting.getProject());
        Assertions.assertEquals("19.03.2023", meeting.getStart());
    }
    @Test
    public void meetingMatches() {
        Meeting meeting = new Meeting(3, "Создать проект", "Приложение заданий", "19.03.2023");
        Assertions.assertTrue(meeting.matches("проект"));
        Assertions.assertTrue(meeting.matches("Приложение заданий"));
        Assertions.assertFalse(meeting.matches("2022"));
    }
    @Test
    public void addAndFindAllTasks() {
        SimpleTask simpleTask = new SimpleTask(1, "Сделать домашнее задание");
        String[] subtasks = { "Помыть посуду", "Помыть машину", "Помыть пол" };
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Создать проект", "Приложение заданий", "19.03.2023");
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void simpleTaskMatchesQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Сделать домашнее задание");
        Assertions.assertTrue(task.matches("задание"));
        Assertions.assertFalse(task.matches("проект"));
    }
    @Test
    public void epicMatchesQueryInSubtasks() {
        String[] subtasks = { "Помыть посуду", "Помыть машину", "Помыть пол" };
        Epic task = new Epic(1, subtasks);
        Assertions.assertTrue(task.matches("посуду"));
        Assertions.assertTrue(task.matches("машину"));
        Assertions.assertFalse(task.matches("задание"));
    }
    @Test
    public void meetingMatchesQueryInTopicOrProject() {
        Meeting task = new Meeting(1, "Создать проект", "Приложение заданий", "19.03.2023");
        Assertions.assertTrue(task.matches("проект"));
        Assertions.assertTrue(task.matches("Приложение"));
        Assertions.assertFalse(task.matches("домашнее"));
    }
    @Test
    public void simpleTaskNoMatchesQueryInTitle() {
        Task task = new Task(1);

        Assertions.assertFalse(task.matches("Помыть"));
    }
}
