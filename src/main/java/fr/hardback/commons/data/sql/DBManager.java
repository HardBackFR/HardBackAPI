package fr.hardback.commons.data.sql;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public enum DBManager {

    PLAYERS_INFO(new DBCredentials(Dotenv.configure().load().get("DB-HOST"), Dotenv.configure().load().get("DB-USER"), Dotenv.configure().load().get("DB-PASSWORD"), Dotenv.configure().load().get("DB-DBNAME"), Integer.parseInt(Objects.requireNonNull(Dotenv.configure().load().get("DB-PORT")))));

    private final DBAccess dbAccess;

    DBManager(DBCredentials dbCredentials){
        this.dbAccess = new DBAccess(dbCredentials);
    }

    public DBAccess getDBAccess() {
        return dbAccess;
    }

    public static void initAllDBConnection(){
        System.out.println("-----> DB <-----");
        for(DBManager dbManager : values()){
            dbManager.dbAccess.initPool();
            System.out.println("Connected to " + dbManager.getDBAccess().credentials.getDbName());
        }
        System.out.println("---------------");
    }

    public static void closeAllDBConnection(){
        System.out.println("-----> DB <-----");
        for(DBManager dbManager : values()){
            dbManager.dbAccess.closePool();
            System.out.println("Disconnected from " + dbManager.getDBAccess().credentials.getDbName());
        }
        System.out.println("---------------");
    }
}
