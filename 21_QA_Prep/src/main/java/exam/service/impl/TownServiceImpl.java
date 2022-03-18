package exam.service.impl;

import exam.model.ImportTownsDTO;
import exam.model.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    private final Path xmlPath =
        Path.of("src", "main", "resources", "files", "xml", "towns.xml");

    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path xmlPath =
            Path.of("src", "main", "resources", "files", "xml", "towns.xml");

        return String.join("\n", Files.readAllLines(xmlPath));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        List<String> result = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ImportTownsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportTownsDTO towns = (ImportTownsDTO) unmarshaller.unmarshal(new FileReader(xmlPath.toAbsolutePath().toString()));

        towns
            .getTowns()
            .forEach(t -> {
                if (t.isValid()) {
                    Optional<Town> optTown = this.townRepository.findByName(t.getName());

                    if (optTown.isPresent()) {
                        result.add("Town already exists");
                    } else {
                        Town toPersist = this.mapper.map(t, Town.class);
                        this.townRepository.save(toPersist);

                        result.add("Saved Town " + t.getName());
                    }

                } else {
                    result.add("Invalid Town");
                }
            });

        return String.join("\n", result);
    }
}
