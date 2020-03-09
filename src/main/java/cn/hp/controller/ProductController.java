package cn.hp.controller;

import cn.hp.domain.Product;
import cn.hp.service.ProductService;
import cn.hp.utils.PageBean;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 分页助手------查询所有数据
     * @param model
     * @param currPage      当前页参数
     * @param pageSize      每页显示条目
     * @return
     */
    @RequestMapping("/findAll2")
    public ModelAndView findAll2(
            ModelAndView model,
            @RequestParam(name="currPage",defaultValue = "1") Integer currPage, //默认进入列表显示第1页数据
            @RequestParam(name="pageSize",defaultValue = "5") Integer pageSize  //默认每页显示5条数据
    ){
        PageInfo<Product> pageInfo = productService.findByPageHelper(currPage, pageSize);
        model.addObject("pageInfo",pageInfo);//封装数据
        //设置视图页面
        model.setViewName("product-list1");
        return model;
    }

    /**
     * 手动分页------查询所有数据
     * @param model
     * @param currPage      当前页参数
     * @param pageSize      每页显示条目
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            ModelAndView model,
            @RequestParam(name="currPage",defaultValue = "1") Integer currPage, //默认进入列表显示第1页数据
            @RequestParam(name="pageSize",defaultValue = "5") Integer pageSize  //默认每页显示5条数据
    ){
      /*  没有分页的所有数据
        List<Product> productList = productService.findAll();
        model.addObject("productList",productList);//封装数据*/

        PageBean<Product> pageBean = productService.findByPage(currPage,pageSize);
        model.addObject("pageBean",pageBean);//封装数据
        //设置视图页面
        model.setViewName("product-list");
        return model;
    }

    //保存操作
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        //保存完毕之后,要跳转到页面刷新列表
        return "redirect:findAll";
    }

    //跳转到编辑页,回显数据
    @RequestMapping("/updateUI")
    public  ModelAndView  updateUI(ModelAndView model,Integer id){
        Product product = productService.findById(id);
        model.addObject("product",product);
        model.setViewName("product-update");
        return model;
    }

    //更新产品
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:findAll";
    }

    //删除产品
    @RequestMapping("/deleteOne")
    public String deleteOne(Integer id){
        productService.deleteById(id);
        return "redirect:findAll";  //删除成功后,回到列表刷新
    }

    //删除产品
    @RequestMapping("/deleteMore")
    public String deleteMore(@RequestParam(name="ids") Integer[] ids){
        if(ids!=null){
            for (Integer id:ids) {
                productService.deleteById(id);
            }
        }

        return "redirect:findAll";  //删除成功后,回到列表刷新
    }
}














