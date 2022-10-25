package com.example.qlsach.googleapi;

import com.example.qlsach.model.Book;
import com.example.qlsach.model.BookStore;
import com.example.qlsach.reponsitory.BookRepository;
import com.example.qlsach.reponsitory.BookStoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Log4j2
@Service
public class SheetsServiceUtil {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private SheetDataService sheetDataService;

    @Autowired
    private BookStoreRepository bookStoreRepository;
    private BookRepository bookRepository;

    public void renderVocabFromGoogleSheetToDatabase() throws IOException, GeneralSecurityException {
        log.info("Start to retrieve test data from GoogleSheet");
        List<List<Object>> valueRanges = getDataFromGoogleSheetBookStore("bookStore");
        convertGoogleSheetDataToBookStore(valueRanges);
        log.info("Render test data to database successfully!");
    }

    public List<List<Object>> getDataFromGoogleSheetBookStore(String sheetName) throws IOException, GeneralSecurityException {
        String SPREADSHEET_ID = "1Po8QePukB6nZAPiSdz1Udivj9SwqxlWXulFTHcVI9-4";
        return sheetDataService.getSheetsServiceBookStore(SPREADSHEET_ID);
    }

    private final int ID_COLUMN = 0;
    private final int NAME_COLUMN = 1;
    private final int ADDRESS_COLUMN = 2;

    public void convertGoogleSheetDataToBookStore(List<List<Object>> rows) {
        log.info("=======> syncing data: " + rows.size());
        for (List<Object> row : rows) {
            String id = (String) row.get(ID_COLUMN);
            String name = (String) row.get(NAME_COLUMN);
            String address = (String) row.get(ADDRESS_COLUMN);
            BookStore bookStore = new BookStore(Long.parseLong(id), name, address);
            bookStoreRepository.save(bookStore);
        }
    }

}
