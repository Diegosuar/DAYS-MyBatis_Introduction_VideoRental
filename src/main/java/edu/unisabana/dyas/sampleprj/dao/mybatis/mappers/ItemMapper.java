package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;


import java.util.List;
import edu.unisabana.dyas.samples.entities.Item;

/**
 *
 * @author cesarvefe
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(int id);
    
    public void insertarItem(Item it);

        
}
