package plugin.asyncHttp;

import org.asynchttpclient.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

/**
 * http类
 */
public class AsyncHttpUtil {

    private static AsyncHttpClient asyncHttpClient;

    private static final Long MAX_TIMEOUT = 3000L;

    static {
        init();
    }

    /**
     * 初始化asynchttp请求
     */
    private static void init() {
        asyncHttpClient = asyncHttpClient(config());
    }

    public static void postSync(String url, Map<String,String> headMap)  {
//        new RequestBuilder("MKCOL").setUrl("http://host:port/folder1").build()
//        syncRequest(asyncHttpClient.);
    }

    public static void postAsync(String url, Map<String,String> headMap) {
//        if(null!=headMap){
//            List<String,String> list=headMap.entrySet().iterator().stream().collect(Collectors.toList());
//        }else{
//
//        }
//        Request mkcolRequest = new RequestBuilder(HttpEnum.Method.POST.getCode())
//                .setUrl(url)
//                .setHeaders()
//                .build();
//        asyncRequest(asyncHttpClient.);
    }



    private static Response syncRequest(Request request) {
        Response response = null;
        try {
            response = asyncRequest(request).get(MAX_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return response;
    }


    private static ListenableFuture<Response> asyncRequest(Request request) {
        return asyncHttpClient.executeRequest(request);
    }

    public static void main(String[] args) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConstant.SerializerType.BYTE_ARRAY.getSerializer());
//        props.putAll(producerConfigs());
//        KafkaTemplate<Integer, String> kafkaTemplate = getTemplate(null, String.class, null);
//        KafkaConstant.SerializerType serializerType = KafkaConstant.SerializerType.getSerializerByClass(KafkaProducerCreateFactory.class);
//        System.out.printf(props.toString());
        try {
            String url = "https://cn.pornhub.com/video/search?search=pronhub";
            // bound
            Future<Response> whenResponse = asyncHttpClient.prepareGet(url).execute();
            System.out.println(whenResponse.get().getResponseBody());
            // unbound
//            Request request = get("https://github.com/AsyncHttpClient/async-http-client").build();
//            Future<Response> whenResponse2 = asyncHttpClient.executeRequest(request);

//            System.out.println(whenResponse2.get().getResponseBody());

            Request mkcolRequest = new RequestBuilder("MKCOL").setUrl("http://host:port/folder1").build();
            Response response = asyncHttpClient.executeRequest(mkcolRequest).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try (AsyncHttpClient asyncHttpClient1 = asyncHttpClient()) {
            asyncHttpClient
                    .prepareGet("http://www.example.com/")
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .thenAccept(System.out::println)
                    .join();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
