package service.custom.impl;

import dto.custom.PublisherDTO;
import entity.custom.Publisher;
import repository.custom.PublisherRepository;
import repository.custom.impl.PublisherRepositoryIMPL;
import service.custom.PublisherService;
import util.exception.ServiceException;
import util.exception.custom.BookException;
import util.exception.custom.PublisherException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherServiceIMPL implements PublisherService {

    private PublisherRepository repository= new PublisherRepositoryIMPL();

    @Override
    public boolean save(PublisherDTO publisherDTO) throws PublisherException {
       Publisher publisher=convertToEntity(publisherDTO);
        try {
          return   repository.save(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException)e).getErrorCode()==1062){
                throw new PublisherException("ID Already Exists-Cannot Save.");
            }else if (((SQLException)e).getErrorCode()==1406){
                String message = ((SQLException)e).getMessage();
                String[]s=message.split("");
                throw new PublisherException("Data is To Large For "+s[1]);

            }
            throw new PublisherException("Error Occured Developer",e);
        }

    }

    @Override
    public boolean update(PublisherDTO publisherDTO) throws PublisherException {
       Publisher publisher=convertToEntity(publisherDTO);
        try {
          return   repository.update(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("");
                throw new PublisherException("Data is To Large For " + s[1]);
            }
            throw new PublisherException("Error Occured Developer",e);
        }

    }

    @Override
    public boolean delete(PublisherDTO publisherDTO) throws PublisherException {
        try {
         return   repository.delete(publisherDTO.getId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Not Implement yet fully");
        }

    }

    @Override
    public Optional<PublisherDTO> search(Integer integer) throws PublisherException {
        try {
           Optional <Publisher> search= repository.search(integer);
        if (search.isPresent()){
            return  Optional.of(convertToDTO(search.get()));
        }
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("contact Developer",e);
        }
        return Optional.empty();
    }

    @Override
    public List<PublisherDTO> getAll() throws PublisherException {
        try {
            List<Publisher>all= repository.getAll();
            ArrayList<PublisherDTO>publisherDTOS=new ArrayList<>();
        for (Publisher publisher:all){
            PublisherDTO publisherDTO=convertToDTO(publisher);
            publisherDTO.add(publisherDTO);
        }
        return publisherDTOS;
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Contact Developer",e);
        }

    }

    private  PublisherDTO convertToDTO(Publisher publisher){
        PublisherDTO publisherDTO=new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setLocation(publisher.getLocation());
        publisherDTO.setContact(publisher.getContact());
        return publisherDTO;
    }

    private Publisher convertToEntity(PublisherDTO publisherDTO){
        Publisher publisher= new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setLocation(publisherDTO.getLocation());
        publisher.setContact(publisherDTO.getContact());
        return publisher;
    }
}
