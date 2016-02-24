package sample;

/**
 * Created by Dorine on 21/02/2016.
 */
import java.sql.*;
import java.util.Scanner;

public class Subscribe {

    private Connection con;

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void connect() throws Exception {

        //Check connection
        if (con != null) return;

        try {
            //Connection to the database
            Class.forName("com.mysql.jdbc.Driver");

            String connectionURL = "jdbc:mysql://localhost:3306/sharin?autoReconnect=true&useSSL=false";

            con = DriverManager.getConnection(connectionURL, "root", "root");

            Statement myStmt = con.createStatement();

            //Users data
            //NAME
            System.out.println("Nom :");
            Scanner sc1 = new Scanner(System.in);
            //Check if this is an int value
            while(sc1.hasNextInt()){
                //Invalid value
                System.out.println("Votre nom ne doit comporter que des caractères alphabétiques");
                sc1 = new Scanner(System.in);
            }
            String name = sc1.nextLine();
            //FIRSTNAME
            System.out.println("Prénom :");
            Scanner sc2 = new Scanner(System.in);
            //Check if this is an int value
            while(sc2.hasNextInt()){
                //Invalid value
                System.out.println("Votre prénom ne doit comporter que des caractères alphabétiques");
                sc1 = new Scanner(System.in);
            }
            String firstname = sc2.nextLine();
            //MAIL
            System.out.println("Mail:");
            Scanner sc3 = new Scanner(System.in);
            String mail = sc3.nextLine();
            if(!isValidEmailAddress(mail)){
                System.out.println("Votre mail doit être valide");
                sc3 = new Scanner(System.in);
            }
            mail = sc3.nextLine();
            //PASSWORD
            System.out.println("Mot de passe :");
            Scanner sc4 = new Scanner(System.in);
            //Check if this is an int value
            String pwd = sc4.nextLine();


            //Insert query to add new user
            String sql = "INSERT INTO users (name, firstname, email, password) VALUES ('"+name+"', '"+firstname+"', '"+mail+"', '"+pwd+"');";
            myStmt.executeUpdate(sql);


            //Select query on users table
            ResultSet myRs = myStmt.executeQuery("SELECT * from users");

            //Display content of table users
            while(myRs.next()){
                System.out.println(myRs.getString("name") + " , " + myRs.getString("firstname") + " , " + myRs.getString("email")+ " , " + myRs.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            throw new Exception("No database");
        }


    }

    //Close connection
    public void close() {
        if (con != null) {
            try {
                System.out.println("Connexion établie");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
