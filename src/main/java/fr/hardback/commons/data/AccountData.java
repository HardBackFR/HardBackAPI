package fr.hardback.commons.data;

import fr.hardback.bungee.core.rank.RankUnit;

import java.sql.Date;

public class AccountData{

    private String name;
    private RankUnit rank;
    private Double credits, coins;
    private Integer mysteryKeys;
    private Date createdAt;

    public AccountData(String name, RankUnit rank, Double credits, Double coins, Integer mysteryKeys, Date createdAt) {
        this.name = name;
        this.rank = rank;
        this.credits = credits;
        this.coins = coins;
        this.mysteryKeys = mysteryKeys;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RankUnit getRank() {
        return rank;
    }

    public void setRank(RankUnit rank) {
        this.rank = rank;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Double getCoins() {
        return coins;
    }

    public void setCoins(Double coins) {
        this.coins = coins;
    }

    public Integer getMysteryKeys() {
        return mysteryKeys;
    }

    public void setMysteryKeys(Integer mysteryKeys) {
        this.mysteryKeys = mysteryKeys;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
