package se.lexicon.attendance_app.dao;

import se.lexicon.attendance_app.db.MySQLDatabaseConnection;
import se.lexicon.attendance_app.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public Student save(Student student) {

        String sql = "INSERT INTO student (name, class_group) VALUES (?, ?)";

        try(Connection connection = MySQLDatabaseConnection.getConnection();

            PreparedStatement preparedStatement =  connection.prepareStatement(sql);

        ){


        } catch (SQLException e) {
            System.err.println("❌ Error saving student: " + e.getMessage());

        }


        return null;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }
}
