package DataModel;

public class Constant {

    public static final String DB_NAME = "info.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\WorkModeON\\IdeaProjects\\Speed Up Mini Project With Planning\\src\\DataFiles\\"+DB_NAME;

    public static final String TABLE_USER = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_DOB = "dob";
    public static final String COLUMN_USER_PRACTICE = "practice";
    public static final String COLUMN_USER_TEST = "test";

    public static final String TABLE_PRACTICE_RECORD = "practiceRecords";
    public static final String COLUMN_PRACTICE_RECORD_ID = "_id";
    public static final String COLUMN_PRACTICE_RECORD_RECORD = "record";

    public static final String TABLE_TEST_RECORD = "testRecords";
    public static final String COLUMN_TEST_RECORD_ID = "_id";
    public static final String COLUMN_TEST_RECORD_RECORD = "record";

    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY, " + COLUMN_USER_NAME + " TEXT NOT NULL, " + COLUMN_USER_EMAIL + " TEXT NOT NULL, " +
            COLUMN_USER_PASSWORD + " TEXT NOT NULL, " + COLUMN_USER_DOB + " TEXT NOT NULL, " + COLUMN_USER_PRACTICE +
            " INTEGER, " + COLUMN_USER_TEST + " INTEGER)";

    public static final String CREATE_TABLE_PRACTICE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRACTICE_RECORD + "(" +
            COLUMN_PRACTICE_RECORD_ID + " INTEGER PRIMARY KEY, " + COLUMN_PRACTICE_RECORD_RECORD + " INTEGER)";

    public static final String CREATE_TABLE_TEST = "CREATE TABLE IF NOT EXISTS " + TABLE_TEST_RECORD + "(" +
            COLUMN_TEST_RECORD_ID + " INTEGER PRIMARY KEY, " + COLUMN_TEST_RECORD_RECORD + " INTEGER)";



    private Constant() {

    }
}
