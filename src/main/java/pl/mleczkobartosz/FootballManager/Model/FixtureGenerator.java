package pl.mleczkobartosz.FootballManager.Model;

import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Service.LeagueService;

import java.util.*;

public class FixtureGenerator {

    private List<FootballMatch> footballMatches;

    private Set<Club> clubs = new HashSet<>();

    private final LeagueService leagueService;

    public FixtureGenerator(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    public FixtureGenerator(List<FootballMatch> footballMatches, Set<Club> clubs, LeagueService leagueService) {
        this.footballMatches = footballMatches;
        this.clubs = clubs;
        this.leagueService = leagueService;
    }

    public List<List<FootballMatch>> generate(Long id){

        clubs = leagueService.findClubs(id);
        List<Club> clubList = new ArrayList<>();
        List<FootballMatch> fixture = new ArrayList<>();
        clubList.addAll(clubs);
        Collections.sort(clubList, new Comparator<Club>() {
            @Override
            public int compare(Club o1, Club o2) {
                return (int) (o1.getClub_id()-o2.getClub_id());
            }
        });
        List<List<FootballMatch>> allFixtures = new ArrayList<>();
        List<Club> list1 = clubList.subList(0,(clubList.size()/2));
        List<Club> list2 = clubList.subList(clubList.size()/2, clubList.size());
        Collections.reverse(list2);

        for(Club c : list1)
            System.out.println(c.getClub_id() + " ,");
        System.out.println();
        for(Club c  : list2)
            System.out.println(c.getClub_id() + " ,");
        System.out.println();


        for(int j=0 ; j < clubList.size()-1;j++){

            System.out.println("fixture " + (j+1));
            if(j>0){
                Club lastofFirstList = list1.get(list1.size()-1);
                for(int k = 1; k<list1.size()-1;k++){
                    list1.set(k+1,list1.get(k));
                }
                list1.set(1,list2.get(0));

                System.out.println("list 1");
                for(Club c : list1){
                    System.out.print(c.getClub_id()+ ", ");
                }

                System.out.println();
                for(int k = 0; k<list2.size()-1;k++){
                    list2.set(k,list2.get(k+1));
                }
                list2.set(list2.size()-1,lastofFirstList);
                System.out.println("list 2");
                for(Club c : list2){
                    System.out.print(c.getClub_id() + ", ");
                }
                System.out.println();
            }

            for(int i=0; i<clubList.size()/2; i++ ){

                FootballMatch match = new FootballMatch();
                if(list1.get(i)!=list2.get(i)){
                    match.setHomeTeam(list1.get(i));
                    match.setAwayTeam(list2.get(i));

                    fixture.add(match);
                }

            }
            allFixtures.add(fixture);
        }




            return allFixtures;
    }
}
