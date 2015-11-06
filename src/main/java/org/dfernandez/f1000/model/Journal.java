package org.dfernandez.f1000.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 06/11/15
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public class Journal {
    private int rank;
    private String name;
    private double score;
    private boolean review;
    private Date year;

    public Journal(String name, double score, Date year) {
        this.name = name;
        this.score = score;
        this.year = year;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String toString() {
        return rank +", " + name + ": " + score ;
    }

}
