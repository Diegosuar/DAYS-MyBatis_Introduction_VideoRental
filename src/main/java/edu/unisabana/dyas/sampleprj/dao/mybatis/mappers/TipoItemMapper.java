package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;


import java.util.List;
import edu.unisabana.dyas.samples.entities.TipoItem;

public interface TipoItemMapper {
    
    
    public List<TipoItem> getTiposItems();
    
    public TipoItem getTipoItem(int id);
    
    public void addTipoItem(String des);

}
