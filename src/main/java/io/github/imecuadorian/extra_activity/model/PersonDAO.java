package io.github.imecuadorian.extra_activity.model;

import io.github.imecuadorian.library.Files;
import io.github.imecuadorian.library.FileType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static final String REGISTER_PATH = "registro.txt";
    private final Files fileManager;

    public PersonDAO() throws IOException {
        fileManager = new Files(REGISTER_PATH);
        fileManager.createFile(FileType.FILE);
    }

    public void save(Person person) throws IOException {
        fileManager.writeFile(person.toString(), false);
    }

    public List<Person> loadAll() throws IOException {
        List<Person> people = new ArrayList<>();
        String content = fileManager.readFile();
        for (String line : content.split(System.lineSeparator())) {
            String[] parts = line.split(";");
            if (parts.length == 3) {
                people.add(new Person(parts[0], parts[1], parts[2]));
            }
        }
        return people;
    }
}
