package teamproject;

public class CustomListTest {

    public static void main(String[] args) {

        testAddAndGet();
        testSizeAndIsEmpty();
        testIterator();
        testOutOfBounds();

        System.out.println("CustomListTest FINISHED");
    }

    private static void testAddAndGet() {
        CustomList<String> list = new CustomList<>();
        list.add("A");
        list.add("B");

        boolean passed =
                "A".equals(list.get(0)) &&
                        "B".equals(list.get(1));

        printResult("testAddAndGet", passed);
    }

    private static void testSizeAndIsEmpty() {
        CustomList<Integer> list = new CustomList<>();

        boolean initial =
                list.size() == 0 &&
                        list.isEmpty();

        list.add(10);
        list.add(20);

        boolean afterAdd =
                list.size() == 2 &&
                        !list.isEmpty();

        printResult("testSizeAndIsEmpty", initial && afterAdd);
    }

    private static void testIterator() {
        CustomList<String> list = new CustomList<>();
        list.add("X");
        list.add("Y");
        list.add("Z");

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }

        printResult("testIterator", "XYZ".equals(sb.toString()));
    }

    private static void testOutOfBounds() {
        CustomList<String> list = new CustomList<>();
        boolean exceptionThrown = false;

        try {
            list.get(0);
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }

        printResult("testOutOfBounds", exceptionThrown);
    }

    private static void printResult(String testName, boolean passed) {
        if (passed) {
            System.out.println(testName + " PASSED");
        } else {
            System.out.println(testName + " FAILED");
        }
    }
}
