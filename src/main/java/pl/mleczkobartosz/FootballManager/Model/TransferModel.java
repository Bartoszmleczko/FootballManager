package pl.mleczkobartosz.FootballManager.Model;

public class TransferModel {

    private Long buying_id;
    private Long seller_id;
    private Long playerId;
    private Long basePrice;
    private Long addOns;
    private Long finalPrice;

    public TransferModel() {
    }

    public TransferModel(Long buying_id, Long seller_id, Long playerId, Long basePrice, Long addOns) {
        this.buying_id = buying_id;
        this.seller_id = seller_id;
        this.playerId = playerId;
        this.basePrice = basePrice;
        this.addOns = addOns;
    }

    public Long getBuying_id() {
        return buying_id;
    }

    public void setBuying_id(Long buying_id) {
        this.buying_id = buying_id;
    }

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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
}
