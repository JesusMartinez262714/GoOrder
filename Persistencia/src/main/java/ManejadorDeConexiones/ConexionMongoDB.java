
package ManejadorDeConexiones;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Alex García Trejo
 */
public class ConexionMongoDB {
    
    public static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String DATA_BASE = "ProyectoGoOrder";
    
    
    public static MongoClient crearConexion() {
        MongoClient mongoClient = MongoClients.create(CADENA_CONEXION);        
        return mongoClient;
    }
    
    public static CodecRegistry obtenerCodec() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();                
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return pojoCodecRegistry;
    }
}