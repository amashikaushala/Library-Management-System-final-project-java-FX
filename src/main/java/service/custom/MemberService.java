package service.custom;

import dto.custom.MemberDTO;
import service.CrudService;
import util.exception.custom.MemberException;

import java.util.Optional;

public interface MemberService extends CrudService <MemberDTO,String> {

    boolean save(MemberDTO member);

    boolean delete(Integer integer) throws MemberException;

    boolean delete(String id) throws MemberException;

    boolean update(MemberDTO memberDTO) throws MemberException;

    Optional<MemberDTO> search(String id) throws MemberException;


}
