package cn.hp.service;

import cn.hp.domain.Product;
import cn.hp.utils.PageBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 产品业务层
 */
public interface ProductService {
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
     * 根据id执行删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 手动分页业务层方法
     * @param currPage
     * @param pageSize
     * @return
     */
    PageBean<Product> findByPage(Integer currPage, Integer pageSize);

    /**
     * TODO 分页助手的查询方法
     * PageInfo  该类 等同于 原来 手动分页工具类 PageBean
     * @param currPage
     * @param pageSize
     * @return
     */
    PageInfo<Product> findByPageHelper(Integer currPage, Integer pageSize);
}







