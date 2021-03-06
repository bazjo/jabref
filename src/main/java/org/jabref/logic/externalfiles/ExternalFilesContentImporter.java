package org.jabref.logic.externalfiles;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import org.jabref.logic.importer.ImportFormatPreferences;
import org.jabref.logic.importer.OpenDatabase;
import org.jabref.logic.importer.ParserResult;
import org.jabref.logic.importer.fileformat.PdfContentImporter;
import org.jabref.logic.importer.fileformat.PdfXmpImporter;
import org.jabref.logic.preferences.TimestampPreferences;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.util.FileUpdateMonitor;

public class ExternalFilesContentImporter {

    private final ImportFormatPreferences importFormatPreferences;
    private final TimestampPreferences timestampPreferences;

    public ExternalFilesContentImporter(ImportFormatPreferences importFormatPreferences, TimestampPreferences timestampPreferences) {
        this.importFormatPreferences = importFormatPreferences;
        this.timestampPreferences = timestampPreferences;
    }

    public List<BibEntry> importPDFContent(Path file) {
        return new PdfContentImporter(importFormatPreferences).importDatabase(file, StandardCharsets.UTF_8).getDatabase().getEntries();
    }

    public List<BibEntry> importXMPContent(Path file) {
        return new PdfXmpImporter(importFormatPreferences.getXmpPreferences()).importDatabase(file, StandardCharsets.UTF_8).getDatabase().getEntries();
    }

    public List<BibEntry> importFromBibFile(Path bibFile, FileUpdateMonitor fileUpdateMonitor) {
        ParserResult parserResult = OpenDatabase.loadDatabase(bibFile.toString(), importFormatPreferences, timestampPreferences, fileUpdateMonitor);
        return parserResult.getDatabaseContext().getEntries();
    }
}
