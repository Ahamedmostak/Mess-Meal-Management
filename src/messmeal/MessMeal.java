package messmeal;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



 
public class MessMeal {

 public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scnnr = new Scanner(System.in);
        int choice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MessMeal?", "root", "")) 
            {
           
               do {
                    System.out.println("Mess-Meal Management");
                    System.out.println("");
                    System.out.println("Enter a Number from 1 To 5 for this Databse");
                    System.out.println("____________________________________________");
                    System.out.println("Enter '1' For Adding new Entry");
                    System.out.println("");
                    System.out.println("Enter '2' For Show The Data");
                    System.out.println("");
                    System.out.println("Enter '3' For Updating Entries");
                    System.out.println("");
                    System.out.println("Enter '4' For Delete Any  Entry");
                    System.out.println("");
                    System.out.println("Enter '5' To Exit.");
                    System.out.println("");
                    System.out.print("Enter a Number To Forward: ");
                    choice = scnnr.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                            System.out.println("Enter Your Id : ");
                            int Id = scnnr.nextInt();
                            System.out.println("Enter Your Name : ");
                            String Name = scnnr.next();
                            System.out.println("Enter Meal_Input Number : ");
                            int Meal_Input = scnnr.nextInt();
                            System.out.println("Enter Payment_Status : ");
                            String Payment_Status = scnnr.next();
                            String sql = "insert into info values (?,?,?,?);";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Id);
                            stmt.setString(2, Name);
                            stmt.setInt(3, Meal_Input);
                            stmt.setString(4,Payment_Status);
                            stmt.execute();
                            System.out.println("New Input Added Successfully!");
                            System.out.println("");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 2:
                            try {
                            System.out.println("Enter Your Id to see the Data : ");
                            Scanner input = new Scanner(System.in);
                            int Id = input.nextInt();
                            String sql = "select * from info where Id=?;";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Id);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {

                                System.out.println("Id No = " + rs.getInt(1));
                                System.out.println("Name is = " + rs.getString(2));
                                System.out.println("Meal Input is = " + rs.getInt(3));
                                System.out.println("Payment Status = " + rs.getString(4));
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3:
                            try {
                            System.out.println("Enter Your Name : ");
                            String Name = scnnr.next();
                            System.out.println("Enter New Meal Input : ");
                            int Meal_Input = scnnr.nextInt();
                            String sql = "update info set Meal_Input= ? where Name = ?";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setInt(1, Meal_Input);
                            statement.setString(2,Name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Data Updated Successfully!");
                            } else {
                                System.out.println("There is no Record Founded in This Database That You Entered");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                        case 4:
                            try {
                            System.out.println("Enter Name for Delete Data : ");
                            String Name = scnnr.next();
                            String sql = "delete from info where Name = ?;";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, Name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Data Deleted Successfully!");
                            } else {
                                System.out.println("There is no Record Founded in This Database That You Entered");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 5:
                            System.out.println("");
                            break;
                        default:
                            System.out.println("Please Enter '1-5' Option!");
                            break;
                    }

                } while (choice != 5);
                System.out.println("Thank You, Stay Home!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MessMeal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

