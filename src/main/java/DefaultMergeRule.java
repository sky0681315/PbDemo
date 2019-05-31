public class DefaultMergeRule implements MergeRuleInterface {
    public Instrument merge(Instrument src) {
        if (src == null) {
            return null;
        }
        Instrument mergeInstrument = new Instrument();
        mergeInstrument.setLabel(src.getLabel());
        String srcExchangeCode = src.getExchangeCode();
        if (srcExchangeCode != null && !"".equals(srcExchangeCode)) {
            Instrument exchangeInstr = PB.instance.getInstrument(srcExchangeCode).merge(this);
            mergeInstrument.setLastTradingDate(exchangeInstr.getLastTradingDate());
            mergeInstrument.setDeliveryDate(exchangeInstr.getDeliveryDate());
            // enforce this tradable flag  in all cases
            mergeInstrument.setTradable(src.isTradable());
        } else {
            // d
            mergeInstrument.setLastTradingDate(src.getLastTradingDate());
            mergeInstrument.setDeliveryDate(src.getDeliveryDate());
            mergeInstrument.setTradable(true);
        }
        mergeInstrument.setMarket("PB");
        return mergeInstrument;

    }
}
