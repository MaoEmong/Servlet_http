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
        // return "forward:list"; // 포워딩
        return "list"; // view 이름
    }

    public String insertForm(HttpServletRequest req, HttpServletResponse resp) {
        // return "forward:insert-form";
        return "insert-form"; // view 이름
    }

    public String detail(HttpServletRequest req, HttpServletResponse resp) {
        var model = productService.상품상세(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("model", model);
        // return "forward:detail";
        return "detail"; // view 이름
    }

    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String qty = req.getParameter("qty");
        // int price = Integer.parseInt(req.getParameter("price"));
        // int qty = Integer.parseInt(req.getParameter("qty"));

        int price2 = Integer.parseInt(price);
        int qty2 = Integer.parseInt(qty);

        productService.상품등록(name, price2, qty2);

        return "/product.do?cmd=list"; // 주소 url
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        int id2 = Integer.parseInt(id);

        productService.상품삭제(id2);
        return "/product.do?cmd=list"; // 주소 url
    }
}
