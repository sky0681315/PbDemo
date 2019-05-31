public class Instrument implements Cloneable{

    public String getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(String lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    private String lastTradingDate; //
    private String deliveryDate;
    private String market;
    private String label;
    private boolean tradable;
    private String exchangeCode;


    public Instrument merge(MergeRuleInterface mergeRule) {
        return mergeRule.merge(this);

    }

    public void mapping(String column, String value) throws Exception{
        if("LAST_TRADING_DATE".equals(column)) {
            this.setLastTradingDate(value);
        } else if("DELIVERY_DATE".equals(column)) {
            this.setDeliveryDate(value);
        } else if("MARKET".equals(column)) {
            this.setMarket(value);
        } else if("LABEL".equals(column)) {
            this.setLabel(value);
        } else if("TRADABLE".equals(column)) {
            this.setTradable(Boolean.valueOf(value));
        } else if("EXCHANGE_CODE".equals(column)) {
            if (PB.instance.getInstrument(value) != null) {
                this.setExchangeCode(value);
            } else {
                throw new Exception("unknow exchange code");
            }
        } else {
            // to-do nothing
        }
    }

    @Override
    public String toString() {
        return "|LAST_TRADING_DATE|DELIVERY_DATE|MARKET|LABEL|TRADABLE|\n" + "|" + this.lastTradingDate + "|" +
                this.deliveryDate + "|" + this.market + "|" + this.label + "|" + this.tradable + "|";
    }


}
