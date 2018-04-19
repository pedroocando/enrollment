package controllers.utils;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by nisa on 16/05/17.
 */
public class ListPager<E> {
    public List<E> entities; // entites per page
    public JsonNode jsonNode;

    private class Pager {
        public Integer totalEntitiesPerPage; // total entities for page
        public Integer totalEntities; // total entities for table
        public Integer pageIndex; // current page
        public Integer pageSize; // size per page
        public Integer pages;   // amount of pages
        public final Integer startIndex = 0;
        public Integer endIndex;

        public Pager(Integer totalEntitiesPage, Integer entitiesTotal, Integer pageIndex, Integer pageSize){
            this.totalEntitiesPerPage = totalEntitiesPage;
            this.totalEntities = entitiesTotal;
            if(pageIndex == null || pageSize == null || pageIndex < 0 || pageSize < 1) {
                this.pageIndex = 0;
                this.pageSize = this.totalEntities;
            }else{
                this.pageIndex = pageIndex;
                this.pageSize = pageSize;
            }
            this.pages = (int) Math.ceil((double)this.totalEntities / this.pageSize);
            this.endIndex = this.pages - 1;
        }

    }
    public Pager pager;

    public ListPager(List<E> entities, Integer totalEntities, Integer pageIndex, Integer pageSize) {
        this.entities = entities;
        pager = new Pager(entities.size(), totalEntities, pageIndex, pageSize);
    }

    public ListPager(List<E> entities) {
        this.entities = entities;
        pager = new Pager(entities.size(), entities.size(), 0, entities.size());
    }

    public int getTotalEntities(){
        return this.pager.totalEntities;
    }
}
