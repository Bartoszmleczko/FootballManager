package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Entity.Transfer;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Exception.WrongTransferRequest;
import pl.mleczkobartosz.FootballManager.Model.TransferModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;
import pl.mleczkobartosz.FootballManager.Repository.TransferRepository;

import javax.validation.Valid;

@RestController
public class TransferController {

    private final TransferRepository transferRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public TransferController(TransferRepository transferRepository, ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.transferRepository = transferRepository;
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/transfers")
    public Page<Transfer> findAll(Pageable pageable){
        return transferRepository.findAll(pageable);
    }

    @PostMapping("/transfers")
    public Transfer saveTransfer(@Valid @RequestBody TransferModel transfer){

        Transfer dbTransfer = new Transfer();
        Club buyer = clubRepository.findById(transfer.getBuying_id()).orElseThrow(() -> new CustomNotFoundException(new Club(),transfer.getBuying_id()));
        Club seller = clubRepository.findById(transfer.getSeller_id()).orElseThrow(() -> new CustomNotFoundException(new Club(),transfer.getSeller_id()));
        Player player = playerRepository.findById(transfer.getPlayerId()).orElseThrow(() -> new CustomNotFoundException(new Player(),transfer.getPlayerId()));

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
