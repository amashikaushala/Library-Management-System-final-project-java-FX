package service.custom.impl;

import dto.MemberDTO;
import entity.Member;
import repository.custom.impl.MemberRepositoryIMPL;
import util.exception.custom.MemberException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberServiceIMPL {
    private static final Logger LOGGER = Logger.getLogger(MemberServiceIMPL.class.getName());
    private final MemberRepositoryIMPL memberRepository = new MemberRepositoryIMPL();

    public boolean addMember(MemberDTO member) {
        Member entity = this.convertDTOtoEntity(member);
        try {
            return memberRepository.saveMember(entity);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error saving member", e);
            return false;
        }
    }

    public boolean delete(String id) throws MemberException {
        try {
            boolean delete = memberRepository.delete(id);
            return delete;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MemberException("Error Occured Contact Developer", e);
        }
    }

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

    public Optional<MemberDTO> search(String id) {
        try {
            Optional<Member> member = memberRepository.searchCustomer(id);
            if (member.isPresent()) {
                MemberDTO memberDTO =convertEntityToDTO(member.get());
                return Optional.of(memberDTO);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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

    private Member convertEntityToDTO(Member memberEntity) {
        Member memberDTO = new Member();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setContact(memberEntity.getContact());
        return memberDTO;
    }
}
