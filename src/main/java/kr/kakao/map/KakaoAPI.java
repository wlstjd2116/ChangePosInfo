package kr.kakao.map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;

public class KakaoAPI {
    private static final String KAKAO_API_KEY = "";
    public static double[] getAddressCoordinate(String address) throws IOException {
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String requestUrl = apiUrl + "?query=" + encodedAddress;

        // 요청 객체 생성
        CloseableHttpClient httpClinet = HttpClients.createDefault();
        // url 요청
        HttpGet httpGet = new HttpGet(requestUrl);
        // httpGet 객체에 Header set
        httpGet.setHeader("Authorization", "KakaoAK " + KAKAO_API_KEY);

        try (CloseableHttpResponse response = httpClinet.execute(httpGet)){
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray documents = jsonObject.getAsJsonArray("documents");

            // 결과 값이 있다면
            if (documents.size() > 0) {
                JsonObject document = documents.get(0).getAsJsonObject();
                double latitude = document.get("y").getAsDouble();
                double longitude = document.get("x").getAsDouble();
                return new double[]{latitude, longitude};
            } else {
                return null;
            }
        }
    }

}
