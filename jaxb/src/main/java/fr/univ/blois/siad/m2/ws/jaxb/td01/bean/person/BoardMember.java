package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person;

public class BoardMember extends Adherent {

    private BoardMemberType boardMemberType;

    public BoardMemberType getBoardMemberType() {
        return boardMemberType;
    }

    public void setBoardMemberType(BoardMemberType boardMemberType) {
        this.boardMemberType = boardMemberType;
    }
}
