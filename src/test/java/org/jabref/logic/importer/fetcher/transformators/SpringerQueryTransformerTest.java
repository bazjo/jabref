package org.jabref.logic.importer.fetcher.transformators;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpringerQueryTransformerTest implements InfixTransformerTest {

    @Override
    public String getAuthorPrefix() {
        return "name:";
    }

    @Override
    public AbstractQueryTransformer getTransformator() {
        return new SpringerQueryTransformer();
    }

    @Override
    public String getUnFieldedPrefix() {
        return "";
    }

    @Override
    public String getJournalPrefix() {
        return "journal:";
    }

    @Override
    public String getTitlePrefix() {
        return "title:";
    }

    @Override
    public void convertYearField() throws Exception {
        Optional<String> searchQuery = getTransformator().parseQueryStringIntoComplexQuery("year:2015");
        Optional<String> expected = Optional.of("date:2015*");
        assertEquals(expected, searchQuery);
    }

    @Override
    public void convertYearRangeField() throws Exception {
        Optional<String> searchQuery = getTransformator().parseQueryStringIntoComplexQuery("year-range:2012-2015");
        Optional<String> expected = Optional.of("date:2012* OR date:2013* OR date:2014* OR date:2015*");
        assertEquals(expected, searchQuery);
    }
}
