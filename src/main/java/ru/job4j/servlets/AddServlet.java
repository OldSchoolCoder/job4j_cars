package ru.job4j.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.model.Car;
import ru.job4j.model.Photo;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.store.AdRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = 0;
        String brand = null;
        String type = null;
        Integer power = null;
        String photo = null;
        String description = null;
        Boolean sale = false;
        User owner = (User) req.getSession().getAttribute("user");
        ;
        List<Photo> photos = new ArrayList<>();
        AdRepository store = new AdRepository();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig()
                .getServletContext();
        File repository = (File) servletContext.getAttribute("javax" +
                ".servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images//photo");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator +
                            item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    photo = item.getName();
                } else {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    outputStream.write(item.get());
                    String itemValue = outputStream.toString();
                    if (item.getFieldName().equals("id")) {
                        id = Integer.valueOf(itemValue);
                    }
                    if (item.getFieldName().equals("brand")) {
                        brand = itemValue;
                    }
                    if (item.getFieldName().equals("type")) {
                        type = itemValue;
                    }
                    if (item.getFieldName().equals("power")) {
                        power = Integer.valueOf(itemValue);
                    }
                    if (item.getFieldName().equals("description")) {
                        description = itemValue;
                    }
                    if (item.getFieldName().equals("sale")) {
                        sale = itemValue.equals("sale");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (photo == null || brand == null || description == null || type == null
                || power == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                    "Error! Any field is NULL! ");
        } else {
            Car car = new Car(brand, type, power);
            photos.add(new Photo(photo));
            Post post = new Post(id, description, car, photos, sale, owner,
                    new Timestamp(System.currentTimeMillis()));
            store.add(post);
        }
        req.getRequestDispatcher("success.html").forward(req, resp);
    }
}
