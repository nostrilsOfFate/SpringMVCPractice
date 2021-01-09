package practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import practice.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersonDAO {
    //1
  //  private static int PEOPLE_COUNT;

//3
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    2
//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    public static final String USERNAME = "postgres";
//    public static final String PASSWORD = "postgres";
//
//    private static Connection connection;
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }

    public List<Person> index() {
//3
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                people.add(person);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return people;

//4
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id) {
//        1
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

        //2
//        Person person = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//            person = new Person();
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return person;

        //3
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        //1
        // person.setId(++PEOPLE_COUNT);
        //  people.add(person);

//2
//        try {
//            Statement statement = connection.createStatement();
//
//            //Неудобно, можно сделать ошибки и угроза SQL инъекции
//            String SQL =  "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
//
//            statement.executeUpdate(SQL);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

//3
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)",person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatedPerson) {
//1
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());

//2
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("UPDATE Person SET name =?, age =?, email=? WHERE id=?");
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        //3
        jdbcTemplate.update("UPDATE Person SET name =?, age =?, email=? WHERE id=?",updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        //1
        //people.removeIf(p -> p.getId() == id);

        //2
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        //3
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
