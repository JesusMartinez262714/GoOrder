
package goorderpersistencia;

import Entidades.Producto;
import Interfaces.IBaseMongoDAO;
import Interfaces.IProductoDAO;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
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
    private static final String COLLECTION = "Producto";

    public ProductoDAO() {     
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
        return productos;
    }
    
    //----------------------------------------------------------------------------------------------------------

    @Override
    public Producto crearProducto(Producto producto) throws PersistenciaException {
        try (MongoClient client = ConexionMongoDB.crearConexion()) {
            MongoDatabase dataBase = this.obtenerDataBase(client);
            MongoCollection<Producto> collection = this.obtenerCollection(dataBase);
            /**
             * Prueba
             */
            collection.insertOne(producto);
            return producto;
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al agregar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public Producto actualizarProducto(String producto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto eliminarProducto(String producto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> obtenerProductos(String producto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //----------------------------------------------------------------------------------------------------------

    @Override
    public MongoDatabase obtenerDataBase(MongoClient client) {
        MongoDatabase dataBase = client.getDatabase(ConexionMongoDB.DATA_BASE);
        return dataBase;
    }

    @Override
    public MongoCollection obtenerCollection(MongoDatabase dataBase) {
        MongoCollection collection = dataBase.getCollection(COLLECTION).withCodecRegistry(ConexionMongoDB.obtenerCodec());
        return collection;
    }
}