package fr.hardback.commons.data;

import fr.hardback.commons.DatabaseManager;
import fr.hardback.bungee.core.rank.RankUnit;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

public class AccountProvider {

    protected final String TABLE = "players";

    public void createAccount(UUID uuid, String name){
        if(DatabaseManager.REDIS.getAccountData(uuid) == null){
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            DatabaseManager.DATABASE.query("SELECT uuid FROM " + TABLE + " WHERE uuid='" + uuid + "'", rs -> {
                try {
                    if(!rs.next()){
                        DatabaseManager.DATABASE.update("INSERT INTO " + TABLE + " (uuid, name, rank, credits, coins, mysteryKeys, createdAt) VALUES (" +
                                "'" + uuid + "'," +
                                "'" + name + "'," +
                                "'" + RankUnit.Joueur.getName() + "'," +
                                "'0.0'," +
                                "'0.0'," +
                                "'0'," +
                                "'" + date + "')");

                        DatabaseManager.REDIS.setAccountData(uuid, new AccountData(name, RankUnit.Joueur, 0.0, 0.0, 0, date));
                    }
                    this.loadAccount(uuid);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            });
        }
    }

    public void saveAccount(UUID uuid){
        AccountData accountData = DatabaseManager.REDIS.getAccountData(uuid);
        String name = accountData.getName();
        RankUnit rank = accountData.getRank();
        Double credits = accountData.getCredits(), coins = accountData.getCoins();
        Integer mysteryKeys = accountData.getMysteryKeys();
        Date createdAt = accountData.getCreatedAt();

        DatabaseManager.DATABASE.update("UPDATE " + TABLE + " SET " +
                "name='" + name + "'," +
                "rank='" + rank + "'," +
                "credits='" + credits + "'," +
                "coins='" + coins + "'," +
                "mysteryKeys='" + mysteryKeys + "'," +
                "createdAt='" + createdAt + "' " +
                "WHERE uuid='" + uuid + "'");
    }

    public void loadAccounts(){
        DatabaseManager.DATABASE.query("SELECT uuid, name, rank, credits, coins, mysteryKeys, createdAt FROM " + TABLE, rs -> {
            try {
                while (rs.next()){
                    UUID uuid = UUID.fromString(rs.getString("uuid"));

                    if(DatabaseManager.REDIS.getAccountData(uuid) == null){
                        String name = rs.getString("name");
                        RankUnit rank = RankUnit.getByName(rs.getString("rank"));
                        Double credits = rs.getDouble("credits"), coins = rs.getDouble("coins");
                        Integer mysteryKeys = rs.getInt("mysteryKeys");
                        Date createdAt = rs.getDate("createdAt");

                        DatabaseManager.REDIS.setAccountData(uuid, new AccountData(name, rank, credits, coins, mysteryKeys, createdAt));
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    private void loadAccount(UUID uuid){
        if(DatabaseManager.REDIS.getAccountData(uuid) == null){
            DatabaseManager.DATABASE.query("SELECT name, rank, credits, coins, mysteryKeys, createdAt FROM " + TABLE + " WHERE uuid='" + uuid + "'", rs -> {
                try {
                    while (rs.next()){
                        String name = rs.getString("name");
                        RankUnit rank = RankUnit.getByName(rs.getString("rank"));
                        Double credits = rs.getDouble("credits"), coins = rs.getDouble("coins");
                        Integer mysteryKeys = rs.getInt("mysteryKeys");
                        Date createdAt = rs.getDate("createdAt");

                        DatabaseManager.REDIS.setAccountData(uuid, new AccountData(name, rank, credits, coins, mysteryKeys, createdAt));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
