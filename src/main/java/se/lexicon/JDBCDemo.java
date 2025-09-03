package se.lexicon;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JDBCDemo {

    private static String URL = "jdbc:mysql://localhost:3306/g56_student_db";
    private static String USER = "root";
    private static String PASSWORD = "12345PipMurre";

    public static void main(String[] args) {

        ex2();

    }

    private static void ex1() {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();


        ){


            boolean isValid = connection.isValid(0);
            System.out.println("isValid = " + isValid);

            System.out.println("✅ Connection was made");

            String query = "SELECT id, name, class_group, create_date FROM student";

            ResultSet resultSet = statement.executeQuery(query);

            /*
                1️⃣ executeQuery(String sql) - Used for SELECT statements that return a ResultSet.
                ✅ Use Case: Fetching records from the database.

                2️⃣ executeUpdate(String sql) - Used for INSERT, UPDATE, DELETE statements.
                ✅ Use Case: Modifying data (returns number of affected rows).

                3️⃣ execute(String sql) - Can be used for both SELECT and non-SELECT queries.
                ✅ Use Case: Running stored procedures or unknown query types.

                4️⃣ executeBatch() - Executes multiple queries in one batch for performance optimization.
                ✅ Use Case: Bulk inserting multiple rows efficiently.
             */

            System.out.println("Student result:");

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String classGroup = resultSet.getString("class_group");
                LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();

                String formatedDate = createDate.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));

                // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
                // Format the date using DateTimeFormatter with a custom pattern.
                // "EEEE" - Full name of the day (e.g., "Monday")
                // "dd"   - Two-digit day of the month (e.g., "07", "12")
                // "MMMM" - Full name of the month (e.g., "March")
                // "yyyy" - Four-digit year (e.g., "2024")

                System.out.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup+ "| Created At: " + formatedDate);
            }

        }catch (SQLException e) {
            System.err.println("❌ Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ex2() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

             Statement statement = connection.createStatement();

             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, class_group, create_date FROM student WHERE class_group LIKE ?");


        ) {

            System.out.println("✅ Connection was made");
            String classGroupParm = "G1";


            preparedStatement.setString(1, classGroupParm);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {


                System.out.println("Student result:");

                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String classGroup = resultSet.getString("class_group");
                    LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();


                    System.out.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup + "| Created At: " + createDate);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error connecting to the database: " + e.getMessage());
//            e.printStackTrace();
        }

    }
}
