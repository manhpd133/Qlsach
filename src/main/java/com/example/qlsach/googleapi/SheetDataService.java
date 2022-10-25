package com.example.qlsach.googleapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SheetDataService {
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/google-sheets-client-secret.json";
    private static final String APPLICATION_NAME = "Google Sheets API Java";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, final JsonFactory jsonFactory) throws IOException {
        // Load client secrets.
        return GoogleCredential.fromStream(Objects.requireNonNull(SheetDataService.class.getResourceAsStream(CREDENTIALS_FILE_PATH)), HTTP_TRANSPORT, jsonFactory)
                .createScoped(SCOPES);
    }

    public List<List<Object>> getSheetsServiceBookStore(String sheetID) throws IOException, GeneralSecurityException {
        try {
            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, JSON_FACTORY)).setApplicationName(APPLICATION_NAME).build();
            ValueRange response = service.spreadsheets().values().get(sheetID, "bookStore!A1:C").execute();

            List<List<Object>> rows = response.getValues();
            if (rows == null || rows.isEmpty()) {
                log.info("=====No data found in google sheets =====");
                return null;
            }

            return rows;

        } catch (Exception e) {
            log.error("Throw exception when synced google sheets =============== {}", e.toString());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }
}
