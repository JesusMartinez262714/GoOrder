
package goorderpersistencia;

import Entitys.Producto;
import Interfaces.IBaseMongoDAO;
import Interfaces.IProductoDAO;
import ManejadorDeConexiones.ConexionMongoDB;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class ProductoDAO implements IProductoDAO, IBaseMongoDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductoDAO.class.getName());
    private List<Producto> productos;
    private static final String NOMBRE_COLLECTION = "Producto";

    public ProductoDAO() {
        productos = new ArrayList<>();    
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p: productos) {
            if (p.getNombre().toLowerCase().contains(nombreProducto.toLowerCase())) {
                resultado.add(p);
            }
        }
        if (resultado.isEmpty()) {
            throw new PersistenciaException("Producto(s) no encontrado(s)");
        }
        return resultado;  
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        List<Producto> lista = new ArrayList<>();
        try (MongoClient client = ConexionMongoDB.crearConexion()){
            MongoDatabase dataBase = this.obtenerDataBase(client);
            MongoCollection<Producto> collection = this.obtenerCollection(dataBase);
            
            FindIterable<Producto> iterable = collection.find();
            MongoCursor<Producto> cursor = iterable.cursor();
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
            return lista;
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al listar producto de la Base de datos.");
        }
    }

    @Override
    public MongoDatabase obtenerDataBase(MongoClient client) {
        MongoDatabase dataBase = client.getDatabase(ConexionMongoDB.DATA_BASE).withCodecRegistry(ConexionMongoDB.obtenerCodec());
        return dataBase;
    }

    @Override
    public MongoCollection obtenerCollection(MongoDatabase dataBase) {
        MongoCollection<Producto> collection = dataBase.getCollection(NOMBRE_COLLECTION, Producto.class);
        return collection;
    }
}