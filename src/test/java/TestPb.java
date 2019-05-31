public class TestPb {
    public static void main(String[] args) {

        PB pb = PB.instance;
        pb.readIntrument("LME","PB_03_2018");
        pb.readIntrument("PRIME","PRIME_PB_03_2018");
        // publish PB_03_2018 by LME
        pb.publishInstrument("LME","PB_03_2018");
        // publish PB_03_2018 by PRIME
        pb.publishInstrument("PRIME","PB_03_2018");

        pb.publishInstrument("LME","PRIME_PB_03_2018");


    }
}
