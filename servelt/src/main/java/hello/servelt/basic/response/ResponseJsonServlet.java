package hello.servelt.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servelt.basic.request.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/response-json
 *
 */

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
            /*
            *  application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
            * 그래서 스펙에서 charset=utf-8과 같은 추가 파라미터를 지원하지 않는다.
            * 따라서 application/json 이라고만 사용해야지 application/json;charset=utf-8 이라고 전달하는 것은
            * 의미 없는 파라미터를 추가한 것이 된다.
            * */
            response.setHeader("content-type", "application/json");
//            response.setCharacterEncoding("utf-8");

            HelloData data = new HelloData();
            data.setUsername("kim");
            data.setAge(20);

            //{"username":"kim","age":20}
            //Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 를 사용하면 객체를 JSON 문자로 변경 가능
            String result = objectMapper.writeValueAsString(data);
            response.getWriter().write(result);
    }
}

