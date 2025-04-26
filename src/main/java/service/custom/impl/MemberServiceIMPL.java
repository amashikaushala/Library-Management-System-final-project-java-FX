package service.custom.impl;

import dto.custom.MemberDTO;
import entity.custom.Member;
import repository.custom.impl.MemberRepositoryIMPL;
import service.custom.MemberService;
import util.exception.ServiceException;
import util.exception.custom.MemberException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class MemberServiceIMPL implements MemberService {

    private final MemberRepositoryIMPL memberRepository = new MemberRepositoryIMPL();

    @Override
    public boolean save(MemberDTO member) {
        Member entity = this.convertDTOtoEntity(member);
        try {
            return memberRepository.save(entity);
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Integer integer) throws MemberException {
        return false;
    }


    @Override
    public boolean delete(String id) throws MemberException {
        try {
            boolean delete = memberRepository.delete(id);
            return delete;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MemberException("Error Occured Contact Developer", e);
        }
    }



    @Override
    public boolean update(MemberDTO memberDTO) throws MemberException {

        Member entity = this.convertDTOtoEntity(memberDTO);

        try {
            boolean isUpdated = memberRepository.update(entity);
            return isUpdated;
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException) {
                if (((SQLException) e).getErrorCode() == 1406) {
                    String message = ((SQLException) e).getMessage();
                    String[] s = message.split("'");
                    throw new MemberException("Data is To Large For " + s[1]);
                }

            }
            throw new MemberException("Error Occurred - contact developer", e);
        }
    }

    @Override
    public boolean delete(MemberDTO memberDTO) throws ServiceException {
        return false;
    }

    public Optional<MemberDTO> search(String id) throws MemberException {
        try {
            Optional<Member> member = memberRepository.search(id);
            if (member.isPresent()) {
                MemberDTO memberDTO =convertEntityToDTO(member.get());
                return Optional.of(memberDTO);
            }
        } catch (SQLException | ClassNotFoundException e) {
           throw new MemberException("Contact Developer",e);
        }
        return Optional.empty();
    }

    @Override
    public List<MemberDTO> getAll() throws MemberException {
        return List.of();
    }


    private Member convertDTOtoEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setAddress(memberDTO.getAddress());
        member.setEmail(memberDTO.getEmail());
        member.setContact(memberDTO.getContact());
        return member;
    }

    private MemberDTO convertEntityToDTO(Member memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setContact(memberEntity.getContact());
        return memberDTO;
    }
}
