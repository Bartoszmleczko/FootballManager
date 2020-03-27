package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Entity.Transfer;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Exception.WrongTransferRequest;
import pl.mleczkobartosz.FootballManager.Model.TransferModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;
import pl.mleczkobartosz.FootballManager.Repository.TransferRepository;
import pl.mleczkobartosz.FootballManager.Rest.PlayerController;

import javax.transaction.Transactional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public TransferService(TransferRepository transferRepository, ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.transferRepository = transferRepository;
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Page<Transfer> findAll(Pageable pageable){
        return transferRepository.findAll(pageable);
    }

    @Transactional
    public Transfer save(TransferModel transfer){

        Transfer dbTransfer = new Transfer();
        Club buyer = clubRepository.findById(transfer.getBuying_id()).orElseThrow(() -> new CustomNotFoundException(Club.class.getSimpleName(),transfer.getBuying_id()));
        Club seller = clubRepository.findById(transfer.getSeller_id()).orElseThrow(() -> new CustomNotFoundException(Club.class.getSimpleName(),transfer.getSeller_id()));
        Player player = playerRepository.findById(transfer.getPlayerId()).orElseThrow(() -> new CustomNotFoundException(Player.class.getSimpleName(),transfer.getPlayerId()));

        if(buyer == seller){
            throw new WrongTransferRequest();
        }

        dbTransfer.setBasePrice(transfer.getBasePrice());
        dbTransfer.setAddOns(transfer.getAddOns());
        dbTransfer.setBuyingClub(buyer);
        dbTransfer.setSellingClub(seller);
        dbTransfer.setPlayer(player);
        dbTransfer.setFinalPrice(transfer.getBasePrice()+transfer.getAddOns());

        player.setClub(buyer);

        playerRepository.save(player);

        return transferRepository.save(dbTransfer);
    }

}
