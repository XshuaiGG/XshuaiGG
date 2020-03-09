package cn.hp.dao;

import cn.hp.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (旅游)产品持久层
 */

public interface ProductDao {
    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    void save(Product product);

    /**
     * 根据产品id查询一个产品对象
     * @param id
     * @return
     */
    Product findById(Integer id);
    /**
     * 更新产品
     * @param product
     */
    void update(Product product);

    /**
     * 根据id删除产品
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询总数量
     * @return
     */
    Integer findCount();

    /**
     * 分页查询 结果集
     * @param
     * @return
     */
    List<Product> findByPage(Map<String, Object> map);
}
