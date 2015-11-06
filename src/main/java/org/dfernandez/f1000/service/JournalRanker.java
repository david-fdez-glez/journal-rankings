package org.dfernandez.f1000.service;

import org.dfernandez.f1000.model.Journal;
import org.dfernandez.f1000.util.ComparatorJournals;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 06/11/15
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class JournalRanker {


    /**
     *
     * @param allJournals
     * @param year           Filter Rank Journals By Year
     * @param isReview  Filter Rank Journals By review
     * @return
     */
    public List<Journal> rankJournals(List<Journal> allJournals,Date year, boolean isReview) {

        SortedSet<Journal> setJournals = new TreeSet<Journal>(new ComparatorJournals());

        setJournals.addAll(allJournals)  ;

        Map<Integer, List<Journal>> mapRankJournals = filterByYear(year, setJournals, isReview);

        List<Journal> rankJournalsList = flattenJournalList(mapRankJournals);

        return  rankJournalsList;
    }


    /**
     *
     * @param year      Filter Rank Journals By Year
     * @param setJournals  All available Journals ordered
     * @param isReview  Filter Rank Journals By boolean review
     * @return
     */
    private  Map<Integer,List<Journal>> filterByYear(Date year, SortedSet<Journal> setJournals, boolean isReview) {

        Map<Integer, List<Journal>> mapRankJournalsFiltered = new TreeMap<Integer, List<Journal>>();
        List<Journal> list = new ArrayList<Journal>();
        double lastScore = 0;
        int rank = 0;
        int counter = 0;

        for(Journal journal:setJournals) {

            if(!(journal.isReview()^isReview) && (year.getTime() == journal.getYear().getTime() )) {

                // First value
                if(rank == 0) {
                    rank++;
                    journal.setRank(rank);
                    lastScore = journal.getScore();
                    list.add(journal) ;
                    if(counter == setJournals.size() -1) {
                        mapRankJournalsFiltered.put(rank,list);
                    }
                    counter++;
                    continue;
                }

                if(Double.compare(lastScore,journal.getScore()) !=0 ) {
                    // Add Values to Map
                    mapRankJournalsFiltered.put(rank,list);
                    rank = rank + list.size();
                    list = new ArrayList<Journal>();
                    journal.setRank(rank);
                    list.add(journal) ;
                } else {
                    journal.setRank(rank);
                    list.add(journal);
                }

                lastScore = journal.getScore();
            }
            if(counter == setJournals.size() -1) {
                mapRankJournalsFiltered.put(rank,list);
            }
            counter++;
        }

        return mapRankJournalsFiltered ;
    }


    /**
     *
     * @param map
     * @return
     */
    private List<Journal> flattenJournalList (Map<Integer, List<Journal>> map){

        List<Journal> rankJournalsList = new ArrayList<Journal>();

        for (Map.Entry<Integer, List<Journal>> entry: map.entrySet()) {
            rankJournalsList.addAll(entry.getValue());
        }
        return rankJournalsList;
    }


    public String toList(List<Journal> listJournal) {
        StringBuilder sb = new StringBuilder("");
        String sep = "\n";

        for(Journal journal:listJournal) {
            sb.append(sep).append(journal.toString()).append(sep);
        }
        return sb.toString();
    }
}
