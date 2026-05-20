
package Interfaces;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Alex García Trejo
 */
public interface IBaseMongoDAO {
    
    public abstract MongoDatabase obtenerDataBase(MongoClient client);
    
    public abstract MongoCollection obtenerCollection(MongoDatabase dataBase);
    
}
