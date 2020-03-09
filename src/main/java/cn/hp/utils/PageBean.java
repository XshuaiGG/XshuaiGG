package cn.hp.utils;

import java.util.List;

/**
 * 分页工具类
 */
public class PageBean<T> {

    //分析当前类有哪些属性
    //http://localhost:90/j1912_qx/product/findAll?currpage=5&pageSize=10
    //1.当前页 --- 路径中的提交参数
    private Integer currpage;

    //2.每页显示的数据量---路径中的提交参数
    private Integer pageSize;
    //3.总条目数 -- 当前表的所有数据(通过查询数据库得知) select count(*) from product
    private Integer totalCount;
    //4.总页数  -- 计算得出 Math.ceil(totalCount*1.0/pageSize)    Math.ceil 向上取整(该函数需要double类型的数据)
    private Integer totalPage;
    //5.当前页集合数据
    private List<T> list;

    public Integer getCurrpage() {
        return currpage;
    }

    public void setCurrpage(Integer currpage) {
        this.currpage = currpage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
