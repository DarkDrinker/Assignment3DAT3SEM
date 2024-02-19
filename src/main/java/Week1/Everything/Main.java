package Week1.Everything;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final CSVReader CSVREADER = null;

    public static void main(String[] args) {
                //lambda excercises
        System.out.println();
        System.out.println("Lambda exercises");
        int resultAddition = operate(5, 3, Integer::sum);
        System.out.println("Addidtion:" + resultAddition);

        int resultSubtraction = operate(5, 3, (a, b) -> a - b);
        System.out.println("Subtraction: " + resultSubtraction);

        int resultMultiplication = operate(5, 3, (a, b) -> a * b);
        System.out.println("Multiplication: " + resultMultiplication);

        int resultDivision = operate(6, 2, (a, b) -> a / b);
        System.out.println("Division: " + resultDivision);

        int resultModulus = operate(5, 3, (a, b) -> a % b);
        System.out.println("Modulus: " + resultModulus);

        int resultPower = operate(2, 3, (a, b) -> (int) Math.pow(a, b));
        System.out.println("Power: " + resultPower);

        // Example with arrays
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};

        int[] resultArray = operate(array1, array2, (a, b) -> a + b);
        System.out.println("Array Addition: " + Arrays.toString(resultArray));

                //Functional programming
        System.out.println();
        System.out.println("Functional programming exercises");

        int[] exmparray = {2,5,6,8};

        int[] Multiplicativearray = map(exmparray, x -> x * 2);
        System.out.println("Doubled array: " + Arrays.toString(Multiplicativearray));

        int[] evenArray = filter(exmparray, x -> x % 2 == 0);
        System.out.println("printing the even values:" + Arrays.toString(evenArray));

            // Functional Interfaces
        System.out.println();
        System.out.println("Functional interfaces exercises");

        //Predicate
        List<Integer> numbers = Arrays.asList(14, 21, 30, 35, 42, 49, 50);
        Predicate<Integer> divisibleBy7 = num -> num % 7 == 0;
        List<Integer> filteredNumbers = numbers.stream()
                .filter(divisibleBy7)
                .collect(Collectors.toList());
        System.out.println("Numbers divisible by 7: " + filteredNumbers);

        //Supplier
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<List<Employee>> employeeSupplier = () ->
                names.stream()
                        .map(name -> new Employee(name, 25)) // Assuming a default age of 25 for simplicity
                        .collect(Collectors.toList());
        List<Employee> employees = employeeSupplier.get();

        //Consumer
        Consumer<List<Employee>> employeePrinter = employeeList ->
                employeeList.forEach(System.out::println);
        employeePrinter.accept(employees);

        //Function
        Function<List<Employee>, List<String>> employeeToNameMapper = employeeList ->
                employeeList.stream()
                        .map(Employee::getName)
                        .collect(Collectors.toList());
        List<String> employeeNames = employeeToNameMapper.apply(employees);
        System.out.println("Employee names: " + employeeNames);

        //Predicate check if employee is older than 20
        Predicate<Employee> isOlderThan18 = employee -> employee.getAge1() > 20;
        Employee sampleEmployee = new Employee("Sample", 25);
        System.out.println("Is sample employee older than 18? " + isOlderThan18.test(sampleEmployee));

                //Time API
        System.out.println();
        System.out.println("Time API excersise");






        List<String> names2 = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        List<LocalDate> birthDates = Arrays.asList(
                LocalDate.of(1990, 5, 15),
                LocalDate.of(1988, 8, 21),
                LocalDate.of(1995, 2, 10),
                LocalDate.of(1992, 11, 30),
                LocalDate.of(1985, 7, 5)
        );
        Supplier<List<Employee>> employeeSupplier1 = () ->
                IntStream.range(0, names.size())
                        .mapToObj(i -> new Employee(names.get(i), birthDates.get(i)))
                        .collect(Collectors.toList());
        List<Employee> employees1 = employeeSupplier1.get();

        // Calculate the age of each employee based on their birthdate
        System.out.println("Age of each employee:");
        employees1.forEach(employee ->
                System.out.println(employee.getName() + ": " + employee.getAge()));

        // Calculate the average age of all employees
        double averageAge = employees1.stream()
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage age of all employees: " + averageAge);

        // Filter and display employees who have birthdays in a specific month (example here: May)
        Month specificMonth = Month.MAY;
        List<Employee> employeesInSpecificMonth = employees1.stream()
                .filter(employee -> employee.getBirthMonth() == specificMonth)
                .toList();
        System.out.println("\nEmployees with birthdays in " + specificMonth + ":");
        employeesInSpecificMonth.forEach(System.out::println);

        // Group employees by birth month and display the count of employees in each group
        Map<Month, Long> employeesByBirthMonthCount = employees1.stream()
                .collect(Collectors.groupingBy(
                        Employee::getBirthMonth,
                        Collectors.counting()
                ));
        System.out.println("\nCount of employees by birth month:");
        employeesByBirthMonthCount.forEach((month, count) ->
                System.out.println(month + ": " + count));

        // List all employees who have a birthday in the current month
        Month currentMonth = LocalDate.now().getMonth();
        List<Employee> employeesInCurrentMonth = employees1.stream()
                .filter(employee -> employee.getBirthMonth() == currentMonth)
                .toList();
        System.out.println("\nEmployees with birthdays in the current month:");
        employeesInCurrentMonth.forEach(System.out::println);

                //Method references
        System.out.println();
        System.out.println("Method references excersises");

        System.out.println("Er det ikke det jeg gør i forvejen? Static Method Reference");

                //Streams API
        System.out.println();
        System.out.println("Streams API excersises");

        List<Book> books = CSVREADER.readBooksFromCSV("src/main/java/Bookslist.csv");

        // Find the average rating of all books.
        double averageRating = books.stream().mapToDouble(Book::getRating).average().orElse(0.0);
        System.out.println("Average rating of all books: " + averageRating);

        // Filter and display books published above a specific height.
        int specificYear = 2000;
        List<Book> booksPublishedAfterYear = books.stream()
                .filter(book -> book.getYearPublished() > specificYear)
                .collect(Collectors.toList());
        System.out.println("\nBooks published after " + specificYear + ":");
        for (Book book: booksPublishedAfterYear) {
            System.out.println(book.getTitle());
        }

        // Sort books by rating in descending order.
        List<Book> sortedBooksByRating = books.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());
        System.out.println("\nBooks sorted by rating in descending order:");
        for (Book book: sortedBooksByRating) {
            System.out.println(book.getRating() +", "+book.getTitle() +", "+ book.getAuthor() +", "+  book.getYearPublished());
        }

        // Find and display the title of the book with the highest rating.
        Book highestRatedBook = books.stream()
                .max(Comparator.comparingDouble(Book::getRating))
                .orElse(null);
        System.out.println("\nTitle of the highest-rated book: " + (highestRatedBook != null ? highestRatedBook.getTitle() : "N/A"));

        // Group books by author and calculate the average rating for each author's books.
        Map<String, Double> averageRatingByAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.averagingDouble(Book::getRating)));
        System.out.println("\nAverage rating by author:");
        averageRatingByAuthor.forEach((author, avgRating) ->
                System.out.println(author + ": " + avgRating));

        // Calculate the total number of pages for all books (assuming each book has a fixed number of pages).
        int totalPages = books.stream()
                .mapToInt(book ->  200)
                .sum();
        System.out.println("\nTotal number of pages for all books: " + totalPages);
    }

    public static int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }

    public static int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array length mist be the same");
        }

        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i], b[i]);
        }

        return result;
    }

    public static int[] map(int[] a, MyTransformingType op) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.transform(a[i]);
        }
        return result;
    }

    // Filter method to keep elements in the array based on a validation condition
    public static int[] filter(int[] a, MyValidatingType op) {
        return Arrays.stream(a)
                .filter(op::validate)
                .toArray();
    }


}
