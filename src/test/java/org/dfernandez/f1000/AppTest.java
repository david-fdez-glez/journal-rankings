package org.dfernandez.f1000;



import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import org.junit.*;

import org.dfernandez.f1000.model.Journal;


import org.dfernandez.f1000.service.JournalRanker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    SimpleDateFormat formatter;
    Date utilDate ;
    Journal journalA;
    Journal journalB;
    Journal journalC;
    Journal journal;
    List<Journal> allJournals;
    List<Journal> list;
    List<Journal> actualSolution;
    List<Journal> expectedSolution;

    JournalRanker journalRanker;

    @Before
    public void before() {
        formatter = new SimpleDateFormat("yyyy");
        journalRanker = new JournalRanker();
    }

    /**
     *  Scenario 1 Test
     */
    @Test
    public void rankJournalsTest() {
        try {
            utilDate = formatter.parse("2015");
            journalA = new Journal("Journal A",5.6,utilDate);
            journalB = new Journal("Journal B",2.4,utilDate);
            journalC = new Journal("Journal C",3.1,utilDate);
            allJournals = new ArrayList<Journal>();
            allJournals.add(journalA);
            allJournals.add(journalB);
            allJournals.add(journalC);

            System.out.println("\n\nScenario 1: Rank journals\n");
            System.out.println("Given the following journals have scores for 2015:\n" +
                    "Journal A = 5.6, Journal B = 2.4, Journal C = 3.1");

            actualSolution = new ArrayList<Journal>() ;
            actualSolution = journalRanker.rankJournals(allJournals,utilDate,false);

            System.out.println("\nResult:\n" + journalRanker.toList(actualSolution));

            // Create  Solution to compare
            expectedSolution = new ArrayList<Journal>();
            journal =  journalA;
            journal.setRank(1);
            expectedSolution.add(journal);
            journal = journalC;
            journal.setRank(2);
            expectedSolution.add(journal);
            journal =  journalB;
            journal.setRank(3);
            expectedSolution.add(journal);
            System.out.println("\nExpected Solution \n" + journalRanker.toList(expectedSolution));
            System.out.println("\n\n*****************************************************************************\n");
            assertThat(actualSolution, equalTo(expectedSolution));


        }   catch (ParseException e){
            System.out.println("Error rankJournalsTest: " + e.toString());
        }


    }




    /**
     *  Scenario 2 Test
     */
    @Test
    public void sharedRankTest() {
        try {
            utilDate = formatter.parse("2014");
            journalA = new Journal("Journal A",2.2,utilDate);
            journalB = new Journal("Journal B",6.2,utilDate);
            journalC = new Journal("Journal C",6.2,utilDate);
            allJournals = new ArrayList<Journal>();
            allJournals.add(journalA);
            allJournals.add(journalB);
            allJournals.add(journalC);

            System.out.println("\n\nScenario 2: Rank journals with a shared rank \n");
            System.out.println("Given the following journals have scores for 2014:\n" +
                    "Journal A = 2.2, Journal B = 6.2, Journal C = 6.2");

            actualSolution = new ArrayList<Journal>() ;
            actualSolution = journalRanker.rankJournals(allJournals,utilDate,false);

            System.out.println("\nResult:\n" + journalRanker.toList(actualSolution));

            // Create  Solution to compare
            expectedSolution = new ArrayList<Journal>();
            journal = journalB;
            journal.setRank(1);
            expectedSolution.add(journal);
            journal = journalC;
            journal.setRank(1);
            expectedSolution.add(journal);
            journal = journalA;
            journal.setRank(3);
            expectedSolution.add(journal);
            System.out.println("\nExpected Solution \n" + journalRanker.toList(expectedSolution));
            System.out.println("\n\n*****************************************************************************\n");

            assertThat(actualSolution, equalTo(expectedSolution));


        }   catch (ParseException e){
            System.out.println("Error sharedRankTest: " + e.toString());
        }

    }



    /**
     *  Scenario 3 Test
     */
    @Test
    public void excludeReviewTest() {
        try {
            utilDate = formatter.parse("2013");
            journalA = new Journal("Journal A",5.6,utilDate);
            journalA.setReview(true);
            journalB = new Journal("Journal B",2.4,utilDate);
            journalC = new Journal("Journal C",3.1,utilDate);
            allJournals = new ArrayList<Journal>();
            allJournals.add(journalA);
            allJournals.add(journalB);
            allJournals.add(journalC);

            System.out.println("\n\nScenario 3: Rank journals excluding review journals\n");
            System.out.println("Given the following journals have scores for 2013:\n" +
                    "Journal A = 5.6, Journal B = 2.4, Journal C = 3.1\n" +
                    "And Journal A is a review journal");

            actualSolution = new ArrayList<Journal>() ;
            actualSolution = journalRanker.rankJournals(allJournals,utilDate,false);
            System.out.println("\nResult:\n" + journalRanker.toList(actualSolution));

            // Create  Solution to compare
            expectedSolution = new ArrayList<Journal>();
            journal = journalC;
            journal.setRank(1);
            expectedSolution.add(journal);
            journal = journalB;
            journal.setRank(2);
            expectedSolution.add(journal);

            System.out.println("\nExpected Solution \n" + journalRanker.toList(expectedSolution));
            System.out.println("\n\n*****************************************************************************\n");

            assertThat(actualSolution, equalTo(expectedSolution));

        }   catch (ParseException e){
            System.out.println("Error excludeReviewTest: " + e.toString());
        }


    }

    /**
     *
     */
    @Test
    public void testOneElement() {
        try {
            utilDate = formatter.parse("2010");
            journalA = new Journal("Journal A",5.6,utilDate);
            allJournals = new ArrayList<Journal>();
            allJournals.add(journalA);

            System.out.println("\n\nTest One Element\n");
            System.out.println("Given the following journals have scores for 2010:\n" +
                    "Journal A = 5.6");

            actualSolution = new ArrayList<Journal>() ;
            actualSolution = journalRanker.rankJournals(allJournals,utilDate,false);

            System.out.println("\nResult:\n" + journalRanker.toList(actualSolution));

            // Create  Solution to compare
            expectedSolution = new ArrayList<Journal>();
            journal =  journalA;
            journal.setRank(1);
            expectedSolution.add(journal);

            System.out.println("\nExpected Solution \n" + journalRanker.toList(expectedSolution));
            System.out.println("\n\n*****************************************************************************\n");

            assertThat(actualSolution, equalTo(expectedSolution));


        }   catch (ParseException e){
            System.out.println("Error testOneElement: " + e.toString());
        }

    }

    /**
     *
     */
    @Test
    public void testEmptyElement() {
        try {
            utilDate = formatter.parse("2009");
            allJournals = new ArrayList<Journal>();

            System.out.println("\n\nTest Empty Element\n");
            System.out.println("Given the following journals have scores for 2009:\n" +
                    "");

            actualSolution = new ArrayList<Journal>() ;
            actualSolution = journalRanker.rankJournals(allJournals,utilDate,false);

            System.out.println("\nResult:\n" + journalRanker.toList(actualSolution));

            // Create  Solution to compare
            expectedSolution = new ArrayList<Journal>();

            System.out.println("\nExpected Solution \n" + journalRanker.toList(expectedSolution));
            System.out.println("\n\n*****************************************************************************\n");

            assertThat(actualSolution, equalTo(expectedSolution));


        }   catch (ParseException e){
            System.out.println("Error testEmptyElement: " + e.toString());
        }

    }
}