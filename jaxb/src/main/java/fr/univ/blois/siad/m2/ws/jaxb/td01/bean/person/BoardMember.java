package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person;

public class BoardMember extends Adherent {

    private BoardMemberType boardMemberType;

    public BoardMember() {
    }

    public BoardMember(Long id, String name, String surname, String membershipDate, BoardMemberType boardMemberType) {
        super(id, name, surname, membershipDate);
        this.boardMemberType = boardMemberType;
    }

    public BoardMemberType getBoardMemberType() {
        return boardMemberType;
    }

    public void setBoardMemberType(BoardMemberType boardMemberType) {
        this.boardMemberType = boardMemberType;
    }
}
