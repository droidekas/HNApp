package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "greendao");
        Entity hnItem = schema.addEntity("HNItem");
        hnItem.addStringProperty("title");
        hnItem.addStringProperty("by");
        hnItem.addIntProperty("id").primaryKey();
        hnItem.addStringProperty("kids");
        hnItem.addIntProperty("score");
        hnItem.addStringProperty("time");
        hnItem.addStringProperty("type");
        hnItem.addStringProperty("url");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
