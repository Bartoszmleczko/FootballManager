package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Entity.Transfer;
import pl.mleczkobartosz.FootballManager.Repository.TransferRepository;

import javax.validation.Valid;

@RestController
public class TransferController {

    private final TransferRepository transferRepository;

    public TransferController(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @GetMapping("/transfers")
    public Page<Transfer> findAll(Pageable pageable){
        return transferRepository.findAll(pageable);
    }

    @PostMapping("/transfers")
    public Transfer saveTransfer(@Valid @RequestBody Transfer transfer){
        return transferRepository.save(transfer);
    }

}
