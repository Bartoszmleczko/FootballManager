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
import pl.mleczkobartosz.FootballManager.Service.TransferService;

import javax.validation.Valid;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public Page<Transfer> findAll(Pageable pageable){
        return transferService.findAll(pageable);
    }

    @PostMapping("/transfers")
    public Transfer saveTransfer(@Valid @RequestBody TransferModel transfer){

        return transferService.save(transfer);

    }

}
