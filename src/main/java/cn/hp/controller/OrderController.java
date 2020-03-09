package cn.hp.controller;

import cn.hp.domain.Order;
import cn.hp.domain.Product;
import cn.hp.service.OrderService;
import cn.hp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * 获取订单列表信息
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView model){
        List<Order> orderList = orderService.findAll();
        //将数据库查询结果封装到model
        model.addObject("orderList",orderList);
        //展示页面
        model.setViewName("order-list");
        return model;
    }

    /**
     * 跳转到 添加订单的页面 (注意:需拿到 产品列表并传递到 添加页面)
     * @return
     */
    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView model){
        List<Product> productList = productService.findAll();
        model.addObject("productList",productList); //获取产品列表
        model.setViewName("order-add"); //跳转到 订单添加页面
        return model;
    }

    /**
     * 保存订单信息
     * @param order 接收页面表单参数
     * @return
     */
    @RequestMapping("/save")
    public String save(Order order){
        orderService.save(order);
        return "redirect:findAll";
    }

}













