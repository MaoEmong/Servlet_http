package com.example.prodwebapp.product;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController {

    ProductService productService = new ProductService();

    public String list(HttpServletRequest req, HttpServletResponse resp) {
        // model => 데이터베이스에서 조회한 데이터들을 여기선 모델이라 부른다(모델링된 데이터)
        List<Product> models = productService.상품목록();
        req.setAttribute("models", models);
        req.setAttribute("what", "엉?");
        // return "forward:list";
        return "list";
    }

    public String insertForm(HttpServletRequest req, HttpServletResponse resp) {
        // return "forward:insert-form";
        return "insert-form";
    }

    public String detail(HttpServletRequest req, HttpServletResponse resp) {
        var model = productService.상품상세(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("model", model);
        // return "forward:detail";
        return "detail";
    }

    public String insert(HttpServletRequest req, HttpServletResponse resp) {

        return "/product.do?cmd=list";
    }
}
