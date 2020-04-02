package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Model.Fixture;
import pl.mleczkobartosz.FootballManager.Model.FootballMatch;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.FixtureRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final ClubRepository clubRepository;
    private final FixtureRepository fixtureRepository;

    public LeagueService(LeagueRepository leagueRepository, ClubRepository clubRepository, FixtureRepository fixtureRepository) {
        this.leagueRepository = leagueRepository;
        this.clubRepository = clubRepository;
        this.fixtureRepository = fixtureRepository;
    }

    public Page<League> findAll(Optional<String> leagueName, Pageable pageable){
        return leagueRepository.findByLeagueName(leagueName.orElse("_"),pageable);
    }

    @Transactional
    public League findById(Long id){
        return leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
    }

    @Transactional
    public League save(League league){
        return leagueRepository.save(league);
    }

    @Transactional
    public League update(Long id, League league){
        League dbLeague = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        dbLeague.setLeagueName(league.getLeagueName());
        return leagueRepository.save(dbLeague);
    }

    @Transactional
    public String delete(Long id){
        League league = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        return "League deleted";
    }

    @Transactional
    public Set<Club> findClubs(Long id){
        League league =  leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        return league.getClubs();
    }

    @Transactional
    public String createDatabaseFixtures(Long id){
        League league = this.findById(id);
        if(league.getFixtures()==null){
            List<Fixture> fixtures = this.generateFixtures(id);
            for(Fixture f : fixtures){
                fixtureRepository.save(f);
            }
            return "Fixtures generated in database";
        }else{
            return "League already has generated fixtures";
        }

    }

    @Transactional
    public List<Fixture> getFixtures( Long id){
        League league = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        return league.getFixtures();
    }


    private List<Fixture> generateFixtures(Long id){

        Set<Club> clubs = this.findClubs(id);
        List<Club> clubList = new ArrayList<>();

        LocalDateTime seasonStartDate = LocalDateTime.of(LocalDate.now().getYear(), 9,1,17,0);
        clubList.addAll(clubs);
        Collections.sort(clubList, new Comparator<Club>() {
            @Override
            public int compare(Club o1, Club o2) {
                return (int) (o1.getClub_id()-o2.getClub_id());
            }
        });
        List<Fixture> allFixtures = new ArrayList<>();
        List<Club> list1 = clubList.subList(0,(clubList.size()/2));
        List<Club> list2 = clubList.subList(clubList.size()/2, clubList.size());
        Collections.reverse(list2);

        for(int j=0 ; j < clubList.size()-1;j++){

            Fixture fixture = new Fixture();
            fixture.getMatches().clear();
            fixture.setFixtureNo((long) j);
            fixture.setFixtureStart(seasonStartDate.plusWeeks(j));
            fixture.setLeague(this.findById(id));

            int fixtureLength = Math.round(clubList.size()/3);
            fixture.setFixtureEnd(seasonStartDate.plusWeeks(j).plusDays(fixtureLength));

            if(j>0){
                Club lastofFirstList = list1.get(list1.size()-1);
                for(int k = 1; k<list1.size()-1;k++){
                    list1.set(k+1,list1.get(k));
                }
                list1.set(1,list2.get(0));

                for(int k = 0; k<list2.size()-1;k++){
                    list2.set(k,list2.get(k+1));
                }
                list2.set(list2.size()-1,lastofFirstList);
            }

            for(int i=0; i<clubList.size()/2; i++ ){

                FootballMatch match = new FootballMatch();
                if(list1.get(i)!=list2.get(i)){
                    match.setHomeTeam(list1.get(i));
                    match.setAwayTeam(list2.get(i));
                    match.setDate(fixture.getFixtureStart().plusDays(i%fixtureLength));
                    fixture.getMatches().add(match);
                }
            }
            allFixtures.add(fixture);
        }

        return allFixtures;

    }





}
