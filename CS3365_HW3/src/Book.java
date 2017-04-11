//Import statement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//Book class 
public class Book {
//Declare variable 
 private static String username = "root";
 private static String password = "";
 private static String connectionString = "jdbc:mysql://localhost:3306/CS3365";
 private static Connection connection;
 private static Statement command;
 private static ResultSet data1;
 @SuppressWarnings("unused")
 private static int datainsert;
 @SuppressWarnings("unused")
 private static int datainsert2;
 @SuppressWarnings("unused")
 private static int datainsert3;
 @SuppressWarnings("unused")
 private static int datainsert4;
 @SuppressWarnings("unused")
 private static int datainsert5;
 @SuppressWarnings("unused")
 private static int datainsert6;
 @SuppressWarnings("unused")
 private static int datainsert7;
 @SuppressWarnings("unused")
 private static int datainsert8;
 @SuppressWarnings("unused")
 private static int datainsert9;
 @SuppressWarnings("unused")
 private static int datainsert10;
 @SuppressWarnings("unused")
 private static int datainsert11;
 @SuppressWarnings("unused")
 private static int data;
//Main method
 public static void main(String[] args) throws SQLException {
   //Establish the database connection
   try {
   connection = DriverManager.getConnection(connectionString, username, password);
   command = connection.createStatement();
   //Count how many books are currently stored in the database 
   data1 = command.executeQuery("select count(*)as TotalData from (select * from Book) as Book;");
   //if there is no books in the databoase, go ahead and insert the required books into database
   if (data1.first() && data1.getInt("TotalData") == 0) {
    datainsert = command.executeUpdate("Insert into Book value  (1,'The Three Musketeers', 'Dorrance Publishing Co. Inc');");
    datainsert2 = command.executeUpdate("Insert into Book value (2, 'Identity and Violence:The illusion of Destiny', 'Hachette Book Group');");
    datainsert3 = command.executeUpdate("Insert into Book value (3, 'The Argumentative Indian', 'HarperCollins Publishers');");
    datainsert4 = command.executeUpdate("Insert into Book value (4, 'Development as Freedom', 'Macmillan Publishers');");
    datainsert5 = command.executeUpdate("Insert into Book value (5, 'River of Smoke','Hachette Book Group');");
    datainsert6 = command.executeUpdate("Insert into Book_Authors value (1, 1, 'Alexander Dumas');");
    datainsert7 = command.executeUpdate("Insert into Book_Authors value (2, 2, 'Amartya Sen');");
    datainsert8 = command.executeUpdate("Insert into Book_Authors value (3, 3, 'Amartya Sen');");
    datainsert9 = command.executeUpdate("Insert into Book_Authors value (4, 4, 'Amartya Sen');");
    datainsert10 = command.executeUpdate("Insert into Book_Authors value (5, 5, 'Amitav Ghose');");
    datainsert11 = command.executeUpdate("Insert into Book_Authors value (6, 2, 'Alexander Dumas');");
   }
   //Change book title to "The Lost Tribe"
   data = command.executeUpdate("Update Book set title = 'The Lost Tribe' where bookid = 4");
  //perform SQL query and all required joins to retrieve out desired data
   data1 = command.executeQuery("select count(title),title,authorname, publishername from book_authors inner join book on book.bookid = book_authors.bookid group by title having count(title) <= 2;");
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
   //Print all books out (1 author)
	try {
    System.out.println("This is Wei-Ping Lee's MySQL Homework for CS3365!");
    System.out.println(" ");
    System.out.println("All Book: ");
    System.out.println("[Title" + " | " + "Author" + " | " + "Publisher" + " (Showing only 1 author)]");
    System.out.println("");
    if (data1.first()) {
     System.out.println(data1.getString("Title") + " | " + data1.getString("Authorname") + " | " + data1.getString("publishername"));
     while (data1.next())
      System.out.println(data1.getString("Title") + " | " + data1.getString("Authorname") + " | " + data1.getString("publishername"));
    }
   } catch (SQLException e) {
    e.printStackTrace();
   }
   //Print all Co-Authored Books
   System.out.println("");
   System.out.println("Co-Authored Book: ");
   data1 = command.executeQuery("select count(title) as 'NumberOfAuthor',title,authorname, publishername from book_authors inner join book on book.bookid = book_authors.bookid group by title having count(title) >= 2;");
   if (data1.first()) {
    System.out.println(data1.getString("Title"));
   }
  }
 }
}