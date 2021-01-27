package DataModel;

import java.security.PublicKey;

public class Constant {

    public static final String DB_NAME = "info.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/namanjain/Workspace/IdeaProjects/Speed Up Mini Project With Planning/src/DataFiles/"+DB_NAME;

    public static final String TABLE_USER = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_DOB = "dob";

    public static final String TABLE_LOGIN_STATS = "loginStats";
    public static final String COLUMN_LOGIN_STATS_ID = "_id";
    public static final String COLUMN_LOGIN_STATS_USERNAME = "username";
    public static final String COLUMN_LOGIN_STATS_PASSWORD = "password";
    public static final String COLUMN_LOGIN_STATS_STATUS = "status";

    public static final String TABLE_PRACTICE_RECORD = "practiceRecords";
    public static final String COLUMN_PRACTICE_RECORD_INDEX = "sNo";
    public static final String COLUMN_PRACTICE_RECORD_DIFFICULTY = "difficulty";
    public static final String COLUMN_PRACTICE_RECORD_LENGTH = "length";
    public static final String COLUMN_PRACTICE_RECORD_SPEED = "speed";
    public static final String COLUMN_PRACTICE_RECORD_ERROR = "error";

    public static final String TABLE_TEST_RECORD = "testRecords";
    public static final String COLUMN_TEST_RECORD_INDEX = "sNo";
    public static final String COLUMN_TEST_RECORD_DIFFICULTY = "difficulty";
    public static final String COLUMN_TEST_RECORD_LENGTH = "length";
    public static final String COLUMN_TEST_RECORD_SPEED = "speed";
    public static final String COLUMN_TEST_RECORD_ERROR = "error";
    public static final String COLUMN_TEST_RECORD_TIME = "time";

    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY, " + COLUMN_USER_NAME + " TEXT NOT NULL, " + COLUMN_USER_EMAIL + " TEXT NOT NULL, " +
            COLUMN_USER_PASSWORD + " TEXT NOT NULL, " + COLUMN_USER_DOB + " TEXT NOT NULL)";

    public static final String CREATE_TABLE_LOGIN_STATUS = "CREATE TABLE IF NOT EXISTS " + TABLE_LOGIN_STATS + "(" +
            COLUMN_LOGIN_STATS_ID + " INTEGER PRIMARY KEY, " + COLUMN_LOGIN_STATS_USERNAME + " TEXT NOT NULL, " +
            COLUMN_LOGIN_STATS_PASSWORD + " TEXT NOT NULL, " + COLUMN_LOGIN_STATS_STATUS + " INTEGER)";

    public static final String CREATE_TABLE_PRACTICE_RECORD = "CREATE TABLE IF NOT EXISTS " + TABLE_PRACTICE_RECORD + "(" +
            COLUMN_PRACTICE_RECORD_INDEX + " INTEGER NOT NULL, " + COLUMN_PRACTICE_RECORD_DIFFICULTY + " TEXT NOT NULL, " +
            COLUMN_PRACTICE_RECORD_LENGTH + " INTEGER NOT NULL, " + COLUMN_PRACTICE_RECORD_SPEED + " INTEGER NOT NULL, " +
            COLUMN_PRACTICE_RECORD_ERROR + " INTEGER NOT NULL)";

    public static final String CREATE_TABLE_TEST_RECORD = "CREATE TABLE IF NOT EXISTS " + TABLE_TEST_RECORD + "(" +
            COLUMN_TEST_RECORD_INDEX + " INTEGER NOT NULL, " + COLUMN_TEST_RECORD_DIFFICULTY + " TEXT NOT NULL, " +
            COLUMN_TEST_RECORD_LENGTH + " INTEGER NOT NULL, " + COLUMN_TEST_RECORD_SPEED + " INTEGER NOT NULL, " +
            COLUMN_TEST_RECORD_ERROR + " INTEGER NOT NULL, " + COLUMN_TEST_RECORD_TIME + " INTEGER NOT NULL)";

    public static final String INSERT_USER = "INSERT INTO " + TABLE_USER + "(" + COLUMN_USER_NAME +
            ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_DOB + ") VALUES(?, ?, ?, ?)";

    public static final String TRACK_LOGIN_STATUS = "INSERT INTO " + TABLE_LOGIN_STATS + "(" + COLUMN_LOGIN_STATS_USERNAME +
            ", " + COLUMN_LOGIN_STATS_PASSWORD + ", " + COLUMN_LOGIN_STATS_STATUS + ") VALUES(?, ?, ?)";

    public static final String INSERT_PRACTICE_RECORD = "INSERT INTO " + TABLE_PRACTICE_RECORD + " VALUES(?, ?, ?, ?, ?)";

    public static final String INSERT_TEST_RECORD = "INSERT INTO " + TABLE_TEST_RECORD + " VALUES(?, ?, ?, ?, ?, ?)";

    public static final String QUERY_USER_USERNAME = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME +
            " = ?";

    public static final String QUERY_AUTHENTICATION = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE (" +
            COLUMN_USER_NAME + " = ? OR " + COLUMN_USER_EMAIL + " = ?) AND " + COLUMN_USER_PASSWORD + " = ?";

    public static final String QUERY_AUTHENTICATION_FOR_PASSWORD_CHANGE = "SELECT " + COLUMN_USER_ID + " FROM " +
            TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_DOB + " = ?";

    public static final String UPDATE_PASSWORD = "UPDATE " + TABLE_USER + " SET " + COLUMN_USER_PASSWORD + " = ? WHERE "
            + COLUMN_USER_ID + " = ?";

    public static final String UPDATE_LOGIN_STATUS_TO_0 = "UPDATE " + TABLE_LOGIN_STATS + " SET " +
            COLUMN_LOGIN_STATS_STATUS + " = 0";

    public static final String QUERY_STATS_TO_FIND_CURRENT_USER = "SELECT " + COLUMN_LOGIN_STATS_ID + " FROM " +
            TABLE_LOGIN_STATS + " WHERE " + COLUMN_LOGIN_STATS_STATUS + " = 1";

    public static final String QUERY_SIGNED_USER_INFO = "SELECT " + COLUMN_LOGIN_STATS_USERNAME + " FROM " +
            TABLE_LOGIN_STATS + " WHERE " + COLUMN_LOGIN_STATS_STATUS + " = 1";

    public static final String QUERY_GET_TEST_RECORD = "SELECT " + COLUMN_TEST_RECORD_DIFFICULTY + ", " +
            COLUMN_TEST_RECORD_LENGTH + ", " + COLUMN_TEST_RECORD_SPEED + ", " + COLUMN_TEST_RECORD_ERROR +
            ", " + COLUMN_TEST_RECORD_TIME + " FROM " + TABLE_TEST_RECORD + " WHERE " +
            COLUMN_TEST_RECORD_INDEX + " = ?";

    public static final String QUERY_GET_PRACTICE_RECORD = "SELECT " + COLUMN_PRACTICE_RECORD_DIFFICULTY + ", " +
            COLUMN_PRACTICE_RECORD_LENGTH + ", " + COLUMN_PRACTICE_RECORD_SPEED + ", " + COLUMN_PRACTICE_RECORD_ERROR +
            " FROM " + TABLE_PRACTICE_RECORD + " WHERE " + COLUMN_PRACTICE_RECORD_INDEX + " = ?";

    public static final String UPDATE_USER_INFO = "UPDATE " + TABLE_USER + " SET " + COLUMN_USER_NAME +
            " = ?, " + COLUMN_USER_EMAIL + " = ?, " + COLUMN_USER_DOB + " = ?, " + COLUMN_USER_PASSWORD +
            " = ? WHERE " + COLUMN_USER_ID + " = ?";

    public static final String GET_USER_INFO = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ?";


    private Constant() {

    }
}
