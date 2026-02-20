import java.io.*;
import java.net.*;
import java.sql.*;

public class NewsServer {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/newsdb",
                "root",
                "akarsh11");   

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started at http://localhost:8080");

        while (true) {
            Socket socket = serverSocket.accept();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");

            StringBuilder response = new StringBuilder();
            response.append("HTTP/1.1 200 OK\r\n");
            response.append("Content-Type: application/json\r\n\r\n");
            response.append("[");

            boolean first = true;
            while (rs.next()) {
                if (!first) response.append(",");
                response.append("{");
                response.append("\"id\":").append(rs.getInt("id")).append(",");
                response.append("\"title\":\"").append(rs.getString("title")).append("\",");
                response.append("\"likes\":").append(rs.getInt("likes")).append(",");
                response.append("\"shares\":").append(rs.getInt("shares")).append(",");
                response.append("\"comments\":").append(rs.getInt("comments"));
                response.append("}");
                first = false;
            }
            response.append("]");

            OutputStream out = socket.getOutputStream();
            out.write(response.toString().getBytes());
            out.flush();
            socket.close();
        }
    }
}