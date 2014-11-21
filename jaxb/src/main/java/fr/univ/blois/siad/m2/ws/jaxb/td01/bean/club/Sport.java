package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club;

public class Sport extends Association {

    private String sport; // le nom du sport

    private SportType sportType;

    private Integer teamCount;

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Integer getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }
}
