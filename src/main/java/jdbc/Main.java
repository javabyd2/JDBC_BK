package jdbc;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;

        try{
           connection = DBConnector.getConnection();

           statement = connection.createStatement();

//           String sql1 = "select * from customer";
//           String sql2 = "select mark from car";
//           String sql3 = "insert into car (reg_number,mark,model,rate) values ('CT 12315','Opel','Corsa',3)";
//           String sql4 = "insert into car (reg_number,mark,model,rate) values (?,?,?,?)";
//           String sql5 = "update car set model = ? where model = ?";
           String sql6 = "select count(*) from ?";
           String sql7 = "show tables";
           String getQ = "select * from customer";
           String addQ = "insert into customer (first_name,last_name,address,postal_code,email) values (?,?,?,?,?)";
           String editQ = "update customer set first_name = ?, last_name = ? where first_name = ? and last_name = ?";

//           //Pierwszy execute
//           ResultSet resultSet =  statement.executeQuery(sql1);
//           while(resultSet.next()){
//               System.out.println(resultSet.getRow() + " - imie: " + resultSet.getString("first_name"));
//           }
//
//           //Drugi execute
//           ResultSet resultSet2 = statement.executeQuery(sql2);
//           while(resultSet2.next()){
//               System.out.println("mark: " + resultSet2.getString("mark"));
//           }

           //Trzeci execute
           //statement.executeUpdate(sql3);

//            PreparedStatement preparedStatement = connection.prepareStatement(sql4);
//
//            System.out.println("Podaj num rej:");
//            preparedStatement.setString(1, scanner.nextLine());
//            System.out.println("Podaj marke:");
//            preparedStatement.setString(2, scanner.nextLine());
//            System.out.println("Podaj model:");
//            preparedStatement.setString(3, scanner.nextLine());
//            System.out.println("Podaj rating:");
//            preparedStatement.setInt(4, scanner.nextInt());
//            preparedStatement.executeUpdate();
//            System.out.println("Dodano.");

//            PreparedStatement ps = connection.prepareStatement(sql5);
//            System.out.println("Jaki model zamieniamy?");
//            ps.setString(2, scanner.nextLine());
//            System.out.println("Na jaki model?");
//            ps.setString(1, scanner.nextLine());
//            ps.executeUpdate();
//            System.out.println("Zmieniono");

//            ResultSet tables = statement.executeQuery(sql7);
//            System.out.println("Ktora tabele podliczyc?");
//            ResultSetMetaData rsmd = tables.getMetaData();
//            while(tables.next()){
//               System.out.println(tables.getString("Tables_in_rental_db"));
//            }
//
//            PreparedStatement ps = connection.prepareStatement(sql6);
//            ps.setString(1, scanner.next());
//
//            //System.out.println();
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            System.out.println(rs.getInt(1));



            int n = 1;

            while(n!=0){
                System.out.println("1. Dodaj klienta");
                System.out.println("2. Wyswietl klientow");
                System.out.println("3. Edytuj klienta");
                System.out.println("0. Wyjdz");
                n=scanner.nextInt();
                switch(n){
                    case 1:{
                        PreparedStatement statement1 = connection.prepareStatement(addQ);
                        System.out.println("Podaj imie");
                        statement1.setString(1, scanner.next());
                        System.out.println("Podaj nazwisko");
                        statement1.setString(2, scanner.next());
                        System.out.println("Podaj adres");
                        statement1.setString(3, scanner.next());
                        System.out.println("Podaj kod pocztowy");
                        statement1.setString(4, scanner.next());
                        System.out.println("Podaj email");
                        statement1.setString(5, scanner.next());
                        statement1.executeUpdate();
                        System.out.println("Dodano");
                    }
                    break;
                    case 2:{
                        ResultSet resultSet =  statement.executeQuery(getQ);
                        while(resultSet.next()){
                            System.out.println(resultSet.getString("first_name") + "\t| " +
                                    resultSet.getString("last_name") + "\t| " +
                                    resultSet.getString("address") + "\t| " +
                                    resultSet.getString("postal_code"));
                        }
                    }
                    break;
                    case 3:{
                        PreparedStatement statement2 = connection.prepareStatement(editQ);
                        System.out.println("Podaj stare imie");
                        statement2.setString(3, scanner.next());
                        System.out.println("Podaj stare nazwisko");
                        statement2.setString(4, scanner.next());
                        System.out.println("Podaj nowe imie");
                        statement2.setString(1, scanner.next());
                        System.out.println("Podaj nowe nazwisko");
                        statement2.setString(2, scanner.next());
                        statement2.executeUpdate();
                        System.out.println("Zmieniono");
                    }
                    break;
                }
            }

           statement.close();
           //resultSet.close();
           connection.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

}
