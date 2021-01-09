package DataModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class ParagraphGen {

    public static final String DB_NAME = "words.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/namanjain/Workspace/IdeaProjects/Speed Up Mini Project With Planning/src/DataFiles/"+DB_NAME;

    public static final String TABLE_WORD = "wordList";
    public static final String COLUMN_WORD_ID = "_id";
    public static final String COLUMN_WORD_EASY_WORDS = "easyWords";
    public static final String COLUMN_WORD_MEDIUM_WORDS = "mediumWords";
    public static final String COLUMN_WORD_HARD_WORDS = "hardWords";

    public static final String CREATE_TABLE_WORD = "CREATE TABLE IF NOT EXISTS " + TABLE_WORD + "(" + COLUMN_WORD_ID +
            " INTEGER PRIMARY KEY, " + COLUMN_WORD_EASY_WORDS + " TEXT NOT NULL, " + COLUMN_WORD_MEDIUM_WORDS +
            " TEXT NOT NULL, " + COLUMN_WORD_HARD_WORDS + " TEXT NOT NULL)";

    public static final String INSERT_WORD = "INSERT INTO " + TABLE_WORD + "(" + COLUMN_WORD_EASY_WORDS +
            ", " + COLUMN_WORD_MEDIUM_WORDS + ", " + COLUMN_WORD_HARD_WORDS + ") VALUES(?, ?, ?)";

    public static final String QUERY_GET_EASY_WORD = "SELECT " + COLUMN_WORD_EASY_WORDS + " FROM " + TABLE_WORD + " WHERE " +
            COLUMN_WORD_ID + " = ?";
    public static final String QUERY_GET_MEDIUM_WORD = "SELECT " + COLUMN_WORD_MEDIUM_WORDS + " FROM " + TABLE_WORD + " WHERE " +
            COLUMN_WORD_ID + " = ?";
    public static final String QUERY_GET_HARD_WORD = "SELECT " + COLUMN_WORD_HARD_WORDS + " FROM " + TABLE_WORD + " WHERE " +
            COLUMN_WORD_ID + " = ?";

    private PreparedStatement query_easyWord;
    private PreparedStatement query_mediumWord;
    private PreparedStatement query_hardWord;
//    private PreparedStatement insert_word;

    private static Connection connection;
    private final static ParagraphGen instance = new ParagraphGen();

    private ParagraphGen() {
    }

    public static ParagraphGen getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            query_easyWord = connection.prepareStatement(QUERY_GET_EASY_WORD);
            query_mediumWord = connection.prepareStatement(QUERY_GET_MEDIUM_WORD);
            query_hardWord = connection.prepareStatement(QUERY_GET_HARD_WORD);
//            insert_word = connection.prepareStatement(INSERT_WORD);
            return true;
        } catch (SQLException e){
            System.out.println("Error in making connection " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if(query_easyWord != null) {
                query_easyWord.close();
            }
            if(query_mediumWord != null) {
                query_mediumWord.close();
            }
            if(query_hardWord != null) {
                query_hardWord.close();
            }
//            if(insert_word != null){
//                insert_word.close();
//            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Unable to close database " + e.getMessage());
        }
    }

//    public void create(){
//        try (Statement statement = connection.createStatement()){
//            statement.execute(CREATE_TABLE_WORD);
//        } catch (SQLException e){
//            System.out.println("Unable to create " + e.getMessage());
//        }
//    }

    public String generateParagraph(int length, String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return easyParaGen(length).trim();
            case "medium":
                return mediumParaGen(length).trim();
            case "hard":
                return hardParaGen(length).trim();
        }

        return "";
    }

    private String easyParaGen(int length){
        StringBuilder para = new StringBuilder("");
        try {
            Random random = new Random();
            ResultSet resultSet;
            for(int i=0; i<length; i++){
                int id = random.nextInt(200);
                query_easyWord.setInt(1, id);
                resultSet = query_easyWord.executeQuery();
                while(resultSet.next()){
                    para.append(resultSet.getString(1));
                    para.append(" ");
                }
            }
        } catch (SQLException e){
            System.out.println("Couldn't generate easy paragraph "+e.getMessage());
            e.printStackTrace();
        }
        return para.toString();
    }

    private String mediumParaGen(int length){
        StringBuilder para = new StringBuilder("");
        ResultSet resultSet;
        try {
            Random random = new Random();
            for(int i=0; i<length; i++){
                int id = random.nextInt(200);
                query_mediumWord.setInt(1, id);
                resultSet = query_mediumWord.executeQuery();
                while(resultSet.next()){
                    para.append(resultSet.getString(1));
                    para.append(" ");
                }
            }
        } catch (SQLException e){
            System.out.println("Couldn't generate medium paragraph "+e.getMessage());
        }
        return para.toString();
    }

    private String hardParaGen(int length){
        StringBuilder para = new StringBuilder("");
        ResultSet resultSet;
        try {
            Random random = new Random();
            for(int i=0; i<length; i++){
                int id = random.nextInt(200);
                query_hardWord.setInt(1, id);
                resultSet = query_hardWord.executeQuery();
                while(resultSet.next()){
                    para.append(resultSet.getString(1));
                    para.append(" ");
                }
            }
        } catch (SQLException e){
            System.out.println("Couldn't generate hard paragraph "+e.getMessage());
        }
        return para.toString();
    }

//    private void transferData() {
//        try (Scanner sc1 = new Scanner(new BufferedReader(new FileReader("src/DataFiles/easyWord.txt")));
//             Scanner sc2 = new Scanner(new BufferedReader(new FileReader("src/DataFiles/mediumWord.txt")));
//             Scanner sc3 = new Scanner(new BufferedReader(new FileReader("src/DataFiles/hardWord.txt")))){
//            int counter = 0;
//            while (sc1.hasNextLine() && sc2.hasNextLine() && sc3.hasNextLine()){
//                String easyWord = sc1.nextLine();
//                String mediumWord = sc2.nextLine();
//                String hardWord = sc3.nextLine();
//                counter++;
//                System.out.println(counter+".)"+easyWord+", "+mediumWord+", "+hardWord);
//                insert(easyWord, mediumWord, hardWord);
//            }
//        } catch (IOException e){
//            System.out.println("Unable to read file " + e.getMessage());
//        }
//    }
//
//    private void insert(String easyWord, String mediumWord, String hardWord){
//        try {
//            insert_word.setString(1, easyWord);
//            insert_word.setString(2, mediumWord);
//            insert_word.setString(3, hardWord);
//            insert_word.execute();
//        } catch (SQLException e){
//            System.out.println("Couldn't insert " + e.getMessage());
//        }
//    }
}
