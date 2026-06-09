package app;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {
    static MongoClient client; // 서버 연결
    static MongoDatabase database; // DB까지 연결

    static {
        // pojoCodecProvider
        // BSON으로 변환해 주는 Codec

        // BSON : Binary JSON [타입][필드명][값]
        // MongoDB에서 사용하는 데이터 저장 방법

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

        // CodecRegistry : mongoDB
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017");
        client = MongoClients.create(connectionString);

        // MongoDatabase : Mongodb의 특정 데이터베이스에 접근하기 위한 객체
        database = client.getDatabase("todo_db_v2");
//        database = client.getDatabase("todo_db").withCodecRegistry(pojoCodecRegistry);;
    }

    public static void close() {
        client.close();
    }

    public static MongoCollection<Document> getCollection(String colName) {
        MongoCollection<Document> collection = database.getCollection(colName);
        return collection;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static <T> MongoCollection<T> getCollection(String colName, Class<T> clazz) {
        MongoCollection<T> collection = database.getCollection(colName, clazz);
        return collection;
    }
}