package WorkWithFiles;

import java.util.List;
import java.io.IOException;


public interface StoreFile<T> {
    void WriteToFile(List<T> list) throws IOException;
    List<T> ReadFromFile() throws IOException;
}
