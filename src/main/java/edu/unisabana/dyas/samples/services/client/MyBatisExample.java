package edu.unisabana.dyas.samples.services.client;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Cliente;
import edu.unisabana.dyas.samples.entities.Item;

public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejemplo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    @SuppressWarnings("unused")
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();

        
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            // Obtener el mapper de ClienteMapper
            ClienteMapper clienteMapper = sqlSession.getMapper(ClienteMapper.class);

            // Llamar al método consultarCliente para obtener un cliente
            Cliente cliente = clienteMapper.consultarCliente(123); // Aquí 123 es el ID del cliente que quieres consultar
            System.out.println("Cliente consultado: " + cliente);

            // Crear un objeto Item
            Item item = new Item();
            item.setId(456); // Asigna el ID deseado
            item.setNombre("Nombre del Item"); // Asigna el nombre deseado
            item.setDescrpcion("Descripción del Item"); // Asigna la descripción deseada

            // Obtener el mapper de ItemMapper
            ItemMapper itemMapper = sqlSession.getMapper(ItemMapper.class);

            // Llamar al método insertarItem para insertar un nuevo item
            itemMapper.insertarItem(item);

            // Commit de la transacción
            sqlSession.commit();

            // Verificación de que se ha insertado el item correctamente
            Item itemInsertado = itemMapper.consultarItem(456); // Consulta el item insertado
            System.out.println("Item insertado: " + itemInsertado);

            // Llamar al método consultarItems para obtener todos los items
            System.out.println("Todos los items:");
            for (final Item item2: itemMapper.consultarItems()) {
                System.out.println(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}