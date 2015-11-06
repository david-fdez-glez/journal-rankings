package org.dfernandez.f1000.util;

import org.dfernandez.f1000.model.Journal;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 06/11/15
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ComparatorJournals implements Comparator<Journal> {

    @Override
    public int compare(Journal journal1, Journal journal2) {

        int result = Double.compare(journal1.getScore(), journal2.getScore()) ;
        if(result !=0)
            return result*(-1);
        return journal1.getName().compareTo(journal2.getName());
    }
}
