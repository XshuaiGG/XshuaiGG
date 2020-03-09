package cn.hp.service.imp;

import cn.hp.dao.ProductDao;
import cn.hp.domain.Product;
import cn.hp.service.ProductService;
import cn.hp.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }

    @Override
    public PageBean<Product> findByPage(Integer currPage, Integer pageSize) {
        PageBean<Product> pageBean = new PageBean<Product>();
        //给pageBean 设置5个属性
        pageBean.setCurrpage(currPage);
        pageBean.setPageSize(pageSize);

        //总条目需查询数据库
        Integer totalCount =  productDao.findCount();
        pageBean.setTotalCount(totalCount);

        //计算得出 总页数
        pageBean.setTotalPage((int) Math.ceil(totalCount*1.0/pageSize));

        //这个产品集合,是分页查询的集合数据 select * from product limit 数据起始值,每页数据量
        Integer begin = (currPage-1)*pageSize;  //起始值

        //mybatis 不建议 方法传入多个参数 ,如果有多个参数常用方法有2种
        //1. 将所有参数封装到POJO类,将POJO类作为 参数
        //2. 使用Map集合
        Map<String,Object> map = new HashMap<>();
        map.put("begin",begin);
        map.put("pageSize",pageSize);
        List<Product> list = productDao.findByPage(map);
        System.out.println(list);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    /**
     * TODO  这里是分页助手的代码
     */
    public PageInfo<Product> findByPageHelper(Integer currPage, Integer pageSize) {
        PageHelper.startPage(currPage,pageSize);    //设置 开始页

        List<Product> list = productDao.findAll();  //获取所有产品数据
        // 创建分页助手中的 分页工具类 (参数1: 所有产品集合;  参数2: 代表页面显示的页码的数量)
        PageInfo<Product> pageInfo = new PageInfo<>(list,3);

        // 打印观察 pagehelper 常用的 api
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页查多少:"+pageInfo.getPageSize());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("每页显示的数据:"+pageInfo.getList().size());
        System.out.println("上一页页码:"+pageInfo.getPrePage());
        System.out.println("下一页页码:"+pageInfo.getNextPage());
        System.out.println("页面显示的第一个页码:"+pageInfo.getNavigateFirstPage());
        System.out.println("页面显示的最后一个页码:"+pageInfo.getNavigateLastPage());
        return pageInfo;
    }
}
