package seedu.itlogger.storage;

import seedu.itlogger.IssueList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StorageFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "itlogger.txt";
    public final Path path;


    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath)  {
        path = Paths.get(filePath);
    }

    /**
     * Saves the {@code IssueList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(IssueList issueList) throws StorageOperationException {
        try {
            List<String> encodedItLogger = ItLoggerEncoder.encodeItLogger(issueList);
            Files.write(path, encodedItLogger);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code Vector of Defects} data into the IssueList.
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public void load(IssueList issueList) throws StorageOperationException, FileNotFoundException {
        if (Files.exists(path)) {
            try {
                issueList.loadIssues(ItLoggerDecoder.decodeIssueList(Files.readAllLines(path)));
            } catch (IOException e) {
                throw new StorageOperationException("File contents in wrong format");
            }
        }
    }


    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}
