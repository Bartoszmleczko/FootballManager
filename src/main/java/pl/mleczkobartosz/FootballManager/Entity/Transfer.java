package pl.mleczkobartosz.FootballManager.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long transfer_id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn
    private Club buyingClub;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn
    private Club sellingClub;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn
    private Player player;

    @Min(0)
    @Column(name = "base_price")
    private Long basePrice;

    @Min(0)
    @Column(name = "add_ons")
    private Long addOns;

    @Column(name = "final_price")
    private Long finalPrice;

    public Transfer() {
    }

    public Transfer(Club buyingClub, Club sellingClub, Player player, Long basePrice, Long addOns) {
        this.buyingClub = buyingClub;
        this.sellingClub = sellingClub;
        this.player = player;
        this.basePrice = basePrice;
        this.addOns = addOns;
        this.finalPrice = this.finalPrice + this.addOns;
    }

    public Club getBuyingClub() {
        return buyingClub;
    }

    public void setBuyingClub(Club buyingClub) {
        this.buyingClub = buyingClub;
    }

    public Club getSellingClub() {
        return sellingClub;
    }

    public void setSellingClub(Club sellingClub) {
        this.sellingClub = sellingClub;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public Long getAddOns() {
        return addOns;
    }

    public void setAddOns(Long addOns) {
        this.addOns = addOns;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Long transfer_id) {
        this.transfer_id = transfer_id;
    }
}
