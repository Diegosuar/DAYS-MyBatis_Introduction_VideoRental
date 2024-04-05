package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

/**
 *
 * @author cesarvefe
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") int id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idc") int id, 
            @Param("iditem") int idit, 
            @Param("fi") Date fechainicio,
            @Param("ff") Date fechafin);

    public void deleteAll();

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
}
