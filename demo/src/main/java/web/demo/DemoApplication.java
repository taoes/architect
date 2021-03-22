package web.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping
public class DemoApplication {

    public static synchronized void main(String[] args) throws ClassNotFoundException {
        //System.gc();
        Class.forName("");

        // SpringApplication.run(DemoApplication.class, args);
        System.out.println(String.class.getClassLoader());
    }


    @GetMapping("/gc")
    public String execGC() {

        System.gc();
        return "OK";
    }

    static class MyServlet extends HttpServlet {

        /**
         * Servlet 第被初始化的时候调用或者第一次访问时候执行
         * <p>具体和值{@code  load-on-startup} 有关系</>
         */
        @Override
        public void init() throws ServletException {
            super.init();
        }

        /**
         * Servlet 被销毁的时候调用
         */
        @Override
        public void destroy() {
            super.destroy();
        }

        /**
         * Service 负责根据请求方式分发到 doGet/doPost/doDelete/doOptions
         */
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.service(req, resp);
        }
    }


    static class MyClassLoader extends ClassLoader {


        /**
         * 如果想要破坏双亲委派机制，那么久需要重写 loadClass 方法，其父类实现了双亲委派机制
         */
        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }


        /**
         * loadClass() 方法中使用的了模板方法的设计模式，当需要子类加载器加载类的时候，会执行findClass方法
         */
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }
}
