package edu.umg.programacion3.organigrama.app;

import edu.umg.programacion3.organigrama.app.repository.MemoryTreeRepository;
import edu.umg.programacion3.organigrama.app.repository.MongoTreeRepository;
import edu.umg.programacion3.organigrama.app.repository.PostgresTreeRepository;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Value("${app.storage}")
    private String storage;

    @Bean
    public TreeRepository treeRepository(
            MemoryTreeRepository memoryRepo,
            MongoTreeRepository mongoRepo,
            PostgresTreeRepository postgresRepo
    ) {
        return switch (storage) {
            case "postgres" -> postgresRepo;
            case "mongo" -> mongoRepo;
            default -> memoryRepo;
        };
    }
}