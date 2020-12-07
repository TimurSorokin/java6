import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DataBase {
    public DataBase() {
        System.out.println("Data base started ");
    }

    private Connection connect() {

        //SQLITE
        //String url = "jdbc:sqlite:src/DataBase.db";

        //MS ACCES DATABASES
        //String url = "jdbc:ucanaccess://src//DataBaseAccess.accdb";

        //MYSQL SERVER
        String url = "jdbc:mysql://localhost:3306/practicajdbc";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "qwerty");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //Add New Book to DataBase
    public void insertBook(int isbn, String title, String author, int numofcopies, int yearpub, String publisher, int numpages) {
        String sql = "INSERT INTO libro (ISBN,titulo,autor,númejemplares,anyopublicacion,editorial,numpag) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setInt(4, numofcopies);
            pstmt.setInt(5, yearpub);
            pstmt.setString(6, publisher);
            pstmt.setInt(7, numpages);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Add New User to DataBase
    public void insertUser(String firstname, String lastname, int phonenum, int age, java.sql.Date date) {
        String sql = "INSERT INTO socio (nombre,apellidos,telefono,edad,fecha) VALUES (?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setInt(3, phonenum);
            pstmt.setInt(4, age);
            pstmt.setDate(5, date);
            pstmt.executeUpdate();

            /*ResultSet tableKeys = pstmt.getGeneratedKeys();
            tableKeys.next();*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Add New User to DataBase
    public void insertBorrowing(int BookIsbn, int UserNumsocio, java.sql.Date DateBorrowing, java.sql.Date DateReturn) {
        String sql = "INSERT INTO prestamo (libro,socio,fprestamo,fdevolucion) VALUES (?,?,?,?)";
        String defaultdate = "0001-01-01";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, BookIsbn);
            pstmt.setInt(2, UserNumsocio);
            pstmt.setDate(3, DateBorrowing);
            if (DateReturn.compareTo(java.sql.Date.valueOf(defaultdate)) == 0) {
                pstmt.setNull(4, Types.DATE);
                pstmt.executeUpdate();
            } else {
                pstmt.setDate(4, DateReturn);
                pstmt.executeUpdate();
            }
            /*
            ResultSet tableKeys = pstmt.getGeneratedKeys();
            tableKeys.next();
             */
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete Book
    public void deleteBook(int isbn) {
        String sql = "DELETE FROM libro WHERE ISBN=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //DELETE USER
    public void deleteUser(int numsocio) {
        String sql = "DELETE FROM socio WHERE numsocio=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, numsocio);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public JTable GetTable(int option) {
        String input;
        String sql;
        ResultSet rs;
        JTable table = new JTable();
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] data;

        switch (option) {
            //Books
            case 1:
                 sql = "SELECT * FROM libro";
                dtm.setRowCount(0);
                dtm.addColumn("ISBN");
                dtm.addColumn("TITULO");
                dtm.addColumn("AUTOR");
                dtm.addColumn("NUM. EJEMPLARES");
                dtm.addColumn("YEAR PUBLICACION");
                dtm.addColumn("EDITORIAL");
                dtm.addColumn("NUM. PAGINAS");
                table.setModel(dtm);
                data = new Object[7];
                try {
                    Connection conn = this.connect();
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt(1);
                        data[1] = rs.getString(2);
                        data[2] = rs.getString(3);
                        data[3] = rs.getInt(4);
                        data[4] = rs.getInt(5);
                        data[5] = rs.getString(6);
                        data[6] = rs.getInt(7);
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

                //Users
            case 2:
                sql = "SELECT * FROM socio";
                dtm.setRowCount(0);
                dtm.addColumn("Numsocio");
                dtm.addColumn("Nombre");
                dtm.addColumn("Apellidos");
                dtm.addColumn("Telefono");
                dtm.addColumn("Edad");
                dtm.addColumn("Fecha");

                table.setModel(dtm);
                data = new Object[6];
                try {
                    Connection conn = this.connect();
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt(1);
                        data[1] = rs.getString(2);
                        data[2] = rs.getString(3);
                        data[3] = rs.getInt(4);
                        data[4] = rs.getInt(5);
                        data[5] = rs.getDate(6);
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                System.out.println("Finished");
                return table;

                //Borrowings
            case 3:

                 sql = "SELECT * FROM prestamo";
                dtm.setRowCount(0);
                dtm.addColumn("Libro ISBN: ");
                dtm.addColumn("Socio numsocio: ");
                dtm.addColumn("Fecha prestamo");
                dtm.addColumn("Fecha devolcion");
                table.setModel(dtm);
                data = new Object[4];
                try {
                    Connection conn = this.connect();
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt(1);
                        data[1] = rs.getInt(2);
                        data[2] = rs.getDate(3);
                        data[3] = rs.getDate(4);
                        dtm.addRow(data);

                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            //LISTADO LIBROS QUE HA SACADO UN SOCIO
            case 4:
                input=JOptionPane.showInputDialog("Numero de Socio: ");
                while (!IsNumber(input))
                {
                    input=JOptionPane.showInputDialog("Numero de Socio: ");
                }
               sql = ("SELECT libro FROM prestamo WHERE socio ="+Integer.parseInt(input));
                dtm.setRowCount(0);
                dtm.addColumn("Libro ISBN: ");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("libro");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;



            //LISTADO DE SOCIOS QUE HA COGIDO UN DETERMINADO LIBRO
            case 5:
                input=JOptionPane.showInputDialog("ISBN: ");
                while (!IsNumber(input))
                {
                    input=JOptionPane.showInputDialog("ISBN: ");
                }
                sql = ("SELECT socio FROM prestamo WHERE libro ="+Integer.parseInt(input));
                dtm.setRowCount(0);
                dtm.addColumn("Numero de Socio: ");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("socio");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 6:
                //NÚMERO DE PRÉSTAMOS DE UN LIBRO
                input=JOptionPane.showInputDialog("ISBN: ");
                while (!IsNumber(input))
                {
                    input=JOptionPane.showInputDialog("ISBN: ");
                }
                sql = ("SELECT libro, COUNT(*) FROM  prestamo WHERE  libro = "+Integer.parseInt(input));
                dtm.setRowCount(0);
                dtm.addColumn("libro: ");
                dtm.addColumn("Numero de prestamos");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("libro");
                        data[1] = rs.getInt("Count(*)");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 7:
                //NÚMERO DE LIBROS PRESTADOS A UN SOCIO DETERMINADO
                input=JOptionPane.showInputDialog("Numero de socio: ");
                while (!IsNumber(input))
                {
                    input=JOptionPane.showInputDialog("Numero de Socio: ");
                }
                sql = ("SELECT socio, COUNT(*) FROM  prestamo WHERE  socio = "+Integer.parseInt(input));
                dtm.setRowCount(0);
                dtm.addColumn("socio: ");
                dtm.addColumn("Numero de (libros) prestamos: ");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("socio");
                        data[1] = rs.getInt("Count(*)");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;
            case 8:
               // LISTADO SOCIOS QUE NO HAN SACADO NINGÚN LIBRO
                sql = "select socio.numsocio from socio where socio.numsocio NOT IN (select prestamo.socio from prestamo)";
                dtm.setRowCount(0);
                dtm.addColumn("numero de socio: ");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("numsocio");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 9:
                //LISTADO DE SOCIOS DADOS DE ALTA EN UN AÑO INTRODUCIDO POR PANTALLA
                input=JOptionPane.showInputDialog("YEAR ");
                while (!IsNumber(input))
                {
                    input=JOptionPane.showInputDialog("YEAR ");
                }
                sql = ("select numsocio,fecha from socio where year(fecha) ="+Integer.parseInt(input));
                dtm.setRowCount(0);
                dtm.addColumn("Socio: ");
                dtm.addColumn("Year: ");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("numsocio");
                        data[1] = rs.getDate("fecha");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 10:
                //PRÉSTAMOS QUE HAN TENIDO DURACIÓN SUPERIOR A 10 DIAS
                sql = ("Select libro,socio from prestamo where datediff(fdevolucion, fprestamo)>10;");
                dtm.setRowCount(0);
                dtm.addColumn("ISBN: ");
                dtm.addColumn("num socio: ");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("libro");
                        data[1] = rs.getInt("socio");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 11:
                //NÚMERO DE LIBROS PRESTADOS ACTUALMENTE
                sql = ("Select count(*) from prestamo where fdevolucion Is NUll;");
                dtm.setRowCount(0);
                dtm.addColumn("numero de libros");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0]=rs.getInt("count(*)");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;
            case 12:
                //LISTADO DE LIBROS PRESTADOS ACTUALMENTE
                sql = ("Select libro from prestamo where fdevolucion Is NUll;");
                dtm.setRowCount(0);
                dtm.addColumn("ISBN");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0]=rs.getInt("libro");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 13:
                //SOCIOS QUE HAN SACADO MAS DE UNA VEZEL MISMO LIBRO
                sql = ("SELECT socio, COUNT(libro)FROM prestamo GROUP BY socio HAVING COUNT(libro) > 1;");
                dtm.setRowCount(0);
                dtm.addColumn("socio");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0]=rs.getInt("socio");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;
            case 14:
                //LIBROS QUE HAN SUPERADO LA FECHA FIN DE PRÉSTAMO (FPRESTAMO+1 SEMANA)
                sql = ("SELECT libro from prestamo where DATEDIFF(CURDATE(),fprestamo)>7;");
                dtm.setRowCount(0);
                dtm.addColumn("ISBN: ");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("libro");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 15:
                //SOCIOS QUE TIENEN LIBROS ACTUALMENTE
                sql = ("select socio from prestamo where fdevolucion IS NULL GROUP BY socio;");
                dtm.setRowCount(0);
                dtm.addColumn("num socio");
                table.setModel(dtm);
                data = new Object[1];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("socio");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 16:
                //LIBRO MÁS PRESTADO DE LA BIBLIOTECA
                sql = ("select libro,count(*) as prestamos from prestamo group by libro order by prestamos DESC limit 1;");
                dtm.setRowCount(0);
                dtm.addColumn("libro");
                dtm.addColumn("prestamos");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("libro");
                        data[1] = rs.getInt("prestamos");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;

            case 17:
                //SOCIO QUE MÁS PRÉSTAMOS TIENE EN LA BIBLIOTEC
                sql = ("select socio,count(*) as prestamos from prestamo group by socio order by prestamos DESC limit 1;");
                dtm.setRowCount(0);
                dtm.addColumn("socio");
                dtm.addColumn("prestamos");
                table.setModel(dtm);
                data = new Object[2];
                try {
                    Connection conn = this.connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        data[0] = rs.getInt("socio");
                        data[1] = rs.getInt("prestamos");
                        dtm.addRow(data);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "An error occured" + "\n" + e.getMessage());
                }
                return table;
        }
        return null;
    }


    private Boolean IsNumber(String input)
    {
        String regex = "\\d+";
        if(!input.matches(regex))
        {
            JOptionPane.showMessageDialog(null, input+" is not a valid input!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }

}






