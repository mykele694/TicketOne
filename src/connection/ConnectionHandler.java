package connection;

import java.sql.*;
import java.util.Properties;

public class ConnectionHandler implements AutoCloseable {

    public static final String DB_URL_PREFIX = "jdbc:postgresql://";
    public static final String DB_URL_HOST = "localhost";
    public static final String DB_PORT = "5432";
    public static final String DB_NAME = "teatro";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "admin";
    private final Properties dataprops= new Properties();
    private static ConnectionHandler instance;
    private Connection connection;


    private ConnectionHandler(){
        this.dataprops.setProperty("user", DB_USER);
        this.dataprops.setProperty("password", DB_PASSWORD);
    }
    public static ConnectionHandler getInstance(){
        if (instance==null)
            instance=new ConnectionHandler();
        return instance;
    }
    public Connection getConnection() throws SQLException{
        if(connection ==null ||connection.isClosed())
            this.connection =DriverManager.getConnection(getDatabaseurl(),dataprops);
        return this.connection;
    }
    public static String getDatabaseurl(){return DB_URL_PREFIX+DB_URL_HOST+":"+ DB_PORT +"/"+ DB_NAME;}
    public void closeConnection() throws SQLException{
        if(this.connection!=null && !this.connection.isClosed()){
            this.connection.close();
            this.connection=null;
        }
    }
    public void close() throws SQLException{
        this.closeConnection();
    }
    public PreparedStatement getPreparedStatement(String query) throws SQLException{
        Connection conn= getConnection();
        return conn.prepareStatement(query);
    }

   /* public static void main(String[] args) throws SQLException {
        String user="postgres";
        String password="admin";

        Connection conn;
        //1 metodo
        conn= DriverManager.getConnection(getDatabaseurl(),user,password);

        //2 metodo
        conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/teatro?user="+user+"&password="+password);

        //3 metodo
        Properties pros= new Properties();
        pros.setProperty("user",user);
        pros.setProperty("password",password);
        pros.setProperty("currentSchema","public");
        conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/teatro",pros);

        PreparedStatement ps= conn.prepareStatement("SELECT * FROM utente;");
        ResultSet rs=ps.executeQuery();
        while(rs.next()) System.out.println(rs.getString("nome"));

        conn.close();
        ps.close();


        // <-------------------------------------------- connection.ConnectionHandler -------------------------------------------->

        // gestiamo in maniera strutturata apertura/chiusura della connessione con il DB, garantendone l'univocità
        ConnectionHandler ch = ConnectionHandler.getInstance();
        ps = ch.getPreparedStatement("SELECT * FROM utente;");
        rs = ps.executeQuery();
        while (rs.next()) System.out.println(rs.getString("nome"));

        ch.closeConnection();
        ps.close();
        rs.close();

        // implementando l'interfaccia AutoClosable,
        // possiamo chiudere il tutto (PreparedStatement, ResultSet e Connection) anche tramite try-with-resources
        try (ConnectionHandler ch2 = ConnectionHandler.getInstance();
             PreparedStatement ps2 = ch2.getPreparedStatement("SELECT * FROM utente;");
             ResultSet rs2 = ps2.executeQuery())
        {
            while (rs2.next()) System.out.println(rs2.getString("nome"));
        }

        // la chiusura manuale di ogni risorsa aperta non è più necessaria (che andrebbe fatta in un finally)
        finally {
            ch2.closeConnection();
            ps2.close();
            rs2.close();
        }

    }
    */
}
