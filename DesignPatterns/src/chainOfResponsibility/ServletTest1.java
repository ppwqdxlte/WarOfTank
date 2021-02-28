package chainOfResponsibility;

import Iterator.ArrayList;
import Iterator.List;

/**
 * @author:李罡毛
 * @date:2021/2/28 11:24
 * 用最直观的方式处理response
 * 直接将response的处理放在request的下面
 * 简单过滤请求响应字符串，1212顺序
 */
public class ServletTest1 {
    public static void main(String[] args){
        Request request = new Request();
        request.str = "大家好:)，<script>，欢迎访问 github.com ，大家都是996 ";
        Response response = new Response();
        response.str = "";
        FilterChain chain = new FilterChain();
        chain.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
        chain.doFilter(request,response);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}
class Request{
    String str;
}
class Response{
    String str;
}
interface Filter{
    void doFilter(Request request,Response response);
}
class FilterChain{
    private List<Filter> filters = new ArrayList();
    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }
    public void doFilter(Request request,Response response){
        for (int i = 0; i < filters.size(); i++) {
            filters.get(i).doFilter(request,response);
        }
    }
}
class HTMLFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]");
        response.str = response.str.concat("--HTML filtered--");
    }
}
class SensitiveFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("996","955");
        response.str = response.str.concat("--Sensitive filtered--");
    }
}