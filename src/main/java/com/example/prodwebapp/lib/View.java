package com.example.prodwebapp.lib;

import java.io.IOException;
import java.util.Map;

import com.samskivert.mustache.Template;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class View {
    private Template template;

    public View(Template template) {
        this.template = template;
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        resp.setContentType("text/html; charset=UTF-8");

        // req에 저장된 데이터를 model로 사용 (선택사항)
        @SuppressWarnings("unchecked")
        Map<String, Object> model = (Map<String, Object>) req.getAttribute("model");
        if (model == null) {
            model = Map.of();
        }

        template.execute(model, resp.getWriter());
        resp.getWriter().flush();
    }
}

