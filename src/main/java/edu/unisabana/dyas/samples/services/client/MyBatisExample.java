package edu.unisabana.dyas.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.samples.entities.Cliente;

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
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        try (SqlSession sqlss = sessionfact.openSession()) {
            // Obtener el mapper para Cliente
            ClienteMapper clienteMapper = sqlss.getMapper(ClienteMapper.class);
            
            // Ejecutar la consulta para consultar los clientes
            List<Cliente> clientes = clienteMapper.consultarClientes();
            
            // Procesar los resultados
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
