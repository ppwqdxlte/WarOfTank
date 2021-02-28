package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/28 12:07
 * 在filterChain中处理加入位置的记录
 * 同时在filter中加入第三个参数
 * 递归方式，先过滤一遍request 12，再回来一步步退出FilterChain2的doFilter方法，
 * 过滤response 21的顺序
 */
public class ServletTest2 {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:)，<script>，欢迎访问 github.com ，大家都是996 ";
        Response response = new Response();
        response.str = "";
        FilterChain2 chain = new FilterChain2();
        chain.addFilter(new HTMLFilter2()).addFilter(new SensitiveFilter2());
        chain.doFilter(request,response,chain);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}
interface Filter2{
    void doFilter(Request request, Response response,FilterChain2 filterChain2);
}
class HTMLFilter2 implements Filter2{

    @Override
    public void doFilter(Request request, Response response,FilterChain2 filterChain) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]");
        filterChain.doFilter(request,response,filterChain);
        response.str = response.str.concat("--HTML filtered--");
    }
}
class SensitiveFilter2 implements Filter2{

    @Override
    public void doFilter(Request request, Response response,FilterChain2 filterChain) {
        request.str = request.str.replaceAll("996","955");
        filterChain.doFilter(request,response,filterChain);
        response.str = response.str.concat("--Sensitive filtered--");
    }
}
class FilterChain2 implements Filter2{

    private List<Filter2> filter2s = new ArrayList<>();

    int loc;

    public FilterChain2 addFilter(Filter2 filter2){
        filter2s.add(filter2);
        return this;
    }
    @Override
    public void doFilter(Request request, Response response, FilterChain2 filterChain) {
        if (loc == filter2s.size()) return;
        Filter2 filter2 = filter2s.get(loc);
        loc++;
        filter2.doFilter(request,response,filterChain);
    }
}
