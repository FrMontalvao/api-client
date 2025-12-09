package com.francisco.api_client.service;

import com.francisco.api_client.dto.ClientDTO;
import com.francisco.api_client.mapper.ClientMapper;
import com.francisco.api_client.model.Client;
import com.francisco.api_client.repositoty.ClientRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor

@Service
@Transactional(readOnly = true)
public class ClientService {
    private  ClientRepository repository;
    private ClientMapper mapper;
    private ClientService(ClientRepository repository, ClientMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    //Creat
    @Transactional
    public ClientDTO create(ClientDTO clientDTO){
        Client client = mapper.toEntity(clientDTO);
        return mapper.toDTO(client);
    }

    // Read
    public ClientDTO findByID(Long id){
       Client client =  repository.findById(id).orElseThrow(
               ()-> new RuntimeException("Nao existe esse id: "+ id)
       );
       return mapper.toDTO(client);
    }

    // Read
    public List<ClientDTO> findAll(){
        List<Client> client =  repository.findAll();
        return client.stream()
                .map(mapper::toDTO)
                .toList();
    }

    //Update
    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO){
        Client client = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Nao existe esse id: "+ id)
        );
        mapper.updateFromDTO(clientDTO,client);
        client = repository.save(client);
        return mapper.toDTO(client);
    }

    //Delete
    public void delete(Long id){
        Client client = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Nao existe esse id: "+ id)
        );
        client.setActive(false);
        client = repository.save(client);
    }
}
