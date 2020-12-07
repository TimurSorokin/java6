import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class GUI {
    DataBase db = new DataBase();
    JScrollPane DataTable ;
    JFrame frame;

    public GUI()
    {
         frame = new JFrame("Practica JDBC");
        DataTable = GetData(1); //default table (showing books)
        JMenuBar menuBar = new JMenuBar();
        JMenu add = new JMenu("ADD");
        JMenu list = new JMenu("LIST");
        JMenu delete = new JMenu("DELETE");
        JMenu dataConsult = new JMenu("Consult data from data base") ;

        menuBar.add(add);
        menuBar.add(list);
        menuBar.add(delete);
        menuBar.add(dataConsult);
        //add
        JMenuItem addBook, addUser, addBorrowing;
        addBook = new JMenuItem("Add Book");
        addUser = new JMenuItem("Add User");
        addBorrowing = new JMenuItem("Add Borrowing");
        add.add(addBook);
        add.add(addUser);
        add.add(addBorrowing);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();

            }
        });
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        addBorrowing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBorrowing();
            }
        });
        //list
        JMenuItem listBooks, listUsers, listBorrowings;
        listBooks = new JMenuItem("List Books");
        listBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               UpdateFrame(1);
            }
        });
        listUsers = new JMenuItem("List Users");
        listUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(2);
            }
        });
        listBorrowings = new JMenuItem("List Borrowings");
        listBorrowings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(3);
            }
        });
        list.add(listBooks);
        list.add(listUsers);
        list.add(listBorrowings);


        //delete
        JMenuItem deleteBook, deleteUser;
        deleteBook = new JMenuItem("Delete Book");
        deleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        deleteUser = new JMenuItem("Delete User");
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        delete.add(deleteBook);
        delete.add(deleteUser);


        //Data Consult
        JMenuItem list_books_user_borrowed,
                list_users_certain_book,
                list_times_borrowed,
                list_books_certain_user,
                list_users_noborrowed,
                list_users_year,
                list_borrowings_more_10,
                list_number_borrowed_books,
                list_borrowed_books,
                list_users_same_book,
                list_expired_borrowings,
                list_users_with_books,
                list_popular_book,
                list_popular_user;
        list_books_user_borrowed = new JMenuItem("LISTADO LIBROS QUE HA SACADO UN SOCIO");
        list_books_user_borrowed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(4);
            }
        });
        list_users_certain_book = new JMenuItem("LISTADO DE SOCIOS QUE HA COGIDO UN DETERMINADO LIBRO");
        list_users_certain_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(5);
            }
        });

        list_times_borrowed = new JMenuItem("NÚMERO DE PRÉSTAMOS DE UN LIBRO");
        list_times_borrowed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(6);
            }
        });
        list_books_certain_user = new JMenuItem("NÚMERO DE LIBROS PRESTADOS A UN SOCIO DETERMINADO");
        list_books_certain_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(7);
            }
        });
        list_users_noborrowed = new JMenuItem("LISTADO SOCIOS QUE NO HAN SACADO NINGÚN LIBRO");
        list_users_noborrowed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(8);
            }
        });
        list_users_year = new JMenuItem("LISTADO DE SOCIOS DADOS DE ALTA EN UN AÑO INTRODUCIDO POR PANTALLA");
        list_users_year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(9);
            }
        });
        list_borrowings_more_10 = new JMenuItem("PRÉSTAMOS QUE HAN TENIDO DURACIÓN SUPERIOR A 10 DIAS");
        list_borrowings_more_10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(10);
            }
        });
        list_number_borrowed_books = new JMenuItem("NÚMERO DE LIBROS PRESTADOS ACTUALMENTE");
        list_number_borrowed_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(11);
            }
        });
        list_borrowed_books = new JMenuItem("LISTADO DE LIBROS PRESTADOS ACTUALMENTE");
        list_borrowed_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(12);
            }
        });
        list_users_same_book = new JMenuItem("SOCIOS QUE HAN SACADO MAS DE UNA VEZEL MISMO LIBRO");
        list_users_same_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(13);
            }
        });
        list_expired_borrowings = new JMenuItem("LIBROS QUE HAN SUPERADO LA FECHA FIN DE PRÉSTAMO (FPRESTAMO+1 SEMANA)");
        list_expired_borrowings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(14);
            }
        });
        list_users_with_books = new JMenuItem("SOCIOS QUE TIENEN LIBROS ACTUALMENTE");
        list_users_with_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(15);
            }
        });
        list_popular_book = new JMenuItem("LIBRO MÁS PRESTADO DE LA BIBLIOTECA");
        list_popular_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(16);
            }
        });
        list_popular_user = new JMenuItem("SOCIO QUE MÁS PRÉSTAMOS TIENE EN LA BIBLIOTEC");
        list_popular_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(17);
            }
        });
        dataConsult.add(list_books_user_borrowed);
        dataConsult.add(list_users_certain_book);
        dataConsult.add(list_times_borrowed );
        dataConsult.add(list_books_certain_user);
        dataConsult.add(list_users_noborrowed);
        dataConsult.add(list_users_year);
        dataConsult.add(list_borrowings_more_10);
        dataConsult.add(list_number_borrowed_books);
        dataConsult.add(list_borrowed_books);
        dataConsult.add(list_users_same_book);
        dataConsult.add(list_expired_borrowings);
        dataConsult.add(list_users_with_books);
        dataConsult.add(list_popular_book);
        dataConsult.add(list_popular_user);

        listBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(3);
            }
        });
        listUsers = new JMenuItem("List Users");
        listUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(2);
            }
        });
        listBorrowings = new JMenuItem("List Borrowings");
        listBorrowings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFrame(3);
            }
        });
        //frame stuff
        frame.setJMenuBar(menuBar);
        //frame.add(scrollPane);
        frame.add(DataTable);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
//add new book FRAME
    private void addBook()
    {
        JFrame addBook = new JFrame("Add Book");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Add Book");
        JButton close = new JButton("Close");
        //labels
        JLabel isbn = new JLabel("ISBN: ");
        JLabel title = new JLabel("Title: ");
        JLabel author = new JLabel("Author: ");
        JLabel numofcopies = new JLabel("num. of copies");
        JLabel yearpub = new JLabel("Year of publication: ");
        JLabel publisher = new JLabel("Publisher: ");
        JLabel numpages = new JLabel("num. of pages: ");
        //textfields
        JTextField t_isbn = new JTextField();
        JTextField t_title = new JTextField();
        JTextField t_author = new JTextField();
        JTextField t_numofcopies = new JTextField();
        JTextField t_yearpub = new JTextField();
        JTextField t_publisher = new JTextField();
        JTextField t_numpages = new JTextField();
        //adding components
        panel.add(isbn);
        panel.add(t_isbn);
        panel.add(title);
        panel.add(t_title);
        panel.add(author);
        panel.add(t_author);
        panel.add(numofcopies);
        panel.add(t_numofcopies);
        panel.add(yearpub);
        panel.add(t_yearpub);
        panel.add(publisher);
        panel.add(t_publisher);
        panel.add(numpages);
        panel.add(t_numpages);
        buttons.add(ok);
        buttons.add(close);
        //buttons actions listeners
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook.dispose();
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(IsNumber(t_isbn.getText())
                        &&IsNumber(t_numofcopies.getText())
                        &&IsNumber(t_yearpub.getText())
                &&IsNumber(t_numpages.getText()))
                {
                    int i_ISBN = Integer.parseInt(t_isbn.getText());
                    int i_numofcopies = Integer.parseInt(t_numofcopies.getText());
                    int i_yearpub = Integer.parseInt(t_yearpub.getText());
                    int i_numpages = Integer.parseInt(t_numpages.getText());
                    db.insertBook(i_ISBN, t_title.getText(), t_author.getText(), i_numofcopies, i_yearpub, t_publisher.getText(), i_numpages);
                    UpdateFrame(1);
                    JOptionPane.showMessageDialog(null, "Done", "Task completed", JOptionPane.INFORMATION_MESSAGE);
                    addBook.setVisible(true);


                }
            }
        });
        //some frame stuff and layouts
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addBook.add(panel,BorderLayout.NORTH);
        addBook.add(buttons, BorderLayout.SOUTH);
        addBook.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addBook.pack();
        addBook.setLocationRelativeTo(null);
        addBook.setVisible(true);
    }
    //add new user FRAME
    private void addUser()
    {
        JFrame addUser = new JFrame("Add User");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Add User");
        JButton close = new JButton("Close");
        //labels
       // JLabel numsocio= new JLabel("User nº: ");
        JLabel firstname = new JLabel("First Name: ");
        JLabel lastname = new JLabel("Last Name: ");
        JLabel phonenum = new JLabel("Phone No: ");
        JLabel age = new JLabel("Age: ");
        JLabel date = new JLabel("Date: ");
        //textfields
       // JTextField t_numsocio = new JTextField();
        JTextField t_firstname = new JTextField();
        JTextField t_lastname = new JTextField();
        JTextField t_phonenum = new JTextField();
        JTextField t_age = new JTextField();
        JTextField t_date = new JTextField();
        //adding components
       // panel.add(numsocio);
       // panel.add(t_numsocio);
        panel.add(firstname);
        panel.add(t_firstname);
        panel.add(lastname);
        panel.add(t_lastname);
        panel.add(phonenum);
        panel.add(t_phonenum);
        panel.add(age);
        panel.add(t_age);
        panel.add(date);
        panel.add(t_date);
        buttons.add(ok);
        buttons.add(close);
        //buttons actions listeners
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser.dispose();
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(IsNumber(t_phonenum.getText())
                        &&IsNumber(t_age.getText())
                //&&IsNumber(t_numsocio.getText())
                )
                {
                   // int numsocio = Integer.parseInt(t_numsocio.getText());
                    int phonenum = Integer.parseInt(t_phonenum.getText());
                    int age = Integer.parseInt(t_age.getText());


                    String str=t_date.getText();

                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date date1= java.sql.Date.valueOf(str);
                    db.insertUser(t_firstname.getText(),t_lastname.getText(),phonenum,age, date1);
                    UpdateFrame(2);
                    JOptionPane.showMessageDialog(null, "Done", "Task completed", JOptionPane.INFORMATION_MESSAGE);
                    addUser.setVisible(true);
                }
            }
        });
        //some frame stuff and layouts
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addUser.add(panel,BorderLayout.NORTH);
        addUser.add(buttons, BorderLayout.SOUTH);
        addUser.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addUser.pack();
        addUser.setLocationRelativeTo(null);
        addUser.setVisible(true);
    }
    //add new borrowing FRAME
    private void addBorrowing()
    {
        JFrame addBorrowing = new JFrame("Add Borrowing");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Add Borrowing");
        JButton close = new JButton("Close");
        //labels
        JLabel book = new JLabel("Book (ISBN): ");
        JLabel user = new JLabel("User (numsocio): ");
        JLabel dateBorrowing = new JLabel("Date (borrowing): ");
        JLabel dateReturn = new JLabel("Date (return): ");
        //textfields
        JTextField t_book = new JTextField();
        JTextField t_user = new JTextField();
        JTextField t_dateBorrowing = new JTextField();
        JTextField t_dateReturn = new JTextField();
        t_dateReturn.setText("");
        //adding components
        panel.add(book);
        panel.add(t_book);
        panel.add(user);
        panel.add(t_user);
        panel.add(dateBorrowing);
        panel.add(t_dateBorrowing);
        panel.add(dateReturn);
        panel.add(t_dateReturn);
        buttons.add(ok);
        buttons.add(close);
        //buttons actions listeners
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(IsNumber(t_book.getText())
                        &&IsNumber(t_user.getText())
                )
                {
                    int BookIsbn = Integer.parseInt(t_book.getText());
                    int UserNumsocio = Integer.parseInt(t_user.getText());
                    String defaultdate = "0001-01-01";
                    java.sql.Date DateReturn;
                    try{
                       DateReturn=java.sql.Date.valueOf(t_dateReturn.getText());

                    }catch(IllegalArgumentException exception)
                    {
                       DateReturn = java.sql.Date.valueOf(defaultdate);
                       System.out.println(exception);
                    }
                    java.sql.Date DateBorrowing= java.sql.Date.valueOf(t_dateBorrowing.getText());
                    db.insertBorrowing(BookIsbn,UserNumsocio,DateBorrowing,DateReturn);
                    UpdateFrame(3);
                    JOptionPane.showMessageDialog(null, "Done", "Task completed", JOptionPane.INFORMATION_MESSAGE);
                    addBorrowing.setVisible(true);

                }
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBorrowing.dispose();
            }
        });
        //some frame stuff and layouts
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addBorrowing.add(panel,BorderLayout.NORTH);
        addBorrowing.add(buttons, BorderLayout.SOUTH);
        addBorrowing.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addBorrowing.pack();
        addBorrowing.setLocationRelativeTo(null);
        addBorrowing.setVisible(true);
    }


    private void deleteBook()
    {
        JFrame deleteBook = new JFrame("Delete Book");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Delete Book");
        JButton close = new JButton("Close");
        //labels
        JLabel isbn = new JLabel("ISBN: ");
        //textfields
        JTextField t_isbn = new JTextField();
        //adding components
        panel.add(isbn);
        panel.add(t_isbn);
        buttons.add(ok);
        buttons.add(close);
        //buttons actions listeners
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook.dispose();
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(IsNumber(t_isbn.getText()))
                {
                    int i_ISBN = Integer.parseInt(t_isbn.getText());

                   db.deleteBook(i_ISBN);
                   UpdateFrame(1);
                    JOptionPane.showMessageDialog(null, "Done", "Task completed", JOptionPane.INFORMATION_MESSAGE);
                    deleteBook.setVisible(true);
                }
            }
        });
        //some frame stuff and layouts
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        deleteBook.add(panel,BorderLayout.NORTH);
        deleteBook.add(buttons, BorderLayout.SOUTH);
        deleteBook.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        deleteBook.pack();
        deleteBook.setLocationRelativeTo(null);
        deleteBook.setVisible(true);
    }

    private void deleteUser()
    {
        JFrame deleteUser = new JFrame("Delete User");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Delete User");
        JButton close = new JButton("Close");
        //labels
        JLabel numsocio = new JLabel("Num Socio: ");
        //textfields
        JTextField t_numsocio = new JTextField();
        //adding components
        panel.add(numsocio);
        panel.add(t_numsocio);
        buttons.add(ok);
        buttons.add(close);
        //buttons actions listeners
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser.dispose();
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(IsNumber(t_numsocio.getText()))
                {
                    int i_numsocio = Integer.parseInt(t_numsocio.getText());

                 db.deleteUser(i_numsocio);
                 UpdateFrame(2);
                    JOptionPane.showMessageDialog(null, "Done", "Task completed", JOptionPane.INFORMATION_MESSAGE);
                    deleteUser.setVisible(true);
                }
            }
        });
        //some frame stuff and layouts
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        deleteUser.add(panel,BorderLayout.NORTH);
        deleteUser.add(buttons, BorderLayout.SOUTH);
        deleteUser.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        deleteUser.pack();
        deleteUser.setLocationRelativeTo(null);
        deleteUser.setVisible(true);
    }

    //use this to IsNumber for numeric input
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

    //Use this to fill DataTable from SQL
    private JScrollPane GetData(int option) {
        JTable DataOutputTable;
        JScrollPane scrollPane;
        DataOutputTable = db.GetTable(option);
        scrollPane = new JScrollPane(DataOutputTable);
        DataOutputTable.setFillsViewportHeight(true);
        return scrollPane;
        }

    private void UpdateFrame(int option){
        frame.remove(DataTable);
        DataTable = null;
        DataTable = GetData(option);
        frame.add(DataTable);
        frame.repaint();
        frame.setVisible(true);

    }

}
