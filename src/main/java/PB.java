import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PB {
    private PB() {
    }

    public static PB instance = new PB();

    private Map<String, Instrument> instrumentMap = new HashMap<String, Instrument>();
    public Map<String, String> codeMap = new HashMap<String, String>();
    MergeRuleInterface mergeRule = new DefaultMergeRule();
    public void readIntrument(String publisher, String instrument) {
        try {
            codeMap.put(publisher, instrument);
            InputStream is = PB.class.getClassLoader().getResourceAsStream(instrument);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String instrumentHead = br.readLine();
            String[] headColumns = null;
            String[] values = null;
            if (instrumentHead == null || "".equals(instrumentHead)) {

            } else {
                headColumns = instrumentHead.split("\\|");
                // read input instrument values
                String value = br.readLine();
                values = value.split("\\|");
                if (headColumns.length == values.length) {
                    saveInstrument(instrument, headColumns, values);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }

    public boolean saveInstrument(String instrumentName, String[] headColumns, String[] values) {
        if (headColumns == null || values == null || headColumns.length != values.length) {
            return false;
        }
        Instrument instrument = new Instrument();
        synchronized (this) {
            PB.instance.instrumentMap.put(instrumentName, instrument);
        }

        for (int i=0; i<headColumns.length;i++) {
            try {
                if ("".equals(headColumns[i].trim())) {
                    continue;
                } else {
                    instrument.mapping(headColumns[i].trim(), values[i].trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("analyze error:" + e.getMessage() + " instrument:" + instrumentName);
            } finally {
            }
        }

        return true;
    }

    public void publishInstrument(String code, String instrument) {
        try {
            if (codeMap.get(code) == null) {
                throw new Exception("unknow code :" + code);
            } else if (instrument== null ){
                throw new Exception("unknow instrument :" + instrument);
            }

            String mergeCode = instrument;
            if (!codeMap.get(code).equals(instrument)) {
                mergeCode = codeMap.get(code);
            }

            if (instrumentMap.containsKey(mergeCode)) {
                System.out.println("publish pb:" + instrument);
                Instrument mergeInstrument = instrumentMap.get(mergeCode).merge(mergeRule);
                System.out.println(mergeInstrument);
            } else {
                throw new Exception("unknow pb:" + instrument);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
        }
    }

    public Instrument getInstrument(String instrumentName) {
        return PB.instance.instrumentMap.get(instrumentName);
    }
}
